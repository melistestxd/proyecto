/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package mx.edu.uteq.HolaMundo.controller;

import jakarta.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import mx.edu.uteq.HolaMundo.entity.OfertaEducativa;
import mx.edu.uteq.HolaMundo.entity.Profesores;
import mx.edu.uteq.HolaMundo.repository.ProfesoresRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ControladorProfesores {

    @Autowired
    private ProfesoresRepo repo;

    @RequestMapping("/profesores")
    public String page(Model model) {
        List<Profesores> profesores = repo.findAll();

        model.addAttribute("inicioClass", "");
        model.addAttribute("ofertaEducativaClass", "");
        model.addAttribute("admisionesClass", "");
        model.addAttribute("profesoresClass", "fw-bold");
        model.addAttribute("profesores", profesores);
        return "Profesores.html";
    }

    @GetMapping("/agregarProfesor")
    public String mostrarPaginaAgregarProfesor(Model model) {
        model.addAttribute("profesor", new Profesores());
        model.addAttribute("inicioClass", "");
        model.addAttribute("ofertaEducativaClass", "");
        model.addAttribute("admisionesClass", "");
        model.addAttribute("profesoresClass", "fw-bold");
        return "agregarProfesor";
    }

    @PostMapping("/guardar-profesor")
    public String guardarProfesor(@Valid @ModelAttribute("profesor") Profesores profesor, Errors errores) {
        if (errores.hasErrors()) {
            return "agregarProfesor"; //Regresa al formulario de agregar oferta si hay errores
        }

        repo.save(profesor);
        return "redirect:/profesores";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("/eliminarProfesor")
    public String eliminarProfesor(@RequestParam Long id) {
        repo.deleteById(id);
        return "redirect:/profesores";
    }

    @GetMapping("/ModificarProfesor")
    public String mostrarPaginaModificaProfesor(@RequestParam Long id, Model model) {
        Profesores profesor = repo.findById(id).orElse(null);

        if (profesor != null) {
            model.addAttribute("profesor", profesor);
            return "ModificarProfesor";
        } else {
            return "redirect:/profesores";
        }
    }

    @PostMapping("/guardar-profesor-modificado")
    public String guardarModificacion(@Valid @ModelAttribute("profesor") Profesores profesor, Errors errores) {
        if (errores.hasErrors()) {
            return "ModificarProfesor"; // Regresa al formulario de modificación si hay errores
        }

        repo.save(profesor);
        return "redirect:/profesores";
    }

}
