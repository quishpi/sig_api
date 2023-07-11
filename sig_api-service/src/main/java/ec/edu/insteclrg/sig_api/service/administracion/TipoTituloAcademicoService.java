package ec.edu.insteclrg.sig_api.service.administracion;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.insteclrg.sig_api.domain.administracion.TipoTituloAcademico;
import ec.edu.insteclrg.sig_api.dto.administracion.TipoTituloAcademicoDTO;
import ec.edu.insteclrg.sig_api.persistence.administracion.TipoTituloAcademicoPersistence;
import ec.edu.insteclrg.sig_api.service.GenericCrudServiceImpl;

@Service
public class TipoTituloAcademicoService extends GenericCrudServiceImpl<TipoTituloAcademico, TipoTituloAcademicoDTO> {

	@Autowired
	private TipoTituloAcademicoPersistence repository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public Optional<TipoTituloAcademico> find(TipoTituloAcademicoDTO dto) {
		return repository.findById(dto.getId());
	}

	@Override
	public TipoTituloAcademico mapToDomain(TipoTituloAcademicoDTO dto) {
		return modelMapper.map(dto, TipoTituloAcademico.class);
	}

	@Override
	public TipoTituloAcademicoDTO mapToDto(TipoTituloAcademico domain) {
		return modelMapper.map(domain, TipoTituloAcademicoDTO.class);
	}

}
