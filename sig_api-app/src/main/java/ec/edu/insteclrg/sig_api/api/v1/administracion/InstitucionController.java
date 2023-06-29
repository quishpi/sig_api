package ec.edu.insteclrg.sig_api.api.v1.administracion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.insteclrg.sig_api.common.Constants;
import ec.edu.insteclrg.sig_api.dto.administracion.ApiResponseDTO;
import ec.edu.insteclrg.sig_api.dto.administracion.InstitucionDTO;
import ec.edu.insteclrg.sig_api.service.administracion.InstitucionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = { Constants.URI_API_INSTITUCION })
@Tag(name = "Institucion", description = "Gestiona la institucion (ej. Solo puede ver una institucion)")
public class InstitucionController {

	@Autowired
	InstitucionService service;

	@Operation(summary = "Lista la institucion")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> listar() {
		List<InstitucionDTO> list = service.findAll(new InstitucionDTO());
		if (!list.isEmpty()) {
			ApiResponseDTO<List<InstitucionDTO>> response = new ApiResponseDTO<>(true, list);
			return (new ResponseEntity<Object>(response, HttpStatus.OK));
		} else {
			return new ResponseEntity<>(new ApiResponseDTO<>(false, null), HttpStatus.NOT_FOUND);
		}
	}

	@Operation(summary = "Guarda una nueva Institucion")
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> guardar(@RequestBody InstitucionDTO InstitucionDTO) {
		InstitucionDTO InstitucionDTOResult = service.save(InstitucionDTO);
		return new ResponseEntity<>(new ApiResponseDTO<>(true, InstitucionDTOResult), HttpStatus.CREATED);
	}

	@Operation(summary = "Actualiza la Institucion")
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> actualizar(@RequestBody InstitucionDTO InstitucionDTO) {
		InstitucionDTO resultDTO = service.update(InstitucionDTO);
		return new ResponseEntity<>(new ApiResponseDTO<>(true, resultDTO), HttpStatus.CREATED);
	}

	@Operation(summary = "Recupera por id una Institucion si existen varias")
	@GetMapping(value = "{id}/archivo/id", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> buscarPorId(@Valid @PathVariable("id") long id) {
		InstitucionDTO dto = new InstitucionDTO();
		dto.setId(id);
		return new ResponseEntity<>(new ApiResponseDTO<>(true, service.find(dto)), HttpStatus.OK);
	}
}