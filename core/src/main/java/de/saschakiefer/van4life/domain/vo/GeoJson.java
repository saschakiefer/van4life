package de.saschakiefer.van4life.domain.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class GeoJson {
    String type;

    List<List<Double>> coordinates;
}
