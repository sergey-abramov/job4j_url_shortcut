package ru.job4j.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.model.User;
import ru.job4j.repository.UserRepository;

import static java.util.Collections.emptyList;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final PasswordEncoder encoder;

    private final UserRepository repository;

    public User save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        var user = repository.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException(login);
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(),
                user.getPassword(), emptyList());
    }

}
