package kg.example.spring.ecomarket.services.impls;

import kg.example.spring.ecomarket.entities.User;
import kg.example.spring.ecomarket.repositories.UserRepository;
import kg.example.spring.ecomarket.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService, UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    public void removeUser(Long userId){
        userRepository.findById(userId).ifPresent(user -> userRepository.delete(user));
    }

    @Override
    public User uploadUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public UserDetails newLoadUserNameByUsername(String username)  throws UsernameNotFoundException {
        return loadUserByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user =  findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Пользователь 's%' не найдет ", username)
        ));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }

    @Override
    public void createNewUser(User user){
        userRepository.save(user);
    }
}
