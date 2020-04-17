package com.bluesky.myspring.repository;

import com.bluesky.myspring.entity.AuthorUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorUserRepository extends JpaRepository<AuthorUser,String> {
}
