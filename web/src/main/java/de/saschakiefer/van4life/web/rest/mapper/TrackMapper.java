package de.saschakiefer.van4life.web.rest.mapper;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

import de.saschakiefer.van4life.domain.entity.Track;
import de.saschakiefer.van4life.web.rest.dto.TrackCreationRequestDTO;
import de.saschakiefer.van4life.web.rest.dto.TrackResponseDTO;

public class TrackMapper {
	public static final TrackResponseDTO TrackToTrackResponseDto(Track track) {
		ModelMapper mapper = new ModelMapper();
		TrackResponseDTO trackResponseDTO = mapper.map(track, TrackResponseDTO.class);
		return trackResponseDTO;
	}

	public static Track TrackCreationRequestDTOToTrack(TrackCreationRequestDTO trackRequest) {
		ModelMapper mapper = new ModelMapper();

		Converter<String, Timestamp> stringTimestampConverter = new AbstractConverter<String, Timestamp>() {
			@Override
			protected Timestamp convert(String source) {
				LocalDateTime localDateTime = LocalDateTime.parse(source, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
				ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.of("UTC"));
				return Timestamp.valueOf(zonedDateTime.withZoneSameInstant(ZoneId.of("CET")).toLocalDateTime());
			}
		};
		mapper.addConverter(stringTimestampConverter);


		Track track = mapper.map(trackRequest, Track.class);

		return track;
	}
}
