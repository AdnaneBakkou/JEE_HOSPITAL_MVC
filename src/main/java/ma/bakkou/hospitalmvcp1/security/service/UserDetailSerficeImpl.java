package ma.bakkou.hospitalmvcp1.security.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.bakkou.hospitalmvcp1.security.entities.AppRole;
import ma.bakkou.hospitalmvcp1.security.entities.AppUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@AllArgsConstructor
public class UserDetailSerficeImpl implements UserDetailsService {

    private AccountService accountService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = accountService.loadUserByUsername(username);

        if (appUser== null) throw new UsernameNotFoundException(String.format("user %s not found"+username));

     String[] roles = appUser.getRoles().stream().map(u->u.getRole()).toArray(String[]::new);

        UserDetails userDetails = User.withUsername(appUser.getUsername())
                                      .password(appUser.getPassword())
                                       .roles(roles).build();
        return userDetails;
    }
}
