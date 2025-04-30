package com.milicom.user_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileRequest {
    private String address;
    private String phoneNumber;
}
