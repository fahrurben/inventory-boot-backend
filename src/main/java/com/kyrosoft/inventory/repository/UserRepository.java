package com.kyrosoft.inventory.repository;

import com.kyrosoft.inventory.model.User;

public interface UserRepository extends BaseRepository<User, Long> {
    User findByUsername(String username);
}
