package mx.edu.uteq.HolaMundo.controller;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import mx.edu.uteq.HolaMundo.entity.Admision;
import mx.edu.uteq.HolaMundo.entity.Directorio;
import mx.edu.uteq.HolaMundo.entity.OfertaEducativa;
import mx.edu.uteq.HolaMundo.entity.PreguntaFrecuente;
import mx.edu.uteq.HolaMundo.entity.Profesores;
import mx.edu.uteq.HolaMundo.repository.AdmisionRepo;
import mx.edu.uteq.HolaMundo.repository.DirectorioRepo;
import mx.edu.uteq.HolaMundo.repository.OfertaEducativaRepo;
import mx.edu.uteq.HolaMundo.repository.PreguntaFrecuenteRepo;
import mx.edu.uteq.HolaMundo.repository.ProfesoresRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class ControladorBuscador {

    @Autowired
    private PreguntaFrecuenteRepo preguntaFrecuenteRepository;

    @Autowired
    private OfertaEducativaRepo ofertaEducativaRepository;

    @Autowired
    private AdmisionRepo admisionRepo;

    @Autowired
    private DirectorioRepo directorioRepo;

    @Autowired
    private ProfesoresRepo profesoresRepo;

    @GetMapping("/buscar")
    public String buscar(@RequestParam(name = "query", required = false) String query, Model model) {
        List<PreguntaFrecuente> preguntas = preguntaFrecuenteRepository.findByPreguntaContainingIgnoreCase(query);
        List<OfertaEducativa> ofertas = ofertaEducativaRepository.findByOfertaEducativaContainingIgnoreCase(query);
        List<Admision> admisiones = admisionRepo.findByNombreAdmisionContainingIgnoreCase(query);
        List<Directorio> directorios = directorioRepo.findByNombreContainingIgnoreCaseOrApellidosContainingIgnoreCaseOrCorreoContainingIgnoreCaseOrOcupacionContainingIgnoreCase(query, query, query, query);
        List<Profesores> profesores = profesoresRepo.findByNombreContainingIgnoreCaseOrApellidosContainingIgnoreCaseOrCorreoContainingIgnoreCase(query, query, query);

        model.addAttribute("preguntas", preguntas);
        model.addAttribute("ofertas", ofertas);
        model.addAttribute("admisiones", admisiones);
        model.addAttribute("directorios", directorios);
        model.addAttribute("profesores", profesores);

        return "resultadosBusqueda";
    }
}
