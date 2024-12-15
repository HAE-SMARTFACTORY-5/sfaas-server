package com.hae5.sfaas.user.mapper;

import com.hae5.sfaas.user.dto.response.UserResponse;
import com.hae5.sfaas.user.enums.UserRole;
import com.hae5.sfaas.user.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {
    void save(User user);
    void deleteAll();
    List<User> findAll();
    Optional<User> findById(Long userId);
    Optional<User> findByEmployeeId(String employeeId);
    void deleteById(Long userId);
    void updateRole(Long userId, UserRole role);

    List<UserResponse> findByKeyword(String keyword, String type, Integer size, Long offset);

    long countByKeyword(String keyword, String type);
}
