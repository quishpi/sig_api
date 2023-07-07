package ec.edu.insteclrg.sig_api.service.administracion;

import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ec.edu.insteclrg.sig_api.domain.administracion.GestionDocumental;
import ec.edu.insteclrg.sig_api.dto.administracion.GestionDocumentalDTO;
import ec.edu.insteclrg.sig_api.persistence.administracion.GestionDocumentalPersistence;
import ec.edu.insteclrg.sig_api.service.GenericCrudServiceImpl;

@Service

public class GestionDocumentalService extends GenericCrudServiceImpl<GestionDocumental, GestionDocumentalDTO>{
	
	@Autowired
	private GestionDocumentalPersistence repository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public Optional<GestionDocumental> find(GestionDocumentalDTO dto) {
		return repository.findById(dto.getId());
	}

	@Override
	public GestionDocumental mapToDomain(GestionDocumentalDTO dto) {
		return modelMapper.map(dto, GestionDocumental.class);
	}

	@Override
	public GestionDocumentalDTO mapToDto(GestionDocumental domain) {
		return modelMapper.map(domain, GestionDocumentalDTO.class);
	}

}