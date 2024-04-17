package cat.institutmarianao.closetws.specifications;

import org.springframework.data.jpa.domain.Specification;

import cat.institutmarianao.closetws.model.Clothes;
import cat.institutmarianao.closetws.model.Clothes.Collection;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ClothesWithCollection implements Specification<Clothes>{

	private static final long serialVersionUID = 1L;
	private Collection collection;

	public ClothesWithCollection(Collection collection) {
		super();
		this.collection = collection;
	}

	@Override
	public Predicate toPredicate(Root<Clothes> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if (collection == null) return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
		return criteriaBuilder.equal(root.get("collection"), collection);
	}

}
