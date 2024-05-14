package cat.institutmarianao.closetws.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cat.institutmarianao.closetws.controllers.dto.AuthLoginRequest;
import cat.institutmarianao.closetws.controllers.dto.AuthResponse;
import cat.institutmarianao.closetws.model.User;
import cat.institutmarianao.closetws.services.UserService;
import cat.institutmarianao.closetws.validation.groups.OnUserCreate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

/* Swagger */
@Tag(name = "Authentication", description = "AuthenticationController API")
/**/
@RestController
@RequestMapping("/auth")
@Validated
public class AuthenticationController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/* Swagger */
	@Operation(summary = "Sign-up a user")
	@io.swagger.v3.oas.annotations.parameters.RequestBody(content = {
			@Content(mediaType = "application/json",schema = @Schema(implementation = User.class))})
	@ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",schema = @Schema(implementation = ResponseEntity.class)) }, description = "User saved ok")
	/**/
	@PostMapping("/signup")
	@Validated(OnUserCreate.class)
	public ResponseEntity<AuthResponse> signup(@RequestBody @Valid User user) {
		return new ResponseEntity<>(userService.createUser(encodePassword(user)),HttpStatus.OK);
	}
	
	
	/* Swagger */
	@Operation(summary = "Authenticate a user")
	@io.swagger.v3.oas.annotations.parameters.RequestBody(content = {
			@Content(mediaType = "application/json", examples = {
					@ExampleObject(value = "{\"username\":\"string\",\"password\":\"string\"}") }) })
	@ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseEntity.class))},  description = "User authenticated ok")
	@ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json") }, description = "Resource not found")
	/**/
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest loginRequest) {
		return new ResponseEntity<>(userService.loginUser(loginRequest),HttpStatus.OK);
		
	}
	
	private User encodePassword(User user) {
		Logger logger = LoggerFactory.getLogger(UserController.class);
		logger.info("PWD"+user.getPassword());
		
		String rawPassword = user.getPassword();
		if (rawPassword != null) user.setPassword(passwordEncoder.encode(rawPassword));
		logger.info("BDPWD"+user.getPassword());
		return user;
	}

}
