package com.jobtrack.repository;

import com.jobtrack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserRepository 是用來操作 User 資料表的資料存取層。
 * 繼承 JpaRepository，可以自動取得 CRUD 方法，不用手動寫 SQL。
 */

@Repository // 告訴 Spring 這是一個資料存取元件（DAO 層）
public interface UserRepository extends JpaRepository<User, Long> {
    // 目前使用 JpaRepository 提供的基本 CRUD 方法，例如：
    // - findAll()
    // - findById(id)
    // - save(entity)
    // - deleteById(id)
    // 如未來需要客製化查詢，可在此新增：
    // 例如：Optional<User> findByEmail(String email);
}