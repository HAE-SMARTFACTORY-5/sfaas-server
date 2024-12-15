package com.hae5.sfaas.user.mapper;

import com.hae5.sfaas.SfaasApplicationTests;
import com.hae5.sfaas.user.dto.response.UserResponse;
import com.hae5.sfaas.user.enums.UserRole;
import com.hae5.sfaas.user.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class UserMapperTest extends SfaasApplicationTests {
    
    @Autowired
    private UserMapper userMapper;
    
    @AfterEach
    public void clear() {
        userMapper.deleteAll();
    }

    @DisplayName("User 저장")
    @Test
    public void saveTest() {
        //given
        User user = User.builder()
                .name("5조")
                .employId("employeeId")
                .password("password")
                .role(UserRole.MEMBER)
                .build();

        //when
        userMapper.save(user);

        //then
        assertThat(userMapper.findAll().size()).isEqualTo(1);
    }

    @DisplayName("User 전체 삭제")
    @Test
    public void deleteAllTest() {
        //given
        User user = User.builder()
                .name("5조")
                .employId("employeeId1")
                .password("password")
                .role(UserRole.MEMBER)
                .build();
        userMapper.save(user);

        //when
        userMapper.deleteAll();

        //then
        assertThat(userMapper.findAll().size()).isEqualTo(0);
    }

    @DisplayName("User 전체 조회")
    @Test
    public void findAllTest() {
        //given
        User user1 = User.builder()
                .name("5조")
                .employId("employeeId1")
                .password("password")
                .role(UserRole.MEMBER)
                .build();
        User user2 = User.builder()
                .name("5조")
                .employId("employeeId2")
                .password("password")
                .role(UserRole.MEMBER)
                .build();
        userMapper.save(user1);
        userMapper.save(user2);

        //when
        List<User> users = userMapper.findAll();

        //then
        assertThat(users.size()).isEqualTo(2);
    }

    @DisplayName("UserId로 조회")
    @Test
    public void findByIdTest() {
        //given
        String employeeId = "employeeId";
        User newUser = User.builder()
                .name("5조")
                .employId("employeeId")
                .password("password")
                .role(UserRole.MEMBER)
                .build();

        userMapper.save(newUser);

        //when
        User user = userMapper.findById(newUser.getUserId()).orElse(null);

        //then
        assertThat(user).isNotNull();
        System.out.println(user.toString());
        assertThat(user.getEmployId()).isEqualTo(employeeId);
    }

    @DisplayName("EmployeeId로 조회")
    @Test
    public void findByEmployeeIdTest() {
        //given
        String employeeId = "employeeId";
        User newUser = User.builder()
                .name("5조")
                .employId(employeeId)
                .password("password")
                .role(UserRole.MEMBER)
                .build();
        userMapper.save(newUser);

        //when
        User user = userMapper.findByEmployeeId(employeeId).orElse(null);

        //then
        assertThat(user).isNotNull();
        assertThat(user.getUserId()).isEqualTo(newUser.getUserId());
    }

    @DisplayName("사용자 Id로 사용자 삭제")
    @Test
    public void deleteByIdTest() {
        //given
        String employeeId = "employeeId";
        User newUser = User.builder()
                .name("5조")
                .employId(employeeId)
                .password("password")
                .role(UserRole.MEMBER)
                .build();
        userMapper.save(newUser);

        //when
        userMapper.deleteById(newUser.getUserId());

        //then
        assertThat(userMapper.findAll().size()).isEqualTo(0);
    }

    @DisplayName("사용자 권한 변경")
    @Test
    public void updateRoleTest() {
        //given
        UserRole expectRole = UserRole.ADMIN;
        User newUser = User.builder()
                .name("5조")
                .employId("employeeId")
                .password("password")
                .role(UserRole.MEMBER)
                .build();
        userMapper.save(newUser);

        //when
        userMapper.updateRole(newUser.getUserId(), expectRole);

        //then
        User user = userMapper.findById(newUser.getUserId()).orElse(null);
        assertThat(user).isNotNull();
        assertThat(user.getRole()).isEqualTo(expectRole);
    }

    @DisplayName("사용자 검색")
    @Test
    public void findByKeywordTest() {
        //given
        User user1 = User.builder()
                .name("아산")
                .employId("employeeId1")
                .password("password")
                .role(UserRole.MEMBER)
                .build();

        User user2 = User.builder()
                .name("아리")
                .employId("employeeId2")
                .password("password")
                .role(UserRole.MEMBER)
                .build();

        userMapper.save(user1);
        userMapper.save(user2);

        //when
        List<UserResponse> users = userMapper.findByKeyword("아산", null, 5, 0L);

        //then
        assertThat(users.size()).isEqualTo(1);
    }

    @DisplayName("검색한 사용자 수 조회")
    @Test
    public void countByKeywordTest() {
        //given
        User user1 = User.builder()
                .name("아산")
                .employId("employeeId1")
                .password("password")
                .role(UserRole.MEMBER)
                .build();

        User user2 = User.builder()
                .name("아리")
                .employId("employeeId2")
                .password("password")
                .role(UserRole.MEMBER)
                .build();

        userMapper.save(user1);
        userMapper.save(user2);

        long searchCount = userMapper.countByKeyword("아산", null);

        //then
        assertThat(searchCount).isEqualTo(1);
    }


}
