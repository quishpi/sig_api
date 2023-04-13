package ec.edu.insteclrg.sig_api.service.crud;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.insteclrg.sig_api.domain.TipoDiscapacidad;
import ec.edu.insteclrg.sig_api.dto.TipoDiscapacidadDTO;
import ec.edu.insteclrg.sig_api.persistence.TipoDiscapacidadPersistence;
import ec.edu.insteclrg.sig_api.service.GenericCrudServiceImpl;

@Service
public class TipoDiscapacidadService  extends GenericCrudServiceImpl<TipoDiscapacidad, TipoDiscapacidadDTO>{

	
	@Autowired
	private TipoDiscapacidadPersistence repository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public Optional<TipoDiscapacidad> buscar(TipoDiscapacidadDTO dto) {
		return repository.findById(dto.getId());
	}

	@Override
	public TipoDiscapacidad mapToDomain(TipoDiscapacidadDTO dto) {
		return modelMapper.map(dto, TipoDiscapacidad.class);
	}

	@Override
	public TipoDiscapacidadDTO mapToDto(TipoDiscapacidad domain) {
		return modelMapper.map(domain, TipoDiscapacidadDTO.class);
	}
	
	

}
