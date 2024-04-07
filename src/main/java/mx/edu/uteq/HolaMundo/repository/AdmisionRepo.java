/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package mx.edu.uteq.HolaMundo.repository;

import java.util.List;
import mx.edu.uteq.HolaMundo.entity.Admision;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Renata
 */
public interface AdmisionRepo extends CrudRepository<Admision, Long> {

    public List<Admision> findByNombreAdmisionContainingIgnoreCase(String query);
    
}
