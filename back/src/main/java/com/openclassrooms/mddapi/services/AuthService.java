package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.domains.models.User;
import org.hibernate.exception.ConstraintViolationException;

public interface AuthService {

    User register(User user) throws ConstraintViolationException;

}
