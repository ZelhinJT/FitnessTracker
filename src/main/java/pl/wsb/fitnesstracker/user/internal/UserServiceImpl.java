package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.user.api.UpdateUserDto;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import pl.wsb.fitnesstracker.user.api.UserService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> getUserByFullName(final String firstName, final String lastName) {
        return userRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findUsersByEmailFragment(final String email) {
        return userRepository.findByEmailContainingIgnoreCase(email);
    }

    @Override
    public List<User> findUsersOlderThan(final int age) {
        return userRepository.findOlderThan(age);
    }

    @Override
    public void deleteUser(final Long userId) {
        log.info("Deleting User with ID {}", userId);
        userRepository.deleteById(userId);
    }

    @Override
    public Optional<User> updateUser(final Long userId, final UpdateUserDto updateUserDto) {
        return userRepository.findById(userId)
                .map(user -> {
                    if (updateUserDto.firstName() != null) {
                        user.updateFirstName(updateUserDto.firstName());
                    }

                    if (updateUserDto.lastName() != null) {
                        user.updateLastName(updateUserDto.lastName());
                    }

                    if (updateUserDto.birthdate() != null) {
                        user.updateBirthdate(updateUserDto.birthdate());
                    }

                    if (updateUserDto.email() != null) {
                        user.updateEmail(updateUserDto.email());
                    }

                    return userRepository.save(user);
                });
    }

}