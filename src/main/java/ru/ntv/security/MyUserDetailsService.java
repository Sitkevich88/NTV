package ru.ntv.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ntv.entity.users.User;
import ru.ntv.repo.user.UserRepository;

import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        final User user = userRepository
//                .findByLogin(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found."));
//        final var roleName = user.getRole().getRoleName();
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getLogin(),
//                user.getPassword(),
//                Collections.singleton(new SimpleGrantedAuthority(roleName))
//        );
//    }


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.
                findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователя с логином " + login + " не существует"));


//        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getRoleName());


        return MyUserDetails.build(user);
    }
}
