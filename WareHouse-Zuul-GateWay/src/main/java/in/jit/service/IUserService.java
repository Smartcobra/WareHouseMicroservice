package in.jit.service;

import java.util.List;
import java.util.Optional;

import in.jit.model.RoleDTO;
import in.jit.model.User;

public interface IUserService {

	public Integer saveUser(User user);
	
	public Optional<User> findByUsername(String username);
	
	public List<String> findUserByRole(String username);
	//public List<RoleDTO> findUserByRole(String username);
	
}
