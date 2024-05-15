package cat.institutmarianao.closetws.specifications;

import java.util.Date;

import org.springframework.data.jpa.domain.Specification;

import cat.institutmarianao.closetws.model.Outfit;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class OutfitWithDate implements Specification<Outfit>{

	private static final long serialVersionUID = 1L;
	private Date from;
	private Date to;

	public OutfitWithDate(Date from, Date to) {
		this.from = from;
		this.to = to;
	}

	@Override
	public Predicate toPredicate(Root<Outfit> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if (from == null && to == null) {
			return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
		} 
		
		if (from != null ) {
			if(to != null) return criteriaBuilder.between(root.get("creationDate"), from, to);
			
			return criteriaBuilder.greaterThanOrEqualTo(root.get("creationDate"), from); 
		}
		return criteriaBuilder.lessThanOrEqualTo(root.get("creationDate"), to); 
	}

}
