package ec.edu.insteclrg.sig_api.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ec.edu.insteclrg.sig_api.domain.Institucion;

@Repository
public interface InstitucionPersistence extends JpaRepository<Institucion, Long> {

}
