package com.enihsyou.trip.bank.service.enums;

import com.enihsyou.trip.bank.service.exception.AuthorityNotGrantedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

public enum AuthorityType {
    ReadProfile("read:profile");

    private String authority;

    private SimpleGrantedAuthority grantedAuthority;

    AuthorityType(String name) {
        authority = name;
        grantedAuthority = new SimpleGrantedAuthority(authority);

    }

    public String getDisplayName() {
        return authority;
    }

    public GrantedAuthority getGrantedAuthority() {
        return grantedAuthority;
    }

    @Override
    public String toString() {
        return authority;
    }

    public void shouldGrant(){
        final Collection<? extends GrantedAuthority> authorities =
            SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        if (!authorities.contains(this.getGrantedAuthority()))
            throw new AuthorityNotGrantedException(this);
    }
}