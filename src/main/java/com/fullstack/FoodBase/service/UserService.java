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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class UserService implements UserDetailsService {

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findById(username).orElse(null);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(user);
    }
}
