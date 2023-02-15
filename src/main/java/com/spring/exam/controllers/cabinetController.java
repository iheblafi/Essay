package com.spring.exam.controllers;

import com.spring.exam.Services.IcabinetService;
import com.spring.exam.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@Controller
@RestController
@RequestMapping("/Controller")
public class cabinetController {

    private IcabinetService cabinetService;

    @Autowired
    public cabinetController(@Qualifier("cabinet") IcabinetService cliniqueService){this.cabinetService = cliniqueService;}

    @PostMapping("/addClinique")
    public Clinique addClinique ( @RequestBody Clinique clinique){  return this.cabinetService.Insert( clinique);}

    @PostMapping("/addMedecinAndAssignToClinique/{cliniqueld}")
    public Medecin addMedecinAndAssignToClinique (@RequestBody  Medecin medecin, @PathVariable Long cliniqueld){
        return     this.cabinetService.addMedecinAndAssignToClinique ( medecin,  cliniqueld);
    }


    @PostMapping("/addPatient")
    public Patient addPatient (@RequestBody Patient patient){  return this.cabinetService.addPatient( patient);}



    @PostMapping("/addRDVAndAssignMedAndPatient/{idMedecin}/{idPatient}")
    public void addRDVAndAssignMedAndPatient(@RequestBody Rendezvous rdv,@PathVariable  Long idMedecin, @PathVariable Long idPatient)
    {    this.cabinetService.addRDVAndAssignMedAndPatient( rdv,  idMedecin,  idPatient); }


    @GetMapping("/addRDVAndAssignMedAndPatient/{cliniqueld}/{specialite}")
    List< Rendezvous > getRendevousByCliniqueAndSpeciallte(@PathVariable Long cliniqueld , @PathVariable Specialite specialite){
       return  this.cabinetService.getRendevousByCliniqueAndSpeciallte(  cliniqueld ,  specialite);
    }

    @GetMapping("/getNbrRendezVousMedecin/{idMedecin}")
    int getNbrRendezVousMedecin(@PathVariable Long idMedecin){
        return  this.cabinetService.getNbrRendezVousMedecin( idMedecin);}



    @GetMapping("/getRevenuMedecin/{startDate}/{endDate}/{idMedcin}")
    int getRevenuMedecin(@PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                         @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                         @PathVariable("idMedcin") long idMedcin)
    {  return  this.cabinetService. getRevenuMedecin(  idMedcin ,  startDate,  endDate); }
}
















       /* SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = sdformat.parse(startDate);
        Date d2 = sdformat.parse(endDate);
        return contratService. nbContratsValides( d1,  d2);*/