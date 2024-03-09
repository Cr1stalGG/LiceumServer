package by.grsu.edu.banking.security.config;

import by.grsu.edu.banking.entity.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AccountUserDetailsConfig implements UserDetails {
    private String username;
    private String password;
    private SimpleGrantedAuthority authority;


    public AccountUserDetailsConfig(Account account){
        this.username = account.getUsername();
        this.password = account.getPassword();
        this.authority = new SimpleGrantedAuthority(account.getRole().toString());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(authority);
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword(){
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
