package ru.ntv.controllers.common;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ntv.dto.request.auth.NewUser;
import ru.ntv.dto.request.auth.OldUser;
import ru.ntv.dto.response.auth.AuthResponse;
import ru.ntv.entity.User;
import ru.ntv.repo.RoleRepository;
import ru.ntv.repo.UserRepository;
import ru.ntv.security.JwtTokenProvider;
import ru.ntv.etc.DatabaseRole;

@RestController
@CrossOrigin("*")
@RequestMapping("auth")
@Validated
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    JwtTokenProvider jwtUtils;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserRepository userRepository;

    @PostMapping("signin")
    ResponseEntity<AuthResponse> signIn(@Valid @RequestBody OldUser user) throws BadCredentialsException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateToken(authentication);
        AuthResponse response = new AuthResponse();
        response.setJwt("Bearer " + jwt);

        return ResponseEntity.ok(response);
    }

    @PostMapping("signup")
    ResponseEntity<AuthResponse> signUp(@Valid @RequestBody NewUser newUser){
        final var response = new AuthResponse();
        if (userRepository.existsByLogin(newUser.getUsername())) {
            response.setErrorMessage("Придумайте другой логин");
            return ResponseEntity
                    .badRequest()
                    .body(response);
        }


        // Create new user's account
        final var user = new User();
        user.setLogin(newUser.getUsername());
        user.setPassword(encoder.encode(newUser.getPassword()));
        user.setRole(
                roleRepository.findRoleByRoleName(
                        DatabaseRole.ROLE_CLIENT.name()
                )
        );
        userRepository.save(user);


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
