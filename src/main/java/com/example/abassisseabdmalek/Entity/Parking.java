package com.example.abassisseabdmalek.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Parking {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String designation;
    private String adresse;
    private int capacite;
    @OneToMany(mappedBy = "parking")
    private List<Zone> zones;
}
