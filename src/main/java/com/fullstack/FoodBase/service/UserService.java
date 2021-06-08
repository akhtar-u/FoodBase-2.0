package com.fullstack.FoodBase.service;

import com.fullstack.FoodBase.exceptions.UserAlreadyExistsException;
import com.fullstack.FoodBase.exceptions.UserNotFoundException;
import com.fullstack.FoodBase.exceptions.WrongPasswordException;
import com.fullstack.FoodBase.model.Login;
import com.fullstack.FoodBase.model.Register;
import com.fullstack.FoodBase.model.User;
import com.fullstack.FoodBase.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public String registerNewUser(Register register) throws UserAlreadyExistsException {
        if (userRepository.findById(register.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("User with username: " + register.getUsername() + " already exists!");
        }
        if (userRepository.findByEmail(register.getEmail()) != null) {
            throw new UserAlreadyExistsException("User with email: " + register.getEmail() + " already exists!");
        }

        User user = new User();
        user.setUsername(register.getUsername());
        user.setEmail(register.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(register.getPassword()));
        userRepository.save(user);

        return "User successfully registered!";
    }

    public String loginUser(Login login) throws UserNotFoundException, WrongPasswordException {
        if (userRepository.findById(login.getUsername()).isPresent()) {
            throw new UserNotFoundException("No user found with username: " + login.getUsername());
        }
        if (bCryptPasswordEncoder.matches(login.getPassword(), userRepository.findByEmail(login.getUsername()).getPassword())) {
            throw new WrongPasswordException("Provided password is wrong for user with email: " + login.getUsername());
        }

        return "User logged in!";
    }
}
