/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package mx.edu.uteq.HolaMundo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@Slf4j
public class ControladorInicio {

    @GetMapping("/")
    public String page(Model model) {
        model.addAttribute("inicioClass", "fw-bold");
        model.addAttribute("ofertaEducativaClass", "");
        model.addAttribute("admisionesClass", "");
        
        return "menu";
    }
    
    @GetMapping("/infoDesSoft")
    public String infoDesSoft(Model model) {
        model.addAttribute("inicioClass", "fw-bold");
        model.addAttribute("ofertaEducativaClass", "");
        model.addAttribute("admisionesClass", "");
        
        return "infoDesSoft";
    }

}
