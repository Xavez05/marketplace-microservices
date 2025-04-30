package com.milicom.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDTO {

    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;

    private String address;
    private String phoneNumber;
}
