package com.querydsl.chapternineteen.repositories;

import com.querydsl.chapternineteen.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}