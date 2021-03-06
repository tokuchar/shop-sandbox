package com.oncors.repo;

import com.oncors.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    User save(User user);

    @Query("select u from User u where u.username = ?1")
    User findUserByUsername(String username);
}
