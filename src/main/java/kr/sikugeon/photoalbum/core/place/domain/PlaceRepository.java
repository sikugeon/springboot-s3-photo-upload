package kr.sikugeon.photoalbum.core.place.domain;

import java.util.List;

public interface PlaceRepository {
    List<Place> findAll();
    Place save(Place place);
}
