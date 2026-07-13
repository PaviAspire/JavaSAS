package com.application.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserRequest(@NotBlank(message="name cannot be blank or null") String name, @NotBlank(message="Email cannot be blank or null") @Email(message = "Invalid Email") String email,@NotBlank @Pattern(regexp = "^[0-9]\\d{10}$",message = "Invalid mobile number.Must be 10 digit mobile number") String mobileNumber) {
}
