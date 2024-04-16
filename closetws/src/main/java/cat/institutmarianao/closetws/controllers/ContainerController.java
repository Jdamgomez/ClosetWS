package cat.institutmarianao.closetws.controllers;

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

import cat.institutmarianao.closetws.model.Closet;
import cat.institutmarianao.closetws.model.Container;
import cat.institutmarianao.closetws.model.Container.Type;
import cat.institutmarianao.closetws.model.Suitcase;
import cat.institutmarianao.closetws.services.ContainerService;
import cat.institutmarianao.closetws.validation.groups.OnContainerCreate;
import cat.institutmarianao.closetws.validation.groups.OnContainerUpdate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

/* Swagger */
@Tag(name = "Container", description = "ContainerController API")
/**/
@RestController
@RequestMapping("/containers")
@Validated
public class ContainerController {

	@Autowired
	private ContainerService containerService;
	
	/* Swagger */
	@Operation(summary = "Find all containers")
	@ApiResponse(responseCode = "200", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(anyOf = {
					Closet.class, Suitcase.class }, discriminatorProperty = "type"))) }, description = "Containers retrieved ok")
	/**/
	@GetMapping(value = "/find/all")
	public List<Container> findAll(@RequestParam(value = "owner", required = false) String owner,
			@RequestParam(value = "type", required = false) Type type) {

		return containerService.findAll(owner,type);
	}
	
	/* Swagger */
	@Operation(summary = "Find all containers")
	@ApiResponse(responseCode = "200", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(anyOf = {
					Closet.class, Suitcase.class }, discriminatorProperty = "type"))) }, description = "Containers retrieved ok")
	/**/
	@GetMapping("/find/all/CLOSETS")
	public List<Container> findAllClosets(@RequestParam(value = "owner", required = false) String owner) {

		return containerService.findAllClosets(owner);
	}
	
	/* Swagger */
	@Operation(summary = "Find all containers")
	@ApiResponse(responseCode = "200", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(anyOf = {
					Closet.class, Suitcase.class }, discriminatorProperty = "type"))) }, description = "Containers retrieved ok")
	/**/
	@GetMapping("/find/all/SUITCASES")
	public List<Container> findAllSuitcases(@RequestParam(value = "owner", required = false) String owner) {

		return containerService.findAllSuitcases(owner);
	}
	
	/* Swagger */
	@Operation(summary = "Get container by id")
	@ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(anyOf = {
			Closet.class, Suitcase.class }, discriminatorProperty = "type")) }, description = "Container retrieved ok")
	@ApiResponse(responseCode = "404", content = {
			@Content(mediaType = "application/json") }, description = "Container not found")
	/**/
	@GetMapping("/get/by/id/{containerId}")
	public Container findById(@PathVariable("containerId") @Positive Long containerId) {
		return containerService.getById(containerId);
	}
	
	/* Swagger */
	@Operation(summary = "Save a container")
	@io.swagger.v3.oas.annotations.parameters.RequestBody(content = {
			@Content(mediaType = "application/json", schema = @Schema(oneOf = { 
					Closet.class,	Suitcase.class}, discriminatorProperty = "type"))})
	@ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(anyOf = {
			Closet.class, Suitcase.class }, discriminatorProperty = "type")) }, description = "Container saved ok")
	/**/
	@PostMapping("/save")
	@Validated(OnContainerCreate.class)
	public Container save(@RequestBody @Valid Container container) {
		return containerService.save(container);
	}
	
	/* Swagger */
	@Operation(summary = "Update a container")
	@ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(anyOf = {
			Closet.class, Suitcase.class }, discriminatorProperty = "type")) }, description = "Container updated ok")
	@ApiResponse(responseCode = "404", content = {
			@Content(mediaType = "application/json") }, description = "Container not found")
	/**/
	@PutMapping("/update")
	@Validated(OnContainerUpdate.class)
	public Container update(@RequestBody @Valid Container container) {
		return containerService.update(container);
	}
	
	/* Swagger */
	@Operation(summary = "Delete a container")
	@ApiResponse(responseCode = "200", content = {
			@Content(mediaType = "application/json") }, description = "Container deleted ok")
	/**/
	@DeleteMapping("/delete/by/id/{containerId}")
	public void deleteById(@PathVariable("containerId") @Positive Long containerId) {
		containerService.deleteById(containerId);
	}
}
