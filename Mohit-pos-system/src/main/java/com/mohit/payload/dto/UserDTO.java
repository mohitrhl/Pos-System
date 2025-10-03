package com.mohit.payload.dto;

import com.mohit.domain.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String email;
    private String password;
    private String phone;
    private String fullName;
    private UserRole role;
    private String username;
    private Long storeId;
    private Long branchId;
    private BranchDTO branch;
    private String branchName;
    private LocalDateTime lastLogin;

}
