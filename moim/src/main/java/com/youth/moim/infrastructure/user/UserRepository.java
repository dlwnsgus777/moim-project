package com.youth.moim.infrastructure.user;

import com.youth.moim.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query("select u from User u where u.id = :loginId")
    Optional<User> findByLoginId(@Param("loginId") String loginId);
}
