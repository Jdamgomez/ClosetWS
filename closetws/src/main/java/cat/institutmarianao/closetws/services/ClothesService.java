package cat.institutmarianao.closetws.services;

import java.util.Date;
import java.util.List;

import cat.institutmarianao.closetws.model.Clothes;
import cat.institutmarianao.closetws.model.Clothes.Category;
import cat.institutmarianao.closetws.model.Clothes.Collection;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public interface ClothesService {

	List<Clothes> findAll(Long container, String owner, Collection collection, Category category, Date from, Date to);

	Clothes getById(@Positive Long id);
	
	Clothes save(@NotNull @Valid Clothes clothes);
	
	Clothes update(@NotNull @Valid Clothes clothes);
	
	List<Clothes> updateLastUse(@NotNull @Valid @NotEmpty List<Clothes> clothesList);

	void deleteById(@Positive Long clothesId);
}
