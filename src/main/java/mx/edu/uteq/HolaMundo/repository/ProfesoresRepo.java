/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mx.edu.uteq.HolaMundo.repository;

import java.util.List;
import mx.edu.uteq.HolaMundo.entity.Profesores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author aguil
 */
@Repository

public interface ProfesoresRepo extends JpaRepository<Profesores, Long> {

    @Query("SELECT p FROM Profesores p WHERE p.correo = :correo")
    Profesores findByCorreo(@Param("correo") String correo);

    public List<Profesores> findByNombreContainingIgnoreCaseOrApellidosContainingIgnoreCaseOrCorreoContainingIgnoreCase(String query, String query0, String query1);

}
