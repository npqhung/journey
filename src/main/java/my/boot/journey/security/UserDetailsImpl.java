package my.boot.journey.security;

import my.boot.journey.pojo.User;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.HashSet;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = -6509897037222767090L;

	private Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
	private String password;
	private String username;
	private String firstname;
	private String lastname;
	private String email;

	private Boolean locked = false;
	private Boolean noLongerHaveAccess = false; //to cater for deleted user who tries to login...

	public UserDetailsImpl(@Nullable User user, Boolean locked, Boolean noLongerHaveAccess,
						   Collection<GrantedAuthority> authorities) {
		if (user != null) {
			this.password = "{noop}" + user.getPassword();
			this.username = user.getUsername();
			this.firstname = user.getFirstname();
			this.lastname = user.getLastname();
			this.email = user.getEmail();
		}
		this.locked = locked;
		this.noLongerHaveAccess = noLongerHaveAccess;
		this.authorities = authorities;
	}

	public Collection<GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() { return true;	}

	@Override
	public boolean isAccountNonLocked() {
		return !this.locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return !this.noLongerHaveAccess;
	}
}


