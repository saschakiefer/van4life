package de.saschakiefer.van4life.web.rest.mapper;

import de.saschakiefer.van4life.domain.entity.Track;
import de.saschakiefer.van4life.web.rest.dto.TrackCreationRequestDTO;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class TrackMapperTest {

    @Test
    void trackToTrackResponseDto() {
    }

    @Test
    void trackCreationRequestDTOToTrack() {
        TrackCreationRequestDTO trackCreationRequestDTO = new TrackCreationRequestDTO("TestName", "2022-09-05T08:57:21+00:00", "geoJson", "gpx");

        Track track = TrackMapper.TrackCreationRequestDTOToTrack(trackCreationRequestDTO);

        assertThat(track.getName(), is("TestName"));
        assertThat(track.getGeojsonString(), is("geoJson"));
        assertThat(track.getGpx(), is("gpx"));
        assertThat(track.getTimestamp().toString(), is("2022-09-05 10:57:21.0"));
    }
}
