package cat.institutmarianao.closetws.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;

import cat.institutmarianao.closetws.exception.NotFoundException;
import cat.institutmarianao.closetws.model.Outfit;
import cat.institutmarianao.closetws.repositories.OutfitRepository;
import cat.institutmarianao.closetws.services.OutfitService;
import cat.institutmarianao.closetws.validation.groups.OnOutfitCreate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class OutfitServiceImpl implements OutfitService{
	
	@Autowired
	private OutfitRepository outfitRepository;

	@Override
	public List<Outfit> findAll(String owner, Date creationFrom, Date creationTo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Outfit getById(@Positive Long id) {
		return outfitRepository.findById(id).orElseThrow(NotFoundException::new);
	}

	@Override
	@Validated(OnOutfitCreate.class)
	public Outfit save(@NotNull @Valid Outfit outfit) {
		return outfitRepository.saveAndFlush(outfit);
	}

	@Override
	public Outfit update(@NotNull @Valid Outfit outfit) {
		Outfit dbOutfit = getById(outfit.getId());
		
		if (outfit.getName() != null) dbOutfit.setName(outfit.getName());
		if (outfit.getModificationDate() != null) dbOutfit.setModificationDate(outfit.getModificationDate());
		if (outfit.getClothes()!=null && !outfit.getClothes().isEmpty()) dbOutfit.setClothes(outfit.getClothes());

		return outfitRepository.saveAndFlush(outfit);
	}

	@Override
	public void deleteById(@Positive Long outfitId) {
		outfitRepository.deleteById(outfitId);
	}

}
