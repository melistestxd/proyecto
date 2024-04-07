/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package mx.edu.uteq.HolaMundo.controller;

import jakarta.validation.Valid;
import mx.edu.uteq.HolaMundo.entity.Admision;
import mx.edu.uteq.HolaMundo.repository.AdmisionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Renata
 */
@Controller
public class ControladorAdmision {

    @Autowired
    private AdmisionRepo repo;

    @GetMapping("/admisiones")
    public String page(Model model) {
        model.addAttribute("inicioClass", "");
        model.addAttribute("ofertaEducativaClass", "");
        model.addAttribute("admisionesClass", "fw-bold");
        model.addAttribute("datos", repo.findAll());
        return "admisiones";
    }

    @GetMapping("/agregarAdmision")
    public String mostrarPaginaAgregarAdmision(Model model) {
        model.addAttribute("admision", new Admision());
        model.addAttribute("inicioClass", "");
        model.addAttribute("ofertaEducativaClass", "");
        model.addAttribute("admisionesClass", "fw-bold");
        return "agregarAdmision";
    }

    @PostMapping("/guardar-admision")
    public String guardarAdmision(@Valid @ModelAttribute("admision") Admision admision, Errors errores) {
        if(errores.hasErrors()){
            return "agregarAdmision";
        }
        repo.save(admision);
        return "redirect:/admisiones";
    }

    @GetMapping("/modificarAdmision")
    public String mostrarPaginaModificarAdmision(@RequestParam Long id, Model model) {
        Admision admision = repo.findById(id).orElse(null);

        if (admision != null) {
            model.addAttribute("admision", admision);
            return "modificarAdmision"; 
        } else {
            return "redirect:/admisiones";
        }
    }

    @PostMapping("/guardar-modificacion-admision")
    public String guardarModificacionAdmision(@Valid @ModelAttribute("admision") Admision admision, Errors errores) {
        if(errores.hasErrors()){
            return "modificarAdmision";
        }
        repo.save(admision);
        return "redirect:/admisiones";
    }

    @GetMapping("/eliminarAdmision")
    public String eliminarAdmision(@RequestParam Long id) {
        repo.deleteById(id);
        return "redirect:/admisiones";
    }

}
