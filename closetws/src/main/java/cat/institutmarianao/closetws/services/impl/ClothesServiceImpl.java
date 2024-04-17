package cat.institutmarianao.closetws.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import cat.institutmarianao.closetws.exception.NotFoundException;
import cat.institutmarianao.closetws.model.Clothes;
import cat.institutmarianao.closetws.model.Clothes.Category;
import cat.institutmarianao.closetws.model.Clothes.Collection;
import cat.institutmarianao.closetws.repositories.ClothesRepository;
import cat.institutmarianao.closetws.services.ClothesService;
import cat.institutmarianao.closetws.validation.groups.OnClothesCreate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class ClothesServiceImpl implements ClothesService{

	@Autowired
	private ClothesRepository clothesRepository;
	
	@Override
	public List<Clothes> findAll(Long container, Collection collection, Category category) {
		// TODO Auto-generated method stub
		return null;
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
	public Clothes update(@NotNull @Valid Clothes clothes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(@Positive Long clothesId) {
		// TODO Auto-generated method stub
		
	}

}
