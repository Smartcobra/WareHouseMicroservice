package in.jit.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import in.jit.model.User;
import in.jit.repo.UserRepository;

@Service
public class UserServiceImpl implements IUserService,UserDetailsService {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	
	@Override
	public Integer saveUser(User user) {
		user.setPassword(
		pwdEncoder.encode(user.getPassword()));
		return userRepo.save(user).getId();
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		Optional<User> opt = findByUsername(username);
		 if(!opt.isPresent())
			 throw new UsernameNotFoundException("User not exists");
		 User user = opt.get();
		return new org.springframework.security.core.userdetails.User(username,
				user.getPassword(),
				user.getRoles().stream().
				map(role-> new SimpleGrantedAuthority(role))
				.collect(Collectors.toList()));
	}

}
