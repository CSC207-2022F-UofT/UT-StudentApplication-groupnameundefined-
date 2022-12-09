package backend.repository;

import backend.model.SocialMediaProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialMediaProfileRepository extends JpaRepository<SocialMediaProfile, Long> {

}
