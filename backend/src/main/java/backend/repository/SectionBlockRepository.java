package backend.repository;

import backend.model.SectionBlock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface SectionBlockRepository extends JpaRepository<SectionBlock, Long> {
	@Query("SELECT s FROM SectionBlock s WHERE s.section.name = :section AND s.section.course.code = :course")
	List<SectionBlock> findByCode(
			@Param("course") String course, @Param("section") String section
	);
}
