package cat.institutmarianao.closetws.services;

import java.util.List;

import cat.institutmarianao.closetws.controllers.dto.AuthLoginRequest;
import cat.institutmarianao.closetws.controllers.dto.AuthResponse;
import cat.institutmarianao.closetws.model.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public interface UserService {
	User authenticate(@NotEmpty String username, @NotEmpty String password);
	
	AuthResponse loginUser(@NotNull AuthLoginRequest authLoginRequest);
	
	List<User> findAll(String fullName);

	User getByUsername(@NotBlank String username);

	User save(@NotNull @Valid User user);

	User update(@NotNull @Valid User user);

	void deleteByUsername(@NotBlank String username);
	
}
