package com.example.abassisseabdmalek.Repository;

import com.example.abassisseabdmalek.Entity.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PersonnelRepository extends JpaRepository<Personnel,Integer> {
public List<Personnel> findByDateDeRecrutementBetween(Date start,Date end);
}
