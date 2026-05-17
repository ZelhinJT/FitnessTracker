package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserDto2;
import pl.wsb.fitnesstracker.user.api.UserEmailDto;

@Component
class UserMapper {

    UserDto toUserDto(User user) {
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getBirthdate(), user.getEmail());
    }

    UserDto2 toUserDto2(User user) {
        return new UserDto2(user.getId(), user.getFirstName(), user.getLastName());
    }

    UserEmailDto toUserEmailDto(User user) {
        return new UserEmailDto(user.getId(), user.getEmail());
    }

    User toUser(UserDto userDto) {
        return new User(userDto.firstName(), userDto.lastName(), userDto.birthdate(), userDto.email());
    }

}