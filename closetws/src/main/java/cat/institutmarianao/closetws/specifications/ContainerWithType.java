package cat.institutmarianao.closetws.specifications;

import org.springframework.data.jpa.domain.Specification;

import cat.institutmarianao.closetws.model.Container;
import cat.institutmarianao.closetws.model.Container.Type;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ContainerWithType implements Specification<Container>{

	private static final long serialVersionUID = 1L;
	private Type type;

	public ContainerWithType(Type type) {
		super();
		this.type = type;
	}

	@Override
	public Predicate toPredicate(Root<Container> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if(type==null) return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
		return criteriaBuilder.equal(root.get("type"), type);
	}

}
