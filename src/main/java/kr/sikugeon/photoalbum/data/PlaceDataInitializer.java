package kr.sikugeon.photoalbum.data;//package kr.sikugeon.photoalbum.data;

import kr.sikugeon.photoalbum.core.place.domain.Place;
import kr.sikugeon.photoalbum.core.place.domain.PlaceRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@ConditionalOnProperty(name = "reason.data.initialize", havingValue = "true")
public class PlaceDataInitializer implements InitializingBean, ApplicationRunner, CommandLineRunner {

    private final PlaceRepository placeRepository;

    public PlaceDataInitializer(PlaceRepository placeRepository) {
        this.placeRepository = Objects.requireNonNull(placeRepository);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        placeRepository.save(Place.create("맛집", "서울시"));
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        placeRepository.save(Place.create("맛집1", "서울시"));
    }

    @Override
    public void run(String... args) throws Exception {
        placeRepository.save(Place.create("맛집2", "서울시"));
    }

}
