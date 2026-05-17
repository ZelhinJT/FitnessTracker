package pl.wsb.fitnesstracker.user.api;

import java.util.List;
import java.util.Optional;

public interface UserProvider {

    Optional<User> getUser(Long userId);

    Optional<User> getUserByEmail(String email);

    Optional<User> getUserByFullName(String firstName, String lastName);

    List<User> findAllUsers();

    List<User> findUsersByEmailFragment(String email);

    List<User> findUsersOlderThan(int age);

}