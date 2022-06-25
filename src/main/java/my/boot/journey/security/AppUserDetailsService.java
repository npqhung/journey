package my.boot.journey.security;

import lombok.extern.slf4j.Slf4j;
import my.boot.journey.pojo.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class AppUserDetailsService implements UserDetailsService{

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Attempting to lookup login by user name " + username);
        if (!StringUtils.hasLength(username)) {
            throw new UsernameNotFoundException("Invalid user name or password");
        }

        if(!username.equals("admin")){
            return null;
        }

        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        my.boot.journey.pojo.User user = new User(username,"password");
        UserDetails userDetails = new UserDetailsImpl(user, false, false, authorities);
        return userDetails;
    }
}
