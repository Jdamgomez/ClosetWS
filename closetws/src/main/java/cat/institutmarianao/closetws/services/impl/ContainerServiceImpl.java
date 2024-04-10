package cat.institutmarianao.closetws.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.annotation.Validated;

import cat.institutmarianao.closetws.model.Container;
import cat.institutmarianao.closetws.model.Container.Type;
import cat.institutmarianao.closetws.repositories.ContainerRepository;
import cat.institutmarianao.closetws.services.ContainerService;
import cat.institutmarianao.closetws.validation.groups.OnUserUpdate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ContainerServiceImpl implements ContainerService{
	
	@Autowired
	private ContainerRepository containerRepository;
	
	@Autowired
	private MessageSource messageSource;

	@Override
	public List<Container> findAll(String owner, Type type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Container getById(@Positive Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Container> findAllClosets() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Container> findAllSuitcases() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Container save(@NotNull @Valid Container container) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Validated(OnUserUpdate.class)
	public Container update(@NotNull @Valid Container container) {
		Container dbContainer= getById(container.getId());
		
		if(container.getName()!=null) dbContainer.setName(container.getName());
		
		return containerRepository.saveAndFlush(dbContainer);
	}

	@Override
	public void deleteById(@Positive Long containerId) {
		containerRepository.deleteById(containerId);
	}

}
