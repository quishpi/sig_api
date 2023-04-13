package ec.edu.insteclrg.sig_api.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@MappedSuperclass
@Getter
@Setter
@ToString
public class EstadoCivilDTO {
	
	private Long id;
	
	private String descripcion;

}
