package com.mohit.payload.dto;

import com.mohit.domain.StoreStatus;
import com.mohit.modal.StoreContact;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreDTO {
    private Long id;
    private String brand;
    private Long storeAdminId;
    private UserDTO storeAdmin;
    private String storeType;
    private StoreStatus status;
    private String description;
    private StoreContact contact;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
