package pl.wsb.fitnesstracker.user.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

import java.time.LocalDate;

public record UpdateUserDto(
        @Nullable String firstName,
        @Nullable String lastName,
        @Nullable @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthdate,
        @Nullable String email) {

}