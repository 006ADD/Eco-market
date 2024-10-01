package kg.example.spring.ecomarket.services;

import kg.example.spring.ecomarket.entities.User;
import kg.example.spring.ecomarket.repositories.UserRepository;
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

public interface UserService{ //implements UserDetailsService
    List<User> getAllUser();

    Optional<User> findByUsername(String username);

    void removeUser(Long userId);

    User uploadUser(User user);


//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        User user =  findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
//           String.format("Пользователь 's%' не найдет ", username)
//        ));
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
//        );
//    }
    UserDetails newLoadUserNameByUsername(String username);
    void createNewUser(User user);
}
