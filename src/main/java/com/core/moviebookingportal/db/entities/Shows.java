package com.core.moviebookingportal.db.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shows")
public class Shows {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "screen_name")
    private String screenName;

    @Column(name = "start_time")
    private Long startTime;

    @Column(name = "duration")
    private Long duration;

    @Column(name = "movie_name")
    private String movieName;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "theatre_id")
    private Theatre theatre;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "show", cascade = CascadeType.MERGE)
    @JsonIgnore
    private List<Booking> bookingList;


    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "show", cascade = CascadeType.MERGE)
    @JsonIgnore
    private List<Seat> seatList;

}
