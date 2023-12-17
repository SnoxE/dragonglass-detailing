package dgbackend.database.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserDto(
        @JsonProperty("id") int id,
        @JsonProperty("first_name")  String firstName,
        @JsonProperty("last_name") String lastName,
        @JsonProperty("email") String email,
        @JsonProperty("phone_number") String phoneNumber,
        @JsonProperty("password") String password,
        @JsonProperty("role") String role) {}
