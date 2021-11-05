package com.project.thirdlabo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    private EmailValidator emailValidator = new EmailValidator();
    private  PasswordChecker passwordChecker = new PasswordChecker();
    private PhoneValidator phoneValidator = new PhoneValidator();

    public List<UserModel> getAllUsers(){
        return (List<UserModel>) userRepo.findAll();
    }

    public UserModel save(UserModel user) {
        return userRepo.save(user);
    }

    public UserModel getUser(Integer id) {
        return userRepo.findById(id).get();
    }

    public boolean validateUser(UserModel user){
        List<String> emailErrors = validateEmail(user.getEmailAddress());
        List<String> phoneNumberErrors = validatePhoneNumber(user.getPhoneNumber());
        List<String> passwordErrors = validatePassword(user.getPassword());
        return emailErrors.equals(null) && phoneNumberErrors.equals(true) && passwordErrors.equals(null);
    }

    public List<String> validateEmail(String email){
        ArrayList<String> emailErrors = new ArrayList<>();
        if(!emailValidator.hasEtaSymbol(email)) emailErrors.add("Email is missing @ symbol");
        if(!emailValidator.hasCorrectSymbols(email)) emailErrors.add("Email contains invalid symbol");
        if(!emailValidator.hasCorrectDomain(email)) emailErrors.add("Invalid email");
        return emailErrors;
    }

    public List<String> validatePhoneNumber(String phoneNumber){
        ArrayList<String> phoneNumberErrors = new ArrayList<>();
        String prefix = phoneNumber.startsWith("8") ? "LT" : "US";

        if(!phoneValidator.hasOnlyNumbers(phoneNumber, prefix))
            phoneNumberErrors.add("Only numbers are accepted");
        if(!phoneValidator.numberMatchesPrefix(phoneNumber, prefix))
            phoneNumberErrors.add("Invalid phone number");
        return phoneNumberErrors;
    }

    public List<String> validatePassword(String password){
        ArrayList<String> passwordErrors = new ArrayList<>();
        if(!passwordChecker.hasSpecialChar(password))
            passwordErrors.add("At least one special character is a must");
        if(!passwordChecker.hasUpperCase(password))
            passwordErrors.add("At least one uppercase is a must");
        if(!passwordChecker.isNotShorter(password, 8))
            passwordErrors.add("Password must be at least 8 characters long");
        return passwordErrors;
    }
}
