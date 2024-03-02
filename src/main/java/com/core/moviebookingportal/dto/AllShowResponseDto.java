package com.core.moviebookingportal.dto;


import com.core.moviebookingportal.db.entities.Shows;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
public class AllShowResponseDto {

    private long id;

    private String screenName;

    private String startTime;

    private String duration;

    private String movieName;

    private String theatreName;

    public AllShowResponseDto(Shows show) {
        this.id = show.getId();
        this.screenName = show.getScreenName();
        this.startTime = convertEpochMilliToLocalDateTime(show.getStartTime());
        this.duration = convertEpochSecondsToHour(show.getDuration());
        this.movieName = show.getMovieName();
        this.theatreName = show.getTheatre().getTheatreName();
    }

    private String convertEpochSecondsToHour(Long duration) {
        return LocalTime.MIN.plusSeconds(duration).toString();
    }


    public String convertEpochMilliToLocalDateTime(Long startTime) {
        Instant instant = Instant.ofEpochMilli(startTime);
        LocalDateTime localDateTime = instant.atZone(ZoneId.of("Asia/Kolkata")).toLocalDateTime();
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
    }
}
