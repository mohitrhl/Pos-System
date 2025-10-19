package com.mohit.controller;

import com.mohit.domain.StoreStatus;
import com.mohit.exception.ResourceNotFoundException;
import com.mohit.exception.UserException;
import com.mohit.mapper.StoreMapper;
import com.mohit.modal.Store;
import com.mohit.modal.User;
import com.mohit.payload.dto.StoreDTO;
import com.mohit.payload.dto.UserDTO;
import com.mohit.payload.response.ApiResponse;
import com.mohit.service.StoreService;
import com.mohit.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;
    private final UserService userService;

    //  Create Store
    @PostMapping
    public ResponseEntity<StoreDTO> createStore(@Valid @RequestBody StoreDTO storeDto,
                                                @RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(storeService.createStore(storeDto, user));
    }

    //  Get Store by ID
    @GetMapping("/{id}")
    public ResponseEntity<StoreDTO> getStoreById(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(storeService.getStoreById(id));
    }

    //  Update Store
    @PutMapping("/{id}")
    public ResponseEntity<StoreDTO> updateStore(
            @PathVariable Long id,
            @RequestBody StoreDTO storeDto)
            throws ResourceNotFoundException,
            UserException {
        return ResponseEntity.ok(storeService.updateStore(id, storeDto));
    }

    //  Delete Store
    @DeleteMapping()
    public ResponseEntity<ApiResponse> deleteStore()
            throws ResourceNotFoundException, UserException {
        storeService.deleteStore();
        return ResponseEntity.ok(new ApiResponse("store deleted successfully"));
    }

    // Get Stores by Admin User ID
    @GetMapping("/admin")
    public ResponseEntity<StoreDTO> getStoresByAdminId() throws UserException {
        Store store=storeService.getStoreByAdminId();
        return ResponseEntity.ok(StoreMapper.toDto(store));
    }

    @GetMapping("/employee")
    public ResponseEntity<StoreDTO> getStoresByEmployee() throws UserException {
        StoreDTO store=storeService.getStoreByEmployee();
        return ResponseEntity.ok(store);
    }

    @GetMapping("/{storeId}/employee/list")
    @PreAuthorize("hasAnyAuthority('ROLE_STORE_MANAGER', 'ROLE_STORE_ADMIN')")
    public ResponseEntity<List<UserDTO>> getStoreEmployeeList(
            @PathVariable Long storeId) throws UserException {
        List<UserDTO> users=storeService.getEmployeesByStore(storeId);
        return ResponseEntity.ok(users);
    }

    @PostMapping("/add/employee")
    @PreAuthorize("hasAnyAuthority('STORE_MANAGER','STORE_ADMIN')")
    public ResponseEntity<UserDTO> addEmployee(
            @RequestBody UserDTO userDTO) throws UserException {
        UserDTO user=storeService.addEmployee(null, userDTO);
        return ResponseEntity.ok(user);
    }

//    super admin action

    //  Get All Stores (without pagination)
    @GetMapping
    public ResponseEntity<List<StoreDTO>> getAllStores(
            @RequestParam(required = false) StoreStatus status
    ) {
        return ResponseEntity.ok(storeService.getAllStores(status));
    }

    @PutMapping("/{storeId}/moderate")
    public StoreDTO moderateStore(
            @PathVariable Long storeId,
            @RequestParam StoreStatus action
    ) throws ResourceNotFoundException {
        return storeService.moderateStore(storeId, action);
    }
}
