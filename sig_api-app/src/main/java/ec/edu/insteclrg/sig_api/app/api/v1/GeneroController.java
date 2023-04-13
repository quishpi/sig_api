package ec.edu.insteclrg.sig_api.app.api.v1;

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

import ec.edu.insteclrg.sig_api.app.common.Constants;
import ec.edu.insteclrg.sig_api.dto.ApiResponseDTO;
import ec.edu.insteclrg.sig_api.dto.GeneroDTO;
import ec.edu.insteclrg.sig_api.service.crud.GeneroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = { Constants.URI_API_GENERO })
@Tag(name = "Genero", description = "Gestiona generos (ej. Masculino, Femenino, etc.")

public class GeneroController {
	
	@Autowired
	private GeneroService service;
	
	@Operation(summary = "Lista todos los generos")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> listar() {
		List<GeneroDTO> list = service.buscarTodo(new GeneroDTO());
		if (!list.isEmpty()) {
			ApiResponseDTO<List<GeneroDTO>> response = new ApiResponseDTO<>(true, list);
			return (new ResponseEntity<Object>(response, HttpStatus.OK));
		} else {
			return new ResponseEntity<>(new ApiResponseDTO<>(false, null), HttpStatus.NOT_FOUND);
		}
	}

	@Operation(summary = "Guarda un nuevo genero")
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> guardar(@RequestBody GeneroDTO GeneroDTO) {
		GeneroDTO GeneroDTOResult = service.guardar(GeneroDTO);
		return new ResponseEntity<>(new ApiResponseDTO<>(true, GeneroDTOResult), HttpStatus.CREATED);
	}

	@Operation(summary = "Actualiza un genero")
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> actualizar(@RequestBody GeneroDTO GeneroDTO) {
		GeneroDTO resultDTO = service.actualizar(GeneroDTO);
		return new ResponseEntity<>(new ApiResponseDTO<>(true, resultDTO), HttpStatus.CREATED);
	}

	@Operation(summary = "Recupera por id un genero")
	@GetMapping(value = "{id}/archivo/id", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> buscarPorId(@Valid @PathVariable("id") long id) {
		GeneroDTO dto = new GeneroDTO();
		dto.setId(id);
		return new ResponseEntity<>(new ApiResponseDTO<>(true, service.buscar(dto)), HttpStatus.OK);
	}

	
	

}
