package pl.wsb.fitnesstracker.user.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Query searching users by email address. It matches by exact match.
     *
     * @param email email of the user to search
     * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                .filter(user -> Objects.equals(user.getEmail(), email))
                .findFirst();
    }

    default Optional<User> findByFirstNameAndLastName(String firstName, String lastName) {
        return findAll().stream()
                .filter(user -> Objects.equals(user.getFirstName(), firstName))
                .filter(user -> Objects.equals(user.getLastName(), lastName))
                .findFirst();
    }

    default List<User> findByEmailContainingIgnoreCase(String email) {
        return findAll().stream()
                .filter(user -> user.getEmail() != null)
                .filter(user -> user.getEmail().toLowerCase().contains(email.toLowerCase()))
                .toList();
    }

    default List<User> findOlderThan(int age) {
        final LocalDate maxBirthdate = LocalDate.now().minusYears(age);

        return findAll().stream()
                .filter(user -> user.getBirthdate().isBefore(maxBirthdate))
                .toList();
    }

}