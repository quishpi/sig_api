package ec.edu.insteclrg.sig_api.api.v1.administracion;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.insteclrg.sig_api.common.Constants;
import ec.edu.insteclrg.sig_api.dto.administracion.ApiResponseDTO;
import ec.edu.insteclrg.sig_api.dto.administracion.EntidadFormadoraDTO;
import ec.edu.insteclrg.sig_api.service.administracion.EntidadFormadoraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = { Constants.URI_API_ENTIDAD_FORMADORA })
@Tag(name = "EntidadFormadora", description = "Gestiona las entidades formadoras")
public class EntidadFormadoraController {
	@Autowired
	private EntidadFormadoraService service;
	
	@Operation(summary = "Listar las Entidades Formadoras")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> listar() {
		List<EntidadFormadoraDTO> list = service.findAll(new EntidadFormadoraDTO());
		if (!list.isEmpty()) { 
			ApiResponseDTO<List<EntidadFormadoraDTO>> response = new ApiResponseDTO<>(true, list);
			return (new ResponseEntity<Object>(response, HttpStatus.OK));
		} else {
			return new ResponseEntity<>(new ApiResponseDTO<>(false, null), HttpStatus.NOT_FOUND);
		}
	}

	@Operation(summary = "Guarda una nueva Entidad Formadora")
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> guardar(@RequestBody EntidadFormadoraDTO EntidadFormadoraDTO) {
		EntidadFormadoraDTO EntidadFormadoraDTOResult = service.save(EntidadFormadoraDTO);
		return new ResponseEntity<>(new ApiResponseDTO<>(true, EntidadFormadoraDTOResult), HttpStatus.CREATED);
	}

	@Operation(summary = "Actualiza una Entidad Formadora")
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> actualizar(@RequestBody EntidadFormadoraDTO EntidadFormadoraDTO) {
		EntidadFormadoraDTO resultDTO = service.update(EntidadFormadoraDTO);
		return new ResponseEntity<>(new ApiResponseDTO<>(true, resultDTO), HttpStatus.CREATED);
	
	}

	@Operation(summary = "Recupera por id Entidad Formadora")
	@GetMapping(value = "{id}/archivo/id", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> buscarPorId(@Valid @PathVariable("id") long id) {
		EntidadFormadoraDTO dto = new EntidadFormadoraDTO();
		dto.setId(id);
		return new ResponseEntity<>(new ApiResponseDTO<>(true, service.find(dto)), HttpStatus.OK);
	}
	@Operation(summary = "Eliminar por id certificado capacitacion")
	@DeleteMapping(value = "{id}/archivo/id", produces = { MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<Object> eliminar(@PathVariable Long id) {
		EntidadFormadoraDTO dto = new EntidadFormadoraDTO();
		dto.setId(id);
	 return new ResponseEntity<>(new ApiResponseDTO<>(true, service.find(dto)), HttpStatus.OK);
	
	}
}

