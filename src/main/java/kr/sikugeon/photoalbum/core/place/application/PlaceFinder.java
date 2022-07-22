package kr.sikugeon.photoalbum.core.place.application;

import kr.sikugeon.photoalbum.core.place.domain.Place;

import java.util.List;

public interface PlaceFinder {
    List<Place> getAll();
}
