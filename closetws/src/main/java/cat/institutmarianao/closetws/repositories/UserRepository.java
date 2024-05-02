package cat.institutmarianao.closetws.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import cat.institutmarianao.closetws.model.User;


public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
	
}
