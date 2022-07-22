package kr.sikugeon.photoalbum.core.place.application;

import kr.sikugeon.photoalbum.core.place.domain.Place;
import kr.sikugeon.photoalbum.core.place.domain.PlaceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlaceManager implements PlaceFinder {
    private PlaceRepository placeRepository;

    public PlaceManager(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Override
    public List<Place> getAll() {
        return placeRepository.findAll();
    }


}
