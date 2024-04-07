package mx.edu.uteq.HolaMundo.repository;

import mx.edu.uteq.HolaMundo.entity.Buzon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuzonRepo extends JpaRepository<Buzon, Long> {
    // Puedes agregar métodos personalizados si es necesario
}
