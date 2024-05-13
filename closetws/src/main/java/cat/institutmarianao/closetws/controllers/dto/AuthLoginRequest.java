package cat.institutmarianao.closetws.controllers.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthLoginRequest(@NotBlank String username,@NotBlank String password) {}
