package cat.institutmarianao.closetws.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cat.institutmarianao.closetws.model.Container;

public interface ContainerRepository extends JpaRepository<Container, Long>, JpaSpecificationExecutor<Container>{

}
