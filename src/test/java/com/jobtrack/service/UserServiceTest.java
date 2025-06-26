package com.jobtrack.service;

import com.jobtrack.model.User;
import com.jobtrack.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        List<User> mockUsers = Arrays.asList(
                new User("user1", "a@example.com"),
                new User("user2", "b@example.com")
        );
        when(userRepository.findAll()).thenReturn(mockUsers);

        List<User> users = userService.getAllUsers();

        assertEquals(2, users.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testGetUserByIdFound() {
        User user = new User("user1", "a@example.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User found = userService.getUserById(1L);

        assertNotNull(found);
        assertEquals("user1", found.getUsername());
    }

    @Test
    public void testGetUserByIdNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        User found = userService.getUserById(1L);

        assertNull(found);
    }

    // ✅ 測試 createUser()
    @Test
    public void testCreateUser() {
        User user = new User("newUser", "new@example.com");
        when(userRepository.save(user)).thenReturn(user);

        User created = userService.createUser(user);

        assertNotNull(created);
        assertEquals("newUser", created.getUsername());
        verify(userRepository, times(1)).save(user);
    }

    // ✅ 測試 updateUser() 成功情況
    @Test
    public void testUpdateUserFound() {
        User oldUser = new User("old", "old@example.com");
        User newUser = new User("updated", "new@example.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(oldUser));
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        User updated = userService.updateUser(1L, newUser);

        assertEquals("updated", updated.getUsername());
        assertEquals("new@example.com", updated.getEmail());
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(oldUser);
    }

    // ✅ 測試 updateUser() 找不到使用者
    @Test
    public void testUpdateUserNotFound() {
        User newUser = new User("updated", "new@example.com");
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.updateUser(1L, newUser);
        });

        assertEquals("User not found with id: 1", exception.getMessage());
    }

    // ✅ 測試 deleteUser() 成功情況
    @Test
    public void testDeleteUserFound() {
        User user = new User("todelete", "del@example.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        userService.deleteUser(1L);

        verify(userRepository, times(1)).delete(user);
    }

    // ✅ 測試 deleteUser() 找不到使用者
    @Test
    public void testDeleteUserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.deleteUser(1L);
        });

        assertEquals("User not found with id: 1", exception.getMessage());
    }
}
