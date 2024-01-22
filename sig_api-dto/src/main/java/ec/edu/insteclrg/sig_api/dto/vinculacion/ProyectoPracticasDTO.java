package ec.edu.insteclrg.sig_api.dto.vinculacion;

import ec.edu.insteclrg.sig_api.dto.general.PeriodoDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProyectoPracticasDTO {

	private Long id;
	private String urlPlanificacion;
	private PeriodoDTO periodo;
	private String horasPlanificadas;

	
}