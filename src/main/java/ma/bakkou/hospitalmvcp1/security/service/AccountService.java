package ma.bakkou.hospitalmvcp1.security.service;

import ma.bakkou.hospitalmvcp1.security.entities.AppRole;
import ma.bakkou.hospitalmvcp1.security.entities.AppUser;

public interface AccountService {
    AppUser addNewUser(String usename,String password , String email , String confirmPassword);
    AppRole addNewRole(String role);
    void addRoleToUser(String username , String role);
    void removeRoleFromUser(String username, String role);
    AppUser loadUserByUsername(String username);
}
