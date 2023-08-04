package ec.edu.insteclrg.sig_api.service.administracion;

import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ec.edu.insteclrg.sig_api.domain.administracion.GestionDocumentalConvenio;
import ec.edu.insteclrg.sig_api.dto.administracion.GestionDocumentalConvenioDTO;
import ec.edu.insteclrg.sig_api.persistence.administracion.GestionDocumentalConvenioPersistence;
import ec.edu.insteclrg.sig_api.service.GenericCrudServiceImpl;

@Service
public class GestionDocumentalConvenioService extends GenericCrudServiceImpl<GestionDocumentalConvenio, GestionDocumentalConvenioDTO>{
	
	@Autowired
	private GestionDocumentalConvenioPersistence repository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public Optional<GestionDocumentalConvenio> find(GestionDocumentalConvenioDTO dto) {
		return repository.findById(dto.getId());
	}

	@Override
	public GestionDocumentalConvenio mapToDomain(GestionDocumentalConvenioDTO dto) {
		return modelMapper.map(dto, GestionDocumentalConvenio.class);
	}

	@Override
	public GestionDocumentalConvenioDTO mapToDto(GestionDocumentalConvenio domain) {
		return modelMapper.map(domain, GestionDocumentalConvenioDTO.class);
	}

}
