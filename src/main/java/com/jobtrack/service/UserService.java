package com.jobtrack.service;

import com.jobtrack.model.User;

import java.util.List;

/**
 * UserService 是 User 資料的商業邏輯層介面。
 * 定義了與使用者相關的操作方法，由實作類別負責實際邏輯實作。
 */
public interface UserService {

    /**
     * 取得所有使用者資料
     * @return 使用者清單
     */
    List<User> getAllUsers();

    /**
     * 根據 ID 取得單一使用者
     * @param id 使用者的主鍵 ID
     * @return 對應的 User 實體，若找不到通常在實作中會回傳 null 或拋例外
     */
    User getUserById(Long id);

    /**
     * 新增一筆使用者資料
     * @param user 前端傳入的使用者資料（尚未存入資料庫）
     * @return 儲存後的使用者（包含生成的 ID）
     */
    User createUser(User user);

    /**
     * 更新使用者資料
     * @param id 要更新的使用者 ID
     * @param user 前端提供的更新內容
     * @return 更新後的使用者實體
     */
    User updateUser(Long id, User user);

    /**
     * 刪除指定使用者
     * @param id 使用者 ID
     */
    void deleteUser(Long id);

}
