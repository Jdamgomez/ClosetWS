package cat.institutmarianao.closetws.specifications;

import org.springframework.data.jpa.domain.Specification;

import cat.institutmarianao.closetws.model.Clothes;
import cat.institutmarianao.closetws.model.Clothes.Category;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ClothesWithCategory implements Specification<Clothes>{

	private static final long serialVersionUID = 1L;
	private Category category;

	public ClothesWithCategory(Category category) {
		super();
		this.category = category;
	}

	@Override
	public Predicate toPredicate(Root<Clothes> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if (category == null) return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
		return criteriaBuilder.equal(root.get("category"), category);
	}

}
