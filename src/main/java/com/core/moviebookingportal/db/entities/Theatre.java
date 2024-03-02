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
@Table(name = "theatre")
public class Theatre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "theatre_name")
    private String theatreName;

    @Column(name = "city")
    private String city;

    @Setter(AccessLevel.NONE)
    @JsonIgnore
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "theatre")
    private List<Shows> showsList;

}
