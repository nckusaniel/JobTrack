package com.jobtrack.model;

import jakarta.persistence.*;// 匯入 JPA 所需要註解
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * User 實體類別，對應到資料庫中的 users 資料表。
 */
@Entity // 宣告這是一個 JPA Entity（資料表對應的類別）
@Table(name = "users") // 指定資料表名稱為 users（避免使用 PostgreSQL 的保留字 "user"）
public class User {

    @Id // 主鍵欄位
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 主鍵自動遞增（依資料庫支援）
    private Long id;

    @NotBlank(message = "使用者名稱不可為空") // 驗證：不可為 null 且不可為空白字串
    private String username;

    @NotBlank(message = "Email 不可為空")
    @Email(message = "請輸入正確的 Email 格式") // 驗證：必須符合 Email 格式
    private String email;

    /**
     * 無參數建構子（JPA 規定一定要有）
     */
    public User() {}

    /**
     * 自訂建構子，方便手動建立 User 實例
     */
    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    // Getters and Setters

    /**
     * 取得 ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 設定 ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 取得使用者名稱
     */
    public String getUsername() {
        return username;
    }

    /**
     * 設定使用者名稱
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * 取得 Email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 設定 Email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
