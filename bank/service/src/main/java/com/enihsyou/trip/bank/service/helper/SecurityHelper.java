package com.enihsyou.trip.bank.service.helper;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class SecurityHelper {

    public String userId() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
