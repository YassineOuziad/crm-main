package ch.zli.m223.crm.service;

import java.util.List;
import java.util.Optional;

import ch.zli.m223.crm.model.AppUser;
import ch.zli.m223.crm.model.impl.AppUserImpl;

public interface UserService {

	List<AppUser> getAllUsers();
	
	AppUser getUserById(long id);

	void deleteById(long id);

	Optional<AppUserImpl> getUserByEmail(String email);
	
	AppUser addUser(String email, String password);

	AppUser setRolesForUser(long id, List<String> roles);
}
