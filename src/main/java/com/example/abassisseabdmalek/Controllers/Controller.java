package com.example.abassisseabdmalek.Controllers;

import com.example.abassisseabdmalek.Entity.Parking;
import com.example.abassisseabdmalek.Entity.Personnel;
import com.example.abassisseabdmalek.Entity.Zone;
import com.example.abassisseabdmalek.Repository.ParkingRepository;
import com.example.abassisseabdmalek.Repository.PersonnelRepository;
import com.example.abassisseabdmalek.Repository.ZoneRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("exam")
@AllArgsConstructor
public class Controller {
    @Autowired
    PersonnelRepository personnelRepository;
    @Autowired
    ParkingRepository parkingRepository;
    @Autowired
    ZoneRepository zoneRepository;

    @PostMapping("/ajouterPersonnel")
    public Personnel ajouterPersonnel(@RequestBody Personnel personnel){
    return personnelRepository.save(personnel);
}

    @PostMapping("/ajoutParkingetZones")
    public void ajoutParkingetZones(@RequestBody Parking parking){
        List<Zone> zones = parking.getZones();
        zones.stream().forEach(zone -> zone.setParking(parking));
        parking.setZones(parking.getZones());
        parkingRepository.save(parking);

        zoneRepository.saveAll(parking.getZones());
    }

    @PostMapping("/affecterPZ/{idzone}/{idGarde}")
    public void affecterPersonnelZone(@PathVariable int idzone,@PathVariable int idGarde){
        Zone zone = zoneRepository.findById(idzone).orElse(null);
        Personnel garde = personnelRepository.findById(idGarde).orElse(null);
        if(zone!=null && garde!=null){
           zone.getPersonnels().add(garde);
           zoneRepository.save(zone);

        }
    }

@PostMapping ("/getAllPersonnelByParking")
    public List<Personnel> getAllPersonnelByParking(@RequestBody Parking parking){
        Parking parking1 = parkingRepository.findById(parking.getId()).orElse(null);
         List<Zone> zones = parking1.getZones();
         List<Personnel> personnels=new ArrayList<Personnel>();
       // List<Personnel> personnels2=parking.getZones();

         zones.stream().forEach(zone -> zone.getPersonnels().forEach(personnel -> personnels.add(personnel)));
        return personnels;

    }

    @GetMapping("/{id}")
    public Parking parkings(@PathVariable int id){
        return parkingRepository.findById(id).orElse(null);
    }
    @GetMapping("/nombreParAdresse/{adresse}")
    public int getNbrGardeJour(@PathVariable String adresse ){
        Parking parking = parkingRepository.findByAdresse(adresse);
        if(parking!=null){
            List<Zone> zones = parking.getZones();
            List<Personnel> personnels=new ArrayList<Personnel>();
            zones.stream().forEach(zone -> zone.getPersonnels().forEach(personnel -> personnels.add(personnel)));
            personnels.stream().filter(personnel -> personnel.getPoste().equals("GARDE_JOUR"));
            return personnels.size();
        }
       return 0;
    }

    @GetMapping("/{start}/{end}")
    public List<Personnel> getPersonalByDate(Date start,Date end){
        return personnelRepository.findByDateDeRecrutementBetween(start,end);
    }




}
