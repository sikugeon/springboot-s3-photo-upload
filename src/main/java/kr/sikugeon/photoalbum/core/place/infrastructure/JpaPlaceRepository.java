package kr.sikugeon.photoalbum.core.place.infrastructure;

import kr.sikugeon.photoalbum.Constant;
import kr.sikugeon.photoalbum.core.place.domain.Place;
import kr.sikugeon.photoalbum.core.place.domain.PlaceRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

@Profile(Constant.PROFILE_DEVELOPMENT)
public interface JpaPlaceRepository extends PlaceRepository, JpaRepository<Place, String> {
}
