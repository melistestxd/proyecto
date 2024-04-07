/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package mx.edu.uteq.HolaMundo.controller;

import jakarta.validation.Valid;
import java.util.List;
import mx.edu.uteq.HolaMundo.entity.Directorio;
import mx.edu.uteq.HolaMundo.repository.DirectorioRepo;
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
 * @author aguil
 */
@Controller
public class ControladorDirectorio {

    @Autowired
    private DirectorioRepo repo;

    @RequestMapping("/Directorio")
    public String page(Model model) {
        List<Directorio> directorios = repo.findAll();

        model.addAttribute("inicioClass", "");
        model.addAttribute("ofertaEducativaClass", "");
        model.addAttribute("admisionesClass", "");
        model.addAttribute("profesoresClass", "");
        model.addAttribute("DirectorioClass", "fw-bold");

        model.addAttribute("directorios", directorios);
        return "Directorio.html";
    }
    
        @GetMapping("/agregarDirectorio")
    public String mostrarPaginaAgregarProfesor(Model model) {
        model.addAttribute("directorio", new Directorio());
        
        model.addAttribute("inicioClass", "");
        model.addAttribute("ofertaEducativaClass", "");
        model.addAttribute("admisionesClass", "");
        model.addAttribute("profesoresClass", "");
        model.addAttribute("DirectorioClass", "fw-bold");

        return "agregarDirectorio";
    }
    
    @PostMapping("/guardar-directorio")
    public String guardarProfesor(@Valid @ModelAttribute("directorio") Directorio directorio, Errors errores) {
        if (errores.hasErrors()) {
            return "agregarDirectorio"; //Regresa al formulario de agregar oferta si hay errores
        }

        repo.save(directorio);
        return "redirect:/Directorio";
    }
    
        @GetMapping("/eliminarDirectorio")
    public String eliminarDirectorio(@RequestParam Long id) {
        repo.deleteById(id);
        return "redirect:/Directorio";
    }
    
        @GetMapping("/ModificarDirectorio")
    public String mostrarPaginaModificaProfesor(@RequestParam Long id, Model model) {
        Directorio directorio = repo.findById(id).orElse(null);

        if (directorio != null) {
            model.addAttribute("directorio", directorio);
            return "ModificarDirectorio";
        } else {
            return "redirect:/Directorio";
        }
    }
    
        @PostMapping("/guardar-directorio-modificado")
    public String guardarModificacion(@Valid @ModelAttribute("directorio") Directorio directorio, Errors errores) {
        if (errores.hasErrors()) {
            return "ModificarDirectorio"; // Regresa al formulario de modificación si hay errores
        }

        repo.save(directorio);
        return "redirect:/Directorio";
    }

}
