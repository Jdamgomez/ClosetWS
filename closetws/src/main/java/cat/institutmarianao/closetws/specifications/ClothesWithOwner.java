package cat.institutmarianao.closetws.specifications;

import org.springframework.data.jpa.domain.Specification;

import cat.institutmarianao.closetws.model.Clothes;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ClothesWithOwner implements Specification<Clothes>{

	private static final long serialVersionUID = 1L;
	private String owner;

	public ClothesWithOwner(String owner) {
		super();
		this.owner = owner;
	}

	@Override
	public Predicate toPredicate(Root<Clothes> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if(owner==null) return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
		return criteriaBuilder.equal(root.get("container").get("owner").get("username"), owner);
	}

}
