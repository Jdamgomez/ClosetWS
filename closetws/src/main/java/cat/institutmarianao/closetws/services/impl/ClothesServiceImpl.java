package cat.institutmarianao.closetws.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import cat.institutmarianao.closetws.exception.NotFoundException;
import cat.institutmarianao.closetws.model.Clothes;
import cat.institutmarianao.closetws.model.Clothes.Category;
import cat.institutmarianao.closetws.model.Clothes.Collection;
import cat.institutmarianao.closetws.repositories.ClothesRepository;
import cat.institutmarianao.closetws.services.ClothesService;
import cat.institutmarianao.closetws.specifications.ClothesWithCategory;
import cat.institutmarianao.closetws.specifications.ClothesWithCollection;
import cat.institutmarianao.closetws.specifications.ClothesWithContainer;
import cat.institutmarianao.closetws.specifications.ClothesWithDate;
import cat.institutmarianao.closetws.specifications.ClothesWithOwner;
import cat.institutmarianao.closetws.validation.groups.OnClothesCreate;
import cat.institutmarianao.closetws.validation.groups.OnClothesUpdate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class ClothesServiceImpl implements ClothesService{

	@Autowired
	private ClothesRepository clothesRepository;
	
	@Override
	public List<Clothes> findAll(Long container, String owner, Collection collection, Category category, Date from, Date to) {
		Specification<Clothes> spec= Specification.where(new ClothesWithContainer(container))
						.and(new ClothesWithOwner(owner)).and(new ClothesWithCollection(collection))
						.and(new ClothesWithCategory(category)).and(new ClothesWithDate(from, to));
		return clothesRepository.findAll(spec);
	}

	@Override
	public Clothes getById(@Positive Long id) {
		return clothesRepository.findById(id).orElseThrow(NotFoundException::new);
	}

	@Override
	@Validated(OnClothesCreate.class)
	public Clothes save(@NotNull @Valid Clothes clothes) {
		return clothesRepository.saveAndFlush(clothes);
	}

	@Override
	@Validated(OnClothesUpdate.class)
	public Clothes update(@NotNull @Valid Clothes clothes) {
		Clothes dbClothes = getById(clothes.getId());
		
		if (clothes.getName() != null) dbClothes.setName(clothes.getName());
		if (clothes.getColor() != null) dbClothes.setColor(clothes.getColor());
		if (clothes.getSize() != null) dbClothes.setSize(clothes.getSize());
		if (clothes.getPicture() != null) dbClothes.setPicture(clothes.getPicture());
		if (clothes.getCollection() != null) dbClothes.setCollection(clothes.getCollection());
		if (clothes.getCategory() != null) dbClothes.setCategory(clothes.getCategory());
		if (clothes.getContainer() != null) dbClothes.setContainer(clothes.getContainer());
		if (clothes.getLastUse() != null) dbClothes.setLastUse(clothes.getLastUse());

		return clothesRepository.saveAndFlush(dbClothes);
	}

	@Override
	@Validated(OnClothesUpdate.class)
	public List<Clothes> updateLastUse(@NotNull @Valid @NotEmpty List<Clothes> clothesList) {
		List<Clothes> dbClothesList= new ArrayList<>();
		for (Clothes clothes : clothesList) {
			Clothes dbClothes = getById(clothes.getId());
			if (clothes.getLastUse() != null) dbClothes.setLastUse(clothes.getLastUse());
			dbClothesList.add(dbClothes);
		}
		return clothesRepository.saveAllAndFlush(dbClothesList);
	}
	
	@Override
	public void deleteById(@Positive Long clothesId) {
		clothesRepository.deleteById(clothesId);
	}
}
