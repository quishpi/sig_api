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
import ec.edu.insteclrg.sig_api.dto.PaisDTO;
import ec.edu.insteclrg.sig_api.service.crud.PaisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = { Constants.URI_API_PAIS })
@Tag(name = "Pais", description = "Gestiona Paises (ej. Ecuador, Colombia, etc.")
public class PaisController {

	@Autowired
	PaisService service;

	@Operation(summary = "Lista todos los países")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> listar() {
		List<PaisDTO> list = service.buscarTodo(new PaisDTO());
		if (!list.isEmpty()) {
			ApiResponseDTO<List<PaisDTO>> response = new ApiResponseDTO<>(true, list);
			return (new ResponseEntity<Object>(response, HttpStatus.OK));
		} else {
			return new ResponseEntity<>(new ApiResponseDTO<>(false, null), HttpStatus.NOT_FOUND);
		}
	}

	@Operation(summary = "Guarda un nuevo Pais")
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> guardar(@RequestBody PaisDTO PaisDTO) {
		PaisDTO PaisDTOResult = service.guardar(PaisDTO);
		return new ResponseEntity<>(new ApiResponseDTO<>(true, PaisDTOResult), HttpStatus.CREATED);
	}

	@Operation(summary = "Actualizar un Pais")
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> actualizar(@RequestBody PaisDTO PaisDTO) {
		PaisDTO resultDTO = service.actualizar(PaisDTO);
		return new ResponseEntity<>(new ApiResponseDTO<>(true, resultDTO), HttpStatus.CREATED);
	}

	@Operation(summary = "Recupera por código un Pais")
	@GetMapping(value = "{codigo}/archivo/codigo", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> buscarPorCodigo(@Valid @PathVariable("codigo") String codigo) {
		return new ResponseEntity<>(new ApiResponseDTO<>(true, service.buscarPorCodigo(codigo)), HttpStatus.OK);
	}

	@Operation(summary = "Recupera por id un Pais")
	@GetMapping(value = "{id}/archivo/id", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> buscarPorId(@Valid @PathVariable("id") long id) {
		PaisDTO dto = new PaisDTO();
		dto.setId(id);
		return new ResponseEntity<>(new ApiResponseDTO<>(true, service.buscar(dto)), HttpStatus.OK);
	}

}
