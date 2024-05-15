package cat.institutmarianao.closetws.services;

import java.util.Date;
import java.util.List;

import cat.institutmarianao.closetws.model.Outfit;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public interface OutfitService {
	
	List<Outfit> findAll(String owner, Date creationFrom, Date creationTo);

	Outfit getById(@Positive Long id);
	
	Outfit save(@NotNull @Valid Outfit outfit);
	
	Outfit update(@NotNull @Valid Outfit outfit);

	void deleteById(@Positive Long outfitId);
}
