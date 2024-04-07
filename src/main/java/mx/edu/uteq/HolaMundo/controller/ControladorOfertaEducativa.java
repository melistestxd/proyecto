/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package mx.edu.uteq.HolaMundo.controller;

import jakarta.validation.Valid;
import java.util.List;
import mx.edu.uteq.HolaMundo.entity.OfertaEducativa;
import mx.edu.uteq.HolaMundo.repository.OfertaEducativaRepo;
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
public class ControladorOfertaEducativa {

    @Autowired
    private OfertaEducativaRepo repo;

    @GetMapping("/ofertaeducativa")
    public String paginaOfertaEducativa(Model model) {
        List<OfertaEducativa> carreras = repo.findAll();
        model.addAttribute("inicioClass", "");
        model.addAttribute("ofertaEducativaClass", "fw-bold");
        model.addAttribute("admisionesClass", "");
        model.addAttribute("carreras", carreras);
        return "ofertaeducativa";
    }

    @GetMapping("/agregarOferta")
    public String mostrarPaginaAgregarOferta(Model model) {
        model.addAttribute("oferta", new OfertaEducativa());
        model.addAttribute("inicioClass", "");
        model.addAttribute("ofertaEducativaClass", "fw-bold");
        model.addAttribute("admisionesClass", "");
        return "agregarOferta"; 
    }

    @PostMapping("/guardar-oferta")
    public String guardarOferta(@Valid @ModelAttribute("oferta") OfertaEducativa oferta, Errors errores) {
        if(errores.hasErrors()){
            return "agregarOferta"; //Regresa al formulario de agregar oferta si hay errores
        }
        
        repo.save(oferta);
        return "redirect:/ofertaeducativa";
    }

    @GetMapping("/modifOferta")
    public String mostrarPaginaModificarOferta(@RequestParam Long id, Model model) {
        OfertaEducativa oferta = repo.findById(id).orElse(null);

        if (oferta != null) {
            model.addAttribute("oferta", oferta);
            return "modificarOferta"; 
        } else {
            return "redirect:/ofertaeducativa";
        }
    }

    @PostMapping("/guardar-modificacion")
    public String guardarModificacion(@Valid @ModelAttribute("oferta") OfertaEducativa oferta, Errors errores) {
        if(errores.hasErrors()){
            return "agregarOferta";
        }
        repo.save(oferta);
        return "redirect:/ofertaeducativa";
    }

    @GetMapping("/eliminarOferta")
    public String eliminarOferta(@RequestParam Long id) {
        repo.deleteById(id);
        return "redirect:/ofertaeducativa";
    }

}
