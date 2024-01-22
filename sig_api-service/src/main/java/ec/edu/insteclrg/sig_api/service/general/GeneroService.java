package ec.edu.insteclrg.sig_api.service.general;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.insteclrg.sig_api.domain.general.Genero;
import ec.edu.insteclrg.sig_api.dto.general.GeneroDTO;
import ec.edu.insteclrg.sig_api.persistence.general.GeneroPersistence;
import ec.edu.insteclrg.sig_api.service.GenericCrudServiceImpl;

@Service
public class GeneroService extends GenericCrudServiceImpl<Genero, GeneroDTO> {
	
	@Autowired
	private GeneroPersistence repository;
	
	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public Optional<Genero> find(GeneroDTO dto) {
		return repository.findById(dto.getId());
	}

	@Override
	public Genero mapToDomain(GeneroDTO dto) {
		return modelMapper.map(dto, Genero.class);
	}

	@Override
	public GeneroDTO mapToDto(Genero domain) {
		return modelMapper.map(domain, GeneroDTO.class);
	}

}