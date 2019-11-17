package com.jap.initial.springjwt.contoller;

import com.jap.initial.springjwt.payload.ApiResponse;
import com.jap.initial.springjwt.payload.LoginRequest;
import com.jap.initial.springjwt.payload.JwtAuthResponse;
import com.jap.initial.springjwt.model.Users;
import com.jap.initial.springjwt.security.JwtTokenProvider;
import com.jap.initial.springjwt.services.MapValidationErrorService;
import com.jap.initial.springjwt.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private UsersService usersService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
        if (errorMap != null) return errorMap;

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JwtAuthResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody Users user, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
        if (errorMap != null) return errorMap;

        Users newUser = usersService.saveUser(user);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.CREATED, newUser), HttpStatus.CREATED);
    }
}
