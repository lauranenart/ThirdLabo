package com.project.thirdlabo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    public List<UserModel> getAllUsers(){
        return (List<UserModel>) userRepo.findAll();
    }

    public UserModel save(UserModel user) {
        return userRepo.save(user);
    }

    public UserModel getUser(Integer id) {
        return userRepo.findById(id).get();
    }
}
