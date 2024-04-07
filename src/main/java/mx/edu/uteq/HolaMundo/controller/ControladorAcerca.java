package mx.edu.uteq.HolaMundo.controller;

import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import mx.edu.uteq.HolaMundo.entity.Buzon;
import mx.edu.uteq.HolaMundo.entity.PreguntaFrecuente;
import mx.edu.uteq.HolaMundo.repository.BuzonRepo;
import mx.edu.uteq.HolaMundo.repository.PreguntaFrecuenteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class ControladorAcerca {

    @Autowired
    private PreguntaFrecuenteRepo preguntaFrecuenteRepository;

    @Autowired
    private BuzonRepo buzonRepo;

    @GetMapping("/acerca")
    public String page(Model model) {
        // Obtener todas las preguntas frecuentes de la base de datos
        Iterable<PreguntaFrecuente> preguntasFrecuentes = preguntaFrecuenteRepository.findAll();

        // Agregar preguntas frecuentes al modelo
        model.addAttribute("preguntasFrecuentes", preguntasFrecuentes);

        return "acerca";
    }

    @GetMapping("/preguntas-frecuentes")
    public String preguntasFrecuentes(Model model) {
        // Obtener todas las preguntas frecuentes de la base de datos
        List<PreguntaFrecuente> preguntasFrecuentes = (List<PreguntaFrecuente>) preguntaFrecuenteRepository.findAll();

        // Agregar preguntas frecuentes al modelo
        model.addAttribute("preguntasFrecuentes", preguntasFrecuentes);

        // También puedes establecer otros atributos necesarios para esta página
        // model.addAttribute("otroAtributo", valor);
        return "preguntas-frecuentes"; // Nombre del archivo HTML/Thymeleaf
    }

    @PostMapping("/enviarPregunta")
    public String enviarPregunta(@RequestParam String nombre, @RequestParam String pregunta) {
        Buzon nuevaPregunta = new Buzon();
        nuevaPregunta.setNombre(nombre);
        nuevaPregunta.setPregunta(pregunta);
        nuevaPregunta.setFecha(LocalDateTime.now());

        buzonRepo.save(nuevaPregunta);
        return "redirect:/acerca";
    }

    @GetMapping("/verBuzon")
    public String verBuzon(Model model) {
        List<Buzon> preguntasEnBuzon = buzonRepo.findAll();
        model.addAttribute("preguntasEnBuzon", preguntasEnBuzon);
        return "verBuzon";
    }
}
