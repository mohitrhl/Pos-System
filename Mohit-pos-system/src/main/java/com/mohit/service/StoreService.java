package com.mohit.service;

import com.mohit.domain.StoreStatus;
import com.mohit.exception.ResourceNotFoundException;
import com.mohit.exception.UserException;
import com.mohit.modal.Store;
import com.mohit.modal.User;
import com.mohit.payload.dto.StoreDTO;
import com.mohit.payload.dto.UserDTO;

import java.util.List;

public interface StoreService {
    StoreDTO createStore(StoreDTO storeDto, User user);
    StoreDTO getStoreById(Long id) throws ResourceNotFoundException;
    List<StoreDTO> getAllStores(StoreStatus status);
    Store getStoreByAdminId() throws UserException;
    StoreDTO getStoreByEmployee() throws UserException;
    StoreDTO updateStore(Long id, StoreDTO storeDto) throws ResourceNotFoundException, UserException;
    void deleteStore() throws ResourceNotFoundException, UserException;
    UserDTO addEmployee(Long id, UserDTO userDto) throws UserException;
    List<UserDTO> getEmployeesByStore(Long storeId) throws UserException;

    StoreDTO moderateStore(Long storeId, StoreStatus action) throws ResourceNotFoundException;
}
