package com.weather.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String pincode;

    private Double latitude;
    private Double longitude;
} 