package com.multi.semo.auth.domain;

import org.springframework.data.repository.CrudRepository;

public interface AuthRepository extends CrudRepository<RefreshToken, Long> {
}
