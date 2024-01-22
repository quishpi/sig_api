package ec.edu.insteclrg.sig_api.service.general;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.insteclrg.sig_api.domain.general.TipoSangre;
import ec.edu.insteclrg.sig_api.dto.general.TipoSangreDTO;
import ec.edu.insteclrg.sig_api.persistence.general.TipoSangrePersistence;
import ec.edu.insteclrg.sig_api.service.GenericCrudServiceImpl;

@Service
public class TipoSangreService extends GenericCrudServiceImpl<TipoSangre,TipoSangreDTO>{
	
	@Autowired
	private TipoSangrePersistence repository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@Override
	public Optional<TipoSangre> find(TipoSangreDTO dto) {
		return repository.findById(dto.getId());
	}

	@Override
	public TipoSangre mapToDomain(TipoSangreDTO dto) {
		return modelMapper.map(dto, TipoSangre.class);
	}

	@Override
	public TipoSangreDTO mapToDto(TipoSangre domain) {
		return modelMapper.map(domain, TipoSangreDTO.class);
	}

}