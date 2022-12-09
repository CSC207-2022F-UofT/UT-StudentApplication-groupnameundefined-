package backend.repository;

import backend.model.SectionBlock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface SectionBlockRepository extends JpaRepository<SectionBlock, Long> {
	@Query("SELECT s FROM SectionBlock s " +
			"WHERE s.section.name = :sectionCode " +
			"AND s.section.course.code = :courseCode " +
			"AND (s.section.course.sectionCode = :session OR s.section.course.sectionCode = 'Y')"
	)
	List<SectionBlock> findByCode(
			@Param("session") String session,
			@Param("courseCode") String courseCode,
			@Param("sectionCode") String sectionCode
	);
}
