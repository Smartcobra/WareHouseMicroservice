package in.jit.service;

import java.util.Optional;

import in.jit.model.User;

public interface IUserService {

	public Integer saveUser(User user);
	
	public Optional<User> findByUsername(String username);
	
}
