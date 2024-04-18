package cat.institutmarianao.closetws.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import cat.institutmarianao.closetws.exception.NotFoundException;
import cat.institutmarianao.closetws.model.Container;
import cat.institutmarianao.closetws.model.Container.Type;
import cat.institutmarianao.closetws.repositories.ContainerRepository;
import cat.institutmarianao.closetws.services.ContainerService;
import cat.institutmarianao.closetws.specifications.ContainerWithOwner;
import cat.institutmarianao.closetws.specifications.ContainerWithType;
import cat.institutmarianao.closetws.validation.groups.OnContainerCreate;
import cat.institutmarianao.closetws.validation.groups.OnContainerUpdate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class ContainerServiceImpl implements ContainerService{
	
	@Autowired
	private ContainerRepository containerRepository;
	

	@Override
	public List<Container> findAll(String owner, Type type) {
		Specification<Container> spec= Specification.where(new ContainerWithOwner(owner))
				.and(new ContainerWithType(type));
		return containerRepository.findAll(spec);
	}

	@Override
	public Container getById(@Positive Long id) {
		return containerRepository.findById(id).orElseThrow(NotFoundException::new);
	}

	@Override
	public List<Container> findAllClosets(String owner) {
		Specification<Container> spec= Specification.where(new ContainerWithOwner(owner))
				.and(new ContainerWithType(Type.CLOSET));
		return containerRepository.findAll(spec);
	}

	@Override
	public List<Container> findAllSuitcases(String owner) {
		Specification<Container> spec= Specification.where(new ContainerWithOwner(owner))
				.and(new ContainerWithType(Type.SUITCASE));
		return containerRepository.findAll(spec);
	}

	@Override
	@Validated(OnContainerCreate.class)
	public Container save(@NotNull @Valid Container container) {
		return containerRepository.saveAndFlush(container);
	}

	@Override
	@Validated(OnContainerUpdate.class)
	public Container update(@NotNull @Valid Container container) {
		Container dbContainer= getById(container.getId());
		
		if(container.getName()!= null) dbContainer.setName(container.getName());
		
		return containerRepository.saveAndFlush(dbContainer);
	}

	@Override
	public void deleteById(@Positive Long containerId) {
		containerRepository.deleteById(containerId);
	}

}
