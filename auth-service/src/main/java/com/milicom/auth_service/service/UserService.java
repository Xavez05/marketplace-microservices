package com.milicom.auth_service.service;

import com.milicom.auth_service.dto.UserDTO;

public interface UserService {
    UserDTO getUserDTOById(Long id);
}
