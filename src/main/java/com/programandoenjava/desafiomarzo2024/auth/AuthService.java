package com.programandoenjava.desafiomarzo2024.auth;

import com.programandoenjava.desafiomarzo2024.jwt.JwtService;
import com.programandoenjava.desafiomarzo2024.models.dtos.UsuarioDto;
import com.programandoenjava.desafiomarzo2024.models.dtos.request.CreateUserDto;
import com.programandoenjava.desafiomarzo2024.models.dtos.request.LoginRequestDto;
import com.programandoenjava.desafiomarzo2024.models.entities.Usuario;
import com.programandoenjava.desafiomarzo2024.models.enums.RolEnum;
import com.programandoenjava.desafiomarzo2024.models.mappers.IUsuarioDtoMapper;
import com.programandoenjava.desafiomarzo2024.models.respositories.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import com.programandoenjava.desafiomarzo2024.exceptions.BadRequestException;
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
    private final IUsuarioDtoMapper usuarioMapper;


    public UsuarioDto login(LoginRequestDto request) {

        var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Usuario user= userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);

        UsuarioDto userDto = usuarioMapper.toDto(user);
        userDto.setToken(token);

        return userDto;

    }

    public UsuarioDto register(CreateUserDto request) {

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
                .password(passwordEncoder.encode( request.getPassword()))
                .rol(RolEnum.CLIENTE)
                .build();

        userRepository.save(user);

        return usuarioMapper.toDto(user);
    }

}
