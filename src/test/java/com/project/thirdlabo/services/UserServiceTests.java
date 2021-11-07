package com.project.thirdlabo.services;

import com.project.thirdlabo.models.UserModel;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTests {

    @Mock
    UserModel userModelMock;

    @InjectMocks
    UserService userService;

    @Test
    public void testValidatePassword() {
        when(userModelMock.getPassword()).thenReturn("password");
        assertEquals(2, userService.validatePassword(userModelMock.getPassword()).size());
    }

    @Test
    public void testValidatePassword2() {
        when(userModelMock.getPassword()).thenReturn("Password123_");
        assertEquals(0, userService.validatePassword(userModelMock.getPassword()).size());
    }

    @Test
    public void testValidateEmail() {
        when(userModelMock.getEmailAddress()).thenReturn("petras.petraitis@gmail.com");
        assertEquals(0, userService.validateEmail(userModelMock.getEmailAddress()).size());
    }

    @Test
    public void testValidateEmail2() {
        when(userModelMock.getEmailAddress()).thenReturn("petras.~petraitisgmail");
        assertEquals(3, userService.validateEmail(userModelMock.getEmailAddress()).size());
    }

    @Test
    public void testValidatePhoneNumber() {
        when(userModelMock.getPhoneNumber()).thenReturn("862555a");
        assertEquals(2, userService.validatePhoneNumber(userModelMock.getPhoneNumber()).size());
    }

    @Test
    public void testValidatePhoneNumber2() {
        when(userModelMock.getPhoneNumber()).thenReturn("86255559a");
        ArrayList<String> errMsg = new ArrayList<>();
        errMsg.add("Only numbers are accepted");
        assertEquals(errMsg, userService.validatePhoneNumber(userModelMock.getPhoneNumber()));
    }
}
