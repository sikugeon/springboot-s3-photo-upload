package kr.sikugeon.photoalbum.core.place.infrastructure;//package kr.sikugeon.photoalbum.core.place.infrastructure;
//
//import kr.sikugeon.photoalbum.Constant;
//import kr.sikugeon.photoalbum.core.place.domain.Place;
//import kr.sikugeon.photoalbum.core.place.domain.PlaceRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Repository;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.Objects;
//import java.util.Optional;
//import java.util.concurrent.CopyOnWriteArrayList;
//
///**
// * 메모리 기반 사용자 저장소 구현체
// *
// * @author springrunner.kr@gmail.com
// */
//@Profile(Constant.PROFILE_PRODUCTION)
//@Repository
//public class InMemoryPlaceRepository implements PlaceRepository, ApplicationRunner {
//
//    private final List<Place> places = new CopyOnWriteArrayList<>();
//    private final Logger log = LoggerFactory.getLogger(InMemoryPlaceRepository.class);
//
//    public InMemoryPlaceRepository() {
//    }
//
//
//    @Override
//    public List<Place> findAll() {
//        return Collections.unmodifiableList(places);
//    }
//
//    @Override
//    public Optional<Place> findByPlacename(String name) {
//        return places.stream()
//                .filter(place -> Objects.equals(place.getName(), name))
//                .findAny();
//    }
//
//    @Override
//    public Place save(Place place) {
//        places.add(place);
//        return place;
//    }
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
////        save(new Place("user", ));
////        log.info("신규 사용자를 등록합니다. (username: user, password: password)");
//    }
//
//}
