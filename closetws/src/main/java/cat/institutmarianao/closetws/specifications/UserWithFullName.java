package cat.institutmarianao.closetws.specifications;

import org.springframework.data.jpa.domain.Specification;

import cat.institutmarianao.closetws.model.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class UserWithFullName implements Specification<User> {

	private static final long serialVersionUID = 1L;
	private String fullName;

	public UserWithFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if (fullName == null) return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); 
		
		return criteriaBuilder.like(criteriaBuilder.lower(root.get("fullName")), "%" + fullName.toLowerCase() + "%");
	}

}
