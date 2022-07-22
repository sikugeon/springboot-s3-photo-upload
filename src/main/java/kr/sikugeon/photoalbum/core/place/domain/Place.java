package kr.sikugeon.photoalbum.core.place.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Place {
    @Id
    private String name;
    private String address;

    public static Place create(String name, String address) {
        Place place = new Place();
        place.setAddress(address);
        place.setName(name);
        return place;
    }
}
