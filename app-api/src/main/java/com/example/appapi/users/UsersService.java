package com.example.appapi.users;

import com.example.appapi.users.model.Users;
import com.example.appapi.users.model.UsersDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsersService implements UserDetailsService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UsersDto.SignupResponse signup(UsersDto.SignupRequest dto) {
        Users user = usersRepository.save(dto.toEntity(passwordEncoder.encode(dto.getPassword())));

        return UsersDto.SignupResponse.from(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> result = usersRepository.findByUserId(username);

        if (result.isPresent()) {
            Users users = result.get();
            return users;
        }

        return null;
    }

    @Transactional(readOnly = true)
    public UsersDto.SignupResponse read(Long userIdx) {
        Users users = usersRepository.findById(userIdx).orElseThrow();
        return UsersDto.SignupResponse.from(users);
    }
}
