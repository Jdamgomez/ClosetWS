package cat.institutmarianao.closetws.specifications;

import org.springframework.data.jpa.domain.Specification;

import cat.institutmarianao.closetws.model.Container;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ContainerWithOwner implements Specification<Container>{

	private static final long serialVersionUID = 1L;
	private String owner;

	public ContainerWithOwner(String owner) {
		super();
		this.owner = owner;
	}

	@Override
	public Predicate toPredicate(Root<Container> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if(owner==null) return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
		return criteriaBuilder.equal(root.get("owner").get("username"), owner);
	}

}
