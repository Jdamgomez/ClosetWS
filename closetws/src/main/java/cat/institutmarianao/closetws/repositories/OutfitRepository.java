package cat.institutmarianao.closetws.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cat.institutmarianao.closetws.model.Outfit;

public interface OutfitRepository extends JpaRepository<Outfit, Long>, JpaSpecificationExecutor<Outfit>{

}
