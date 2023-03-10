package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository3;

    public User createUser(String username, String password)
    {
        //Create new User entity
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName("test");
        user.setLastName("test");

        // After setting all the attributes, we will save the user into database with the help of JPA
        userRepository3.save(user);
        return user;
    }

    public void deleteUser(int userId)
    {
        userRepository3.deleteById(userId);
    }

    public User updateUser(Integer id, String password)
    {
        // we are passing id and password in RequestParam
        // Since .save function will update the passed parameters and set all remaining parameters as null or default
        // Now to prevent this kind of data loss
        // First fetch old data(Original data)
        User oldUser = userRepository3.findById(id).get();   // Got user body
        oldUser.setPassword(password);   // Password is updated now

        //save the user into database with the help of JPA
        userRepository3.save(oldUser);
        return oldUser;
    }
}
