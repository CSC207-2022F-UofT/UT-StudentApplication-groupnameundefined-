package backend.controller.imp;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.controller.SectionController;
import backend.model.Section;

@CrossOrigin
@RestController
@RequestMapping("/api/section")
public class SectionControllerImp implements SectionController {

    @Autowired
    Logger logger;

}
