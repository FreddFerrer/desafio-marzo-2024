package com.programandoenjava.desafiomarzo2024.auth;

import com.programandoenjava.desafiomarzo2024.jwt.JwtService;
import com.programandoenjava.desafiomarzo2024.models.dtos.UsuarioDto;
import com.programandoenjava.desafiomarzo2024.models.dtos.request.CreateUserDto;
import com.programandoenjava.desafiomarzo2024.models.dtos.request.LoginRequestDto;
import com.programandoenjava.desafiomarzo2024.models.entities.Usuario;
import com.programandoenjava.desafiomarzo2024.models.enums.RolEnum;
import com.programandoenjava.desafiomarzo2024.models.respositories.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final IUsuarioRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public UsuarioDto login(LoginRequestDto request) {

        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getContraseña()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Usuario user= userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);
        return UsuarioDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .nombre(user.getNombre())
                .apellido(user.getApellido())
                .email(user.getEmail())
                .token(token)
                .rol(user.getRol().name())
                .build();

    }

    public UsuarioDto register(CreateUserDto request) throws BadRequestException {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new BadRequestException("Username already exists");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already exists");
        }

        Usuario user = Usuario.builder()
                .username(request.getUsername())
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .email(request.getEmail())
                .contraseña(passwordEncoder.encode( request.getContraseña()))
                .rol(RolEnum.CLIENTE)
                .build();

        userRepository.save(user);

        return UsuarioDto.builder()
                .id(user.getId())
                .nombre(user.getNombre())
                .apellido(user.getApellido())
                .username(user.getUsername())
                .email(user.getEmail())
                .rol(user.getRol().name())
                .build();
    }

}
