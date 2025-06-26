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

    // 使用 Mockito 模擬 UserRepository 的行為，不會連接真實資料庫
    @Mock
    private UserRepository userRepository;

    // 將模擬的 UserRepository 注入到 UserServiceImpl 中，方便測試 UserServiceImpl
    @InjectMocks
    private UserServiceImpl userService;

    // 每個測試方法執行前呼叫，初始化 Mockito 的模擬物件
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // 測試 UserService.getAllUsers() 是否能正確取得所有使用者列表
    @Test
    public void testGetAllUsers() {
        // 建立一個模擬使用者清單
        List<User> mockUsers = Arrays.asList(
                new User("user1", "a@example.com"),
                new User("user2", "b@example.com")
        );

        // 當 userRepository.findAll() 被呼叫時，回傳模擬使用者清單
        when(userRepository.findAll()).thenReturn(mockUsers);

        // 執行 service 的方法
        List<User> users = userService.getAllUsers();

        // 驗證回傳的清單大小是否為 2（符合模擬清單）
        assertEquals(2, users.size());

        // 確認 userRepository.findAll() 有被呼叫一次
        verify(userRepository, times(1)).findAll();
    }

    // 測試 UserService.getUserById(id) 在有找到使用者的情況下，能否回傳正確資料
    @Test
    public void testGetUserByIdFound() {
        // 建立一個模擬使用者物件
        User user = new User("user1", "a@example.com");

        // 當 userRepository.findById(1L) 被呼叫時，回傳該使用者的 Optional
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // 執行 service 的方法
        User found = userService.getUserById(1L);

        // 驗證結果不為 null，且 username 是預期的 "user1"
        assertNotNull(found);
        assertEquals("user1", found.getUsername());
    }

    // 測試 UserService.getUserById(id) 在找不到使用者的情況下，應回傳 null
    @Test
    public void testGetUserByIdNotFound() {
        // 當 userRepository.findById(1L) 被呼叫時，回傳空的 Optional
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // 執行 service 的方法
        User found = userService.getUserById(1L);

        // 驗證結果為 null，表示找不到該使用者
        assertNull(found);
    }

    // 你也可以依此模式繼續寫 createUser、updateUser、deleteUser 的測試方法
}
