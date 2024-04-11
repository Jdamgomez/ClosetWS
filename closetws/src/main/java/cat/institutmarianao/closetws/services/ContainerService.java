package cat.institutmarianao.closetws.services;

import java.util.List;

import cat.institutmarianao.closetws.model.Container;
import cat.institutmarianao.closetws.model.Container.Type;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public interface ContainerService {
	
	List<Container> findAll(String owner, Type type);

	Container getById(@Positive Long id);
	
	List<Container> findAllClosets(String owner);
	
	List<Container> findAllSuitcases(String owner);
		
	Container save(@NotNull @Valid Container container);
	
	Container update(@NotNull @Valid Container container);

	void deleteById(@Positive Long containerId);
}
