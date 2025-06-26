package com.jobtrack.controller;

import com.jobtrack.model.User;
import com.jobtrack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController // 表示這是一個 RESTful Controller，回傳資料通常為 JSON 格式
@RequestMapping("/api/users")   // 指定這個 Controller 的基礎路徑
public class UserController {

    @Autowired  // 自動注入 UserService，供本類別使用
    private UserService userService;

    /**
     * 取得所有使用者資料
     * GET /api/users
     */
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * 根據 ID 取得單一使用者資料
     * GET /api/users/{id}
     */
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    /**
     * 新增使用者
     * POST /api/users
     * @param user 由前端傳入的使用者 JSON 物件，會自動映射為 User 實體
     * @return 建立成功的使用者
     */
    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    /**
     * 新增使用者
     * POST /api/users
     * @param user 由前端傳入的使用者 JSON 物件，會自動映射為 User 實體
     * @return 建立成功的使用者
     */
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    /**
     * 刪除指定 ID 的使用者
     * DELETE /api/users/{id}
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
