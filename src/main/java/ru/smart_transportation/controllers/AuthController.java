package ru.smart_transportation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.smart_transportation.dto.request.NewUser;
import ru.smart_transportation.dto.request.OldUser;
import ru.smart_transportation.dto.response.AuthResponse;
import ru.smart_transportation.entity.AppUser;
import ru.smart_transportation.repo.AppUserRepository;
import ru.smart_transportation.repo.RoleRepository;
import ru.smart_transportation.security.JwtTokenProvider;
import ru.smart_transportation.etc.DatabaseRole;
import ru.smart_transportation.service.ClientService;

@RestController
@CrossOrigin("*")
@RequestMapping("auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AppUserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    JwtTokenProvider jwtUtils;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    ClientService clientService;

    @PostMapping("signin")
    ResponseEntity<AuthResponse> signIn(@RequestBody OldUser user){
        final var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final var jwt = jwtUtils.generateToken(authentication);
        final var response = new AuthResponse();
        response.setJwt("Bearer " + jwt);

        return ResponseEntity.ok(response);
    }

    @PostMapping("signup")
    ResponseEntity<AuthResponse> signUp(@RequestBody NewUser newUser){
        final var response = new AuthResponse();
        if (userRepository.existsByUsername(newUser.getUsername())) {
            response.setErrorMessage("Придумайте другой логин");
            return ResponseEntity
                    .badRequest()
                    .body(response);
        }


        // Create new user's account
        final var appUser = new AppUser();
        appUser.setUsername(newUser.getUsername());
        appUser.setPassword(encoder.encode(newUser.getPassword()));
        appUser.setRole(
                roleRepository.findRoleByRoleName(
                        DatabaseRole.ROLE_CLIENT.name()
                )
        );
        userRepository.save(appUser);
        clientService.createClient(newUser, appUser);


        // Sign in
        final var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(newUser.getUsername(), newUser.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final var jwt = jwtUtils.generateToken(authentication);
        response.setJwt("Bearer " + jwt);

        return ResponseEntity.ok(response);
    }
}
