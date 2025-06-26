package com.jobtrack.service;

import com.jobtrack.model.User;
import com.jobtrack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserServiceImpl 是 UserService 介面的實作類別，
 * 處理與使用者資料相關的商業邏輯，例如新增、查詢、更新與刪除。
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 取得所有使用者
     * @return 使用者清單
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * 根據 ID 取得使用者（找不到會拋出例外）
     * @param id 使用者的主鍵 ID
     * @return 對應的 User 物件
     */
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    /**
     * 建立新使用者
     * @param user 從前端收到的 User 物件
     * @return 儲存後（含 id）的使用者物件
     */
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    /**
     * 更新指定 ID 的使用者資料（找不到會拋出例外）
     * @param id 要更新的使用者 ID
     * @param user 新資料（username, email）
     * @return 更新後的 User 物件
     */
    @Override
    public User updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        return userRepository.save(existingUser);
    }

    /**
     * 刪除使用者（找不到會拋出例外）
     * @param id 要刪除的使用者 ID
     */
    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        userRepository.delete(user);
    }
}
