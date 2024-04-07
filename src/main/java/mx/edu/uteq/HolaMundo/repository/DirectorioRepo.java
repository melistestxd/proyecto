/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mx.edu.uteq.HolaMundo.repository;

import java.util.List;
import mx.edu.uteq.HolaMundo.entity.Directorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author aguil
 */
@Repository

public interface DirectorioRepo extends JpaRepository<Directorio, Long>{

    public List<Directorio> findByNombreContainingIgnoreCaseOrApellidosContainingIgnoreCaseOrCorreoContainingIgnoreCaseOrOcupacionContainingIgnoreCase(String query, String query0, String query1, String query2);
    
}
