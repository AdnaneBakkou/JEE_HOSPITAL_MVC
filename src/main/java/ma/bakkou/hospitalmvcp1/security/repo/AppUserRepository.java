package ma.bakkou.hospitalmvcp1.security.repo;

import ma.bakkou.hospitalmvcp1.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,String> {
    AppUser findByUsername(String username);
}
