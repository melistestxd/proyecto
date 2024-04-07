/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mx.edu.uteq.HolaMundo.repository;

import mx.edu.uteq.HolaMundo.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author aguil
 */
@Repository

public interface LoginRepo extends JpaRepository<Login, Long> {

    @Query("SELECT p FROM Login p WHERE p.correo = :email AND p.contraseña = :contraseña")
    Login findByCorreoAndContraseña(@Param("email") String email, @Param("contraseña") String contraseña);
    
        Login findByCorreo(String correo);


}
