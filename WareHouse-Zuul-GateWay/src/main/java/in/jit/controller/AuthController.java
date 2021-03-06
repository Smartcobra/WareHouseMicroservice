package in.jit.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.jit.model.Role;
import in.jit.model.RoleDTO;
import in.jit.model.User;
import in.jit.model.UserRequest;
import in.jit.model.UserResponse;
import in.jit.service.IUserService;
import in.jit.util.JWTUtil;

@RestController
@RequestMapping("/user")
public class AuthController {

	@Autowired
	private IUserService userService;

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;
	

	@PostMapping("/save")
	public ResponseEntity<String> saveUser(@RequestBody User user) {
		Integer id = userService.saveUser(user);
		String body = "User with '" + id + "' saved";
		return ResponseEntity.ok(body);

	}

	@PostMapping("/login")
	public ResponseEntity<UserResponse> loginUser(@RequestBody UserRequest request) {
            System.out.println("user name>>>>>>>>>"+request.getUsername());
            System.out.println("Password>>>>>>>>"+request.getPassword());
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		final Optional<User> userDetails = userService
				.findByUsername(request.getUsername());

		//String token = jwtUtil.generateToken(request.getUsername());
		String token = jwtUtil.generateToken(userDetails.get().getUsername());
		return ResponseEntity.ok(new UserResponse(token, "Success ! Generated By ABSWareHouse"));

	}
	
	@PostMapping("/role")
	public ResponseEntity<RoleDTO> getUserRole(@RequestBody Role role) {
		System.out.println(">>>>>>>>>>>"+role.getUsername());
		//List<RoleDTO> findUserByRole = userService.findUserByRole(role.getUsername());
	     List<String> findUserByRole = userService.findUserByRole(role.getUsername());
		 RoleDTO newrole= new RoleDTO();
		//newrole.setId(1);
		 newrole.setRoles(findUserByRole);
		System.out.println(">>>>findUserByRole"+findUserByRole);
		//String body = "User with '" + findUserByRole + "' is present";
		return new ResponseEntity<RoleDTO>(newrole, HttpStatus.OK);
		//return new ResponseEntity<RoleDTO>(HttpStatus.OK);
	}
}
