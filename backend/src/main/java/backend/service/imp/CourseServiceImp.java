package backend.service.imp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import backend.model.Course;
import backend.model.Section;
import backend.model.SectionBlock;
import backend.repository.CourseRepository;
import backend.repository.SectionBlockRepository;
import backend.repository.SectionRepository;
import backend.service.CourseService;
import org.springframework.util.ResourceUtils;

import com.google.gson.Gson;

@Service
public class CourseServiceImp implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    SectionRepository sectionRepository;

    @Autowired
    SectionBlockRepository sectionBlockRepository;

    @Value(value = "classpath:data/courses.json")
    private Resource courseResource;

    JSONParser parser = new JSONParser();

    @Autowired
    Logger logger;

    @Override
    public List<Course> getAllCourses() {
        return new ArrayList<Course>(courseRepository.findAll());
    }

    @Override
    public void loadCourses() throws FileNotFoundException, IOException, ParseException {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        File courseDataFile = courseResource.getFile();
        Reader courseReader = new FileReader(courseDataFile);

        Object courseData = parser.parse(courseReader);

        JSONArray courses = (JSONArray) courseData;

        for (Object c : courses) {
            JSONObject _course = (JSONObject) c;
            JSONArray courseSections = (JSONArray) _course.get("sections");

            Course course = gson.fromJson(_course.toJSONString(), Course.class);

            if (!course.getCode().contains("CSC207")) {
                continue;
            }

            for (Object s : courseSections) {
                JSONObject _section = (JSONObject) s;
                JSONArray sectionBlocks = (JSONArray) _section.get("sectionBlocks");

                Section section = gson.fromJson(_section.toJSONString(), Section.class);

                if (sectionBlocks != null) {
                    for (Object secB : sectionBlocks) {
                        JSONObject _sectionBlock = (JSONObject) secB;

                        SectionBlock sectionBlock = gson.fromJson(_sectionBlock.toJSONString(),
                                SectionBlock.class);

                        section.addSectionBlock(sectionBlock);
                    }
                }
                course.addSection(section);
            }
            courseRepository.save(course);
        }
    }

}
