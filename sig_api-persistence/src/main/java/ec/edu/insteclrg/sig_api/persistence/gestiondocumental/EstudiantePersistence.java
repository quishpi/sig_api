package ec.edu.insteclrg.sig_api.persistence.gestiondocumental;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.edu.insteclrg.sig_api.domain.gestiondocumental.Estudiante;

public interface EstudiantePersistence extends JpaRepository<Estudiante, Long>{

}