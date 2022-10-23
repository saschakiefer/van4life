package de.saschakiefer.van4life.domain.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.saschakiefer.van4life.domain.vo.GeoJson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "track")
public class Track extends BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "timestamp")
    Timestamp timestamp;

    @Column(name = "geojson")
    private String geojsonString;

    @Column(name = "gpx")
    private String gpx;

    public GeoJson asGeoJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(geojsonString, GeoJson.class);
        } catch (JsonProcessingException e) {
            return new GeoJson();
        }
    }
}
