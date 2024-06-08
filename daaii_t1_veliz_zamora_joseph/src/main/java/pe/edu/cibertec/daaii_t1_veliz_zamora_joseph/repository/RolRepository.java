package pe.edu.cibertec.daaii_t1_veliz_zamora_joseph.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.daaii_t1_veliz_zamora_joseph.model.db.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    Rol findByNomrol(String nomrol);
}
