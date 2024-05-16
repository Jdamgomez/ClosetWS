package cat.institutmarianao.closetws.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cat.institutmarianao.closetws.model.Clothes;
import cat.institutmarianao.closetws.model.Clothes.Category;
import cat.institutmarianao.closetws.model.Clothes.Collection;
import cat.institutmarianao.closetws.services.ClothesService;
import cat.institutmarianao.closetws.validation.groups.OnClothesCreate;
import cat.institutmarianao.closetws.validation.groups.OnClothesUpdate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

/* Swagger */
@Tag(name = "Clothes", description = "ClothesController API")
/**/
@RestController
@RequestMapping("/clothes")
@SecurityRequirement(name = "closeteapi")
@Validated
public class ClothesController {

	@Autowired
	private ClothesService clothesService;

	/* Swagger */
	@Operation(summary = "Find all clothes")
	@ApiResponse(responseCode = "200", content = {
			@Content(mediaType = "application/json",array= @ArraySchema(schema = @Schema(
					implementation = Clothes.class)))} , description = "Clothes retrieved ok")
	/**/
	@GetMapping(value = "/find/all")
	public List<Clothes> findAll( @RequestParam(value = "containerId", required = false) Long container,
			@RequestParam(value = "owner username", required = false) String owner,
			@RequestParam(value = "collection", required = false) Collection collection,
			@RequestParam(value = "category", required = false) Category category,
			@RequestParam(value = "from", required = false) Date from,
			@RequestParam(value = "to", required = false) Date to ){
		return clothesService.findAll(container, owner, collection, category, from, to);
	}

	/* Swagger */
	@Operation(summary = "Get clothes by id")
	@ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",schema = @Schema(implementation = Clothes.class))}, description = "Clothes retrieved ok")
	@ApiResponse(responseCode = "404", content = {@Content(mediaType = "application/json") }, description = "Resource not found")
	/**/
	@GetMapping("/get/by/clothesId/{clothesId}") 
	public Clothes findById(@PathVariable("clothesId") @Positive Long clothesId) {
		return clothesService.getById(clothesId);
	}

	/* Swagger */
	@Operation(summary = "Save a clothes")
	@io.swagger.v3.oas.annotations.parameters.RequestBody(content = {
			@Content(mediaType = "application/json",schema = @Schema(implementation = Clothes.class))})
	@ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",schema = @Schema(implementation = Clothes.class)) }, description = "Clothes saved ok")
	/**/
	@PostMapping("/save")
	@Validated(OnClothesCreate.class)
	public Clothes save(@RequestBody @Valid Clothes clothes) {
		return clothesService.save(clothes);
	}

	/* Swagger */
	@Operation(summary = "Update a clothes")
	@ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json") }, description = "Clothes updated ok")
	@ApiResponse(responseCode = "404", content = {
			@Content(mediaType = "application/json") }, description = "Resource not found")
	/**/
	@PutMapping("/update")
	@Validated(OnClothesUpdate.class)
	public Clothes update(@RequestBody @Valid Clothes clothes) {
		return clothesService.update(clothes);
	}
	
	/* Swagger */
	@Operation(summary = "Update the last use of a list of clothes")
	@ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json") }, description = "Clothes updated ok")
	@ApiResponse(responseCode = "404", content = {
			@Content(mediaType = "application/json") }, description = "Resource not found")
	/**/
	@PutMapping("/update/last-use")
	@Validated(OnClothesUpdate.class)
	public List<Clothes> updateLastUse(@RequestBody @Valid List<Clothes> clothesList) {
		return clothesService.updateLastUse(clothesList);
	}

	/* Swagger */
	@Operation(summary = "Delete a clothes")
	@ApiResponse(responseCode = "200", content = {
			@Content(mediaType = "application/json") }, description = "Clothes deleted ok")
	/**/
	@DeleteMapping("/delete/by/clothesId/{clothesId}")
	public void deleteById(@PathVariable("clothesId") @Positive Long clothesId) {
		clothesService.deleteById(clothesId);
	}
}
