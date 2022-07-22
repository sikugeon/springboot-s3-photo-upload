package kr.sikugeon.photoalbum.web;

import kr.sikugeon.photoalbum.core.place.application.PlaceFinder;
import kr.sikugeon.photoalbum.core.place.domain.Place;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RolesAllowed("ROLE_USER")
public class PlaceRestController {

    private final PlaceFinder finder;

    public PlaceRestController(PlaceFinder placeFinder){
        this.finder=placeFinder;
    }

    @GetMapping("/api/places")
    public Iterable<Place> places() {
        return finder.getAll();
    }
}
