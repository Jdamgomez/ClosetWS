package cat.institutmarianao.closetws.services;

import java.util.List;

import cat.institutmarianao.closetws.model.Clothes;
import cat.institutmarianao.closetws.model.Clothes.Category;
import cat.institutmarianao.closetws.model.Clothes.Collection;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public interface ClothesService {

	List<Clothes> findAll(Long container, Collection collection, Category category);

	Clothes getById(@Positive Long id);
	
	Clothes save(@NotNull @Valid Clothes clothes);
	
	Clothes update(@NotNull @Valid Clothes clothes);

	void deleteById(@Positive Long clothesId);
}
