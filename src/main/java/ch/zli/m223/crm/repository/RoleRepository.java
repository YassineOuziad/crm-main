package ch.zli.m223.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.zli.m223.crm.model.AppUser;
import ch.zli.m223.crm.model.impl.AppUserImpl;
import ch.zli.m223.crm.model.impl.RoleImpl;

public interface RoleRepository extends JpaRepository<RoleImpl, Long>{

	public default AppUser setRoles(AppUser user, List<String> roles) {
		AppUserImpl appUser = (AppUserImpl)user;
		for (String role : roles) {
			RoleImpl dbRole = save(new RoleImpl(role, appUser));
			appUser.addRole(dbRole);
		}
		return user;
	}

}
