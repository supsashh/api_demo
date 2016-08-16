package com.agold;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Data Access Layer for the User model.
 * @author Alex
 *
 */
@Transactional
public interface UserRepository extends JpaRepository<User, Long>{

}
