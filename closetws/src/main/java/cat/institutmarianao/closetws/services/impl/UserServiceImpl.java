package cat.institutmarianao.closetws.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import cat.institutmarianao.closetws.exception.NotFoundException;
import cat.institutmarianao.closetws.model.User;
import cat.institutmarianao.closetws.repositories.UserRepository;
import cat.institutmarianao.closetws.services.UserService;
import cat.institutmarianao.closetws.specifications.UserWithFullName;
import cat.institutmarianao.closetws.validation.groups.OnUserCreate;
import cat.institutmarianao.closetws.validation.groups.OnUserUpdate;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Validated
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MessageSource messageSource;

	@Autowired
	private PasswordEncoder passwordEncoder;
	

	@Override
	public User authenticate(@NotEmpty String username, @NotEmpty String password) {
		User user = getByUsername(username);
		if (!passwordEncoder.matches(password, user.getPassword()))
			throw new ValidationException(messageSource.getMessage("error.UserService.user.password",
					new Object[] { username }, LocaleContextHolder.getLocale()));

		return user;
	}

	@Override
	public List<User> findAll(String fullName) {
		Specification<User> spec = Specification.where(new UserWithFullName(fullName));
		return userRepository.findAll(spec);
	}
	
	@Override
	public User getByUsername(@NotBlank String username) {
		return userRepository.findById(username).orElseThrow(NotFoundException::new);
	}

	@Override
	@Validated(OnUserCreate.class)
	public User save(@NotNull @Valid User user) {
		User ret = userRepository.saveAndFlush(user);
		return ret;
	}

	@Override
	@Validated(OnUserUpdate.class)
	public User update(@NotNull @Valid User user) {
		User dbUser = getByUsername(user.getUsername());

		if (user.getPassword() != null) dbUser.setPassword(user.getPassword());
		if (user.getFullName() != null) dbUser.setFullName(user.getFullName());
		if (user.getProfilePicture() != null) dbUser.setProfilePicture(user.getProfilePicture());

		return userRepository.saveAndFlush(dbUser);
	}

	@Override
	public void deleteByUsername(@NotBlank String username) {
		userRepository.deleteById(username);
	}
}
