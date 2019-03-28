package com.example.WhiteboardStudentServerSpringlikithponnanna.repositories;

import com.example.WhiteboardStudentServerSpringlikithponnanna.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository
        extends CrudRepository<User, Long> {
    @Query("SELECT user from User user WHERE user.username=:username AND user.password=:password")
    public List<User> findUserByUsername(@Param("username") String username, @Param("password") String password);

}