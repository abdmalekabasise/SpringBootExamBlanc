package com.example.abassisseabdmalek.Repository;

import com.example.abassisseabdmalek.Entity.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRepository extends JpaRepository<Parking,Integer> {
    public Parking findByAdresse(String adresse);
}
