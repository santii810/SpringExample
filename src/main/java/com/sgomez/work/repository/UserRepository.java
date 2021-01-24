package com.sgomez.work.repository;

import com.sgomez.work.entities.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository  extends PagingAndSortingRepository<User, String> {
}
