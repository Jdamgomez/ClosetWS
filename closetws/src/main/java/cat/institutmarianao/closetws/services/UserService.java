package cat.institutmarianao.closetws.services;

import java.util.List;

import cat.institutmarianao.closetws.controllers.dto.AuthLoginRequest;
import cat.institutmarianao.closetws.controllers.dto.AuthResponse;
import cat.institutmarianao.closetws.model.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public interface UserService {
	
	AuthResponse loginUser(@NotNull AuthLoginRequest authLoginRequest);
	
	AuthResponse createUser(@NotNull @Valid User user);
	
	List<User> findAll(String fullName);

	User getByUsername(@NotBlank String username);

	User update(@NotNull @Valid User user);

	void deleteByUsername(@NotBlank String username);
	
}
