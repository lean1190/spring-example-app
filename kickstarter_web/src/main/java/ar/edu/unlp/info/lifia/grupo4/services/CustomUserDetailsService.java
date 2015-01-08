package ar.edu.unlp.info.lifia.grupo4.services;

import ar.edu.unlp.info.lifia.grupo1.model.User;
import ar.edu.unlp.info.lifia.grupo1.repository.interfaces.UserDAO;
import ar.edu.unlp.info.lifia.grupo4.dto.UserSession;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired  
    private UserDAO userDAO;

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {        
        // Retrieves user from database
        List<User> foundUsers = this.getUserDAO().findByUsername(username);
        
        if(!foundUsers.isEmpty()) {
            // Founded
            User domainUser = foundUsers.get(0);

            // Creates a Spring User instance and returns it
            return new UserSession(
                    domainUser.getId(),
                    username, 
                    domainUser.getPassword(), 
                    this.getAuthorities(domainUser.isAdministrator()));
        }
        else
            // Not found
            throw new UsernameNotFoundException("User not found");
    }
    
    /**
     * Returns a list of authorities based on the passed parameter.
     * 
     * @param isAdmin if the user is an admin or not
     * @return the list with the user authorities
     */
    private List<GrantedAuthority> getAuthorities(boolean isAdmin) {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);
        
        if (isAdmin) {
            authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        else
            authList.add(new SimpleGrantedAuthority("ROLE_USER"));
        
        return authList;
    }
    
}
