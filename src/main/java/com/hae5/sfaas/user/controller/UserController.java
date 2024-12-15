package com.hae5.sfaas.user.controller;

import com.hae5.sfaas.common.response.PaginationResponse;
import com.hae5.sfaas.user.dto.request.UserRoleEditRequest;
import com.hae5.sfaas.user.dto.response.UserResponse;
import com.hae5.sfaas.user.dto.response.UserRoleEditResponse;
import com.hae5.sfaas.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/role/{userId}")
    public ResponseEntity<UserRoleEditResponse> editUserRole(@PathVariable Long userId, @RequestBody UserRoleEditRequest request) {
        UserRoleEditResponse response = userService.updateUserRole(userId, request);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/search")
    public ResponseEntity<PaginationResponse<UserResponse>> searchUsers(@RequestParam(required = false) String keyword,
                                                                              @RequestParam(required = false) String type,
                                                                              Pageable pageable) {
        PaginationResponse<UserResponse> response = userService.searchUser(keyword, type, pageable);
        return ResponseEntity.ok().body(response);
    }
}
