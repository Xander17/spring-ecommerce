package ru.geekbrains.adminui.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.adminui.dto.UserDto;
import ru.geekbrains.adminui.mapper.UserMapper;
import ru.geekbrains.shopdb.model.User;
import ru.geekbrains.shopdb.repo.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder encoder;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public Page<UserDto> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(userMapper::toDto);
    }

    @Transactional
    public void save(UserDto user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(userMapper.toEntity(user));
    }

    public Optional<UserDto> findById(int id) {
        return repository.findById(id).map(userMapper::toDto);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = repository.findByName(name)
                .orElseThrow(() -> new UsernameNotFoundException("User '" + name + "' not found"));
        List<SimpleGrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        if (user.getName().equals("sadmin"))
            return new org.springframework.security.core.userdetails.User(
                    user.getName(), user.getPassword(), authorities);
        return new org.springframework.security.core.userdetails.User(
                user.getName(), user.getPassword(), user.isEnabled(),
                true, true, true, authorities);
    }
}
