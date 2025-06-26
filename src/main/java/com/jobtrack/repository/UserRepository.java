package com.jobtrack.repository;

import com.jobtrack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 可以加自訂查詢方法，現在先用 JpaRepository 預設功能即可
}
