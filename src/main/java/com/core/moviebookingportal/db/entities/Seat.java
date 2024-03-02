package com.core.moviebookingportal.db.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "seat")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "seatNo")
    private String seatNo;

    @Column(name="lock_expiration_time")
    private LocalDateTime lockExpirationTime;

    @Version
    private Long version;


    @Column(name = "is_booked")
    private boolean isBooked;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JsonIgnore
    @JoinColumn(name ="show_id")
    private Shows show;


}
