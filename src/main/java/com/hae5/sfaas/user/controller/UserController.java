package com.hae5.sfaas.user.controller;

import com.hae5.sfaas.user.dto.request.UserRoleEditRequest;
import com.hae5.sfaas.user.dto.response.UserRoleEditResponse;
import com.hae5.sfaas.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        UserRoleEditResponse response = userService.editUserRole(userId, request);
        return ResponseEntity.ok().body(response);
    }
}
