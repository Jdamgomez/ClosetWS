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

import cat.institutmarianao.closetws.model.Outfit;
import cat.institutmarianao.closetws.services.OutfitService;
import cat.institutmarianao.closetws.validation.groups.OnOutfitCreate;
import cat.institutmarianao.closetws.validation.groups.OnOutfitUpdate;
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
@Tag(name = "Outfit", description = "OutfitController API")
/**/
@RestController
@RequestMapping("/outfits")
@SecurityRequirement(name = "closeteapi")
@Validated
public class OutfitController {
	
	@Autowired
	private OutfitService outfitService;
	
	/* Swagger */
	@Operation(summary = "Find all outfits")
	@ApiResponse(responseCode = "200", content = {
			@Content(mediaType = "application/json",array= @ArraySchema(schema = @Schema(
					implementation = Outfit.class)))} , description = "Outfits retrieved ok")
	/**/
	@GetMapping(value = "/find/all")
	public List<Outfit> findAll( @RequestParam(value = "owner username", required = false) String owner,
			@RequestParam(value = "creation date from", required = false) Date creationFrom,
			@RequestParam(value = "creation date to", required = false) Date creationTo) {
		return outfitService.findAll( owner, creationFrom, creationTo);
	}

	/* Swagger */
	@Operation(summary = "Get outfit by id")
	@ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",schema = @Schema(implementation = Outfit.class))}, description = "Outfit retrieved ok")
	@ApiResponse(responseCode = "404", content = {@Content(mediaType = "application/json") }, description = "Resource not found")
	/**/
	@GetMapping("/get/by/outfitId/{outfitId}") 
	public Outfit findById(@PathVariable("outfitId") @Positive Long outfitId) {
		return outfitService.getById(outfitId);
	}

	/* Swagger */
	@Operation(summary = "Save a outfit")
	@io.swagger.v3.oas.annotations.parameters.RequestBody(content = {
			@Content(mediaType = "application/json",schema = @Schema(implementation = Outfit.class))})
	@ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",schema = @Schema(implementation = Outfit.class)) }, description = "Outfit saved ok")
	/**/
	@PostMapping("/save")
	@Validated(OnOutfitCreate.class)
	public Outfit save(@RequestBody @Valid Outfit outfit) {
		return outfitService.save(outfit);
	}

	/* Swagger */
	@Operation(summary = "Update a outfit")
	@ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json") }, description = "Outfit updated ok")
	@ApiResponse(responseCode = "404", content = {
			@Content(mediaType = "application/json") }, description = "Resource not found")
	/**/
	@PutMapping("/update")
	@Validated(OnOutfitUpdate.class)
	public Outfit update(@RequestBody @Valid Outfit outfit) {
		return outfitService.update(outfit);
	}
	

	/* Swagger */
	@Operation(summary = "Delete a outfit")
	@ApiResponse(responseCode = "200", content = {
			@Content(mediaType = "application/json") }, description = "Outfit deleted ok")
	/**/
	@DeleteMapping("/delete/by/outfitId/{outfitId}")
	public void deleteById(@PathVariable("outfitId") @Positive Long outfitId) {
		outfitService.deleteById(outfitId);
	}

}
