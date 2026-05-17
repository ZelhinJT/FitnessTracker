package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.wsb.fitnesstracker.user.api.*;

import java.util.List;

/**
 * UserController is responsible for handling HTTP requests related to user operations.
 * It provides endpoints for retrieving and creating users.
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserService userService;

    private final UserProvider userProvider;

    private final UserMapper userMapper;

    @PostMapping
    public UserDto addUser(@RequestBody UserDto userDto) throws InterruptedException {
        final User user = userMapper.toUser(userDto);

        return userMapper.toUserDto(userService.createUser(user));
    }

    @GetMapping
    public List<UserDto> getUsers() throws InterruptedException {
        return this.userProvider.findAllUsers().stream()
                .map(this.userMapper::toUserDto)
                .toList();
    }

    /**
     * Zadanie 1:
     * Wylistowanie podstawowych informacji o wszystkich użytkownikach.
     * Zwraca tylko ID, imię i nazwisko.
     */
    @GetMapping("/zadanie1")
    public List<UserDto2> getBasicUsers() throws InterruptedException {
        return this.userProvider.findAllUsers().stream()
                .map(this.userMapper::toUserDto2)
                .toList();
    }

    /**
     * Pobranie szczegółów użytkownika po ID.
     */
    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable Long userId) throws InterruptedException {
        return this.userProvider.getUser(userId)
                .map(this.userMapper::toUserDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    /**
     * Pobranie szczegółów użytkownika po e-mailu.
     */
    @GetMapping("/email/{email}")
    public UserDto getUserByEmail(@PathVariable String email) throws InterruptedException {
        return this.userProvider.getUserByEmail(email)
                .map(this.userMapper::toUserDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    /**
     * Pobranie szczegółów użytkownika po imieniu i nazwisku.
     * Przykład:
     * /v1/users/name?firstName=Jan&lastName=Kowalski
     */
    @GetMapping("/name")
    public UserDto getUserByFullName(@RequestParam String firstName,
                                     @RequestParam String lastName) throws InterruptedException {
        return this.userProvider.getUserByFullName(firstName, lastName)
                .map(this.userMapper::toUserDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    /**
     * Usunięcie użytkownika po ID.
     */
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) throws InterruptedException {
        this.userProvider.getUser(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        this.userService.deleteUser(userId);
    }

    /**
     * Wyszukiwanie użytkowników po fragmencie e-maila, bez rozróżniania wielkości liter.
     * Zwraca tylko ID oraz e-mail.
     * Przykład:
     * /v1/users/search/email?email=gmail
     */
    @GetMapping("/search/email")
    public List<UserEmailDto> findUsersByEmailFragment(@RequestParam String email) throws InterruptedException {
        return this.userProvider.findUsersByEmailFragment(email).stream()
                .map(this.userMapper::toUserEmailDto)
                .toList();
    }

    /**
     * Wyszukiwanie użytkowników starszych niż podany wiek.
     * Przykład:
     * /v1/users/older-than/18
     */
    @GetMapping("/older-than/{age}")
    public List<UserDto> findUsersOlderThan(@PathVariable int age) throws InterruptedException {
        return this.userProvider.findUsersOlderThan(age).stream()
                .map(this.userMapper::toUserDto)
                .toList();
    }

    /**
     * Aktualizacja dowolnego atrybutu użytkownika.
     * Możesz wysłać jedno pole albo kilka pól.
     */
    @PutMapping("/{userId}")
    public UserDto updateUser(@PathVariable Long userId,
                              @RequestBody UpdateUserDto updateUserDto) throws InterruptedException {
        return this.userService.updateUser(userId, updateUserDto)
                .map(this.userMapper::toUserDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

}