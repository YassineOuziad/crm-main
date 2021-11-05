package ch.zli.m223.crm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.zli.m223.crm.model.AppUser;
import ch.zli.m223.crm.model.impl.AppUserImpl;

public interface UserRepository extends JpaRepository<AppUserImpl, Long>{
	
	public Optional<AppUserImpl> findUserByEmail(String email);
	
	public default AppUser add(String email, String password) {
		return save(new AppUserImpl(email, password));
	}
	
}