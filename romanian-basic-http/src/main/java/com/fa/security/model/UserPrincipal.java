package com.fa.security.model;

import com.fa.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {

    private User user;

    public UserPrincipal(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorityList = user.getRoleList().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role)).collect(Collectors.toList());
        authorityList.addAll(this.user.getPermissionList().stream().map(permission -> new SimpleGrantedAuthority(permission)).collect(Collectors.toList()));

        return authorityList;

        /*
        List<GrantedAuthority> authorities = new ArrayList<>();

        this.user.getRoleList().forEach(role -> {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + role);
            authorities.add(grantedAuthority);
        });

        this.user.getPermissionList().forEach(permission -> {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission);
            authorities.add(grantedAuthority);
        });

        return authorities;
        */
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
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
        return this.user.getActive() == 1;
    }
}
