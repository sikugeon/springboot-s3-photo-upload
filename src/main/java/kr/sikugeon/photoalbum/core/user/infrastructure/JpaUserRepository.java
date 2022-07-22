package kr.sikugeon.photoalbum.core.user.infrastructure;

import kr.sikugeon.photoalbum.Constant;
import kr.sikugeon.photoalbum.core.user.domain.User;
import kr.sikugeon.photoalbum.core.user.domain.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Spring Data JPA 기반 사용자 저장소 구현체
 *
 * @author springrunner.kr@gmail.com
 */
@Profile(Constant.PROFILE_PRODUCTION)
@Repository
public interface JpaUserRepository extends UserRepository, JpaRepository<User, String> {

}
