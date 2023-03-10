package com.teksocial.application.runners;

import com.teksocial.application.models.FriendsModel;
import com.teksocial.application.models.UserModel;
import com.teksocial.application.repositories.FriendsRequest;
import com.teksocial.application.repositories.UserRepository;
import com.teksocial.application.utility.GlobalUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LoaderData implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendsRequest friendsRequest;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Adding data to Database");
        UserModel userModel1 = new UserModel();
        userModel1.setEmail("richa@gmail.com");
        userModel1.setUserAbout("I am Issac Newton");
        userModel1.setUserCreatedAt(GlobalUtilities.formatDateAndTime(LocalDateTime.now()));
        userModel1.setUserUpdatedAt(GlobalUtilities.formatDateAndTime(LocalDateTime.now()));
        userModel1.setUserAge("23");
        userModel1.setUserCity("Gwalior");
        userModel1.setUserContact("123456789");
        userModel1.setUserFirstName("Richa");
        userModel1.setUserLastName("Agarwal");
        userModel1.setUserName("richa@123");
        userModel1.setUserGender(GlobalUtilities.FEMALE_GENDER);
        userModel1.setUserState("Madhya Pradesh");
        userRepository.save(userModel1);

        System.out.println();
        System.out.println();
        System.out.println("ADDED TO RICHA DATABASE");
        System.out.println();
        System.out.println();
        UserModel userModel2 = new UserModel();
        userModel2.setEmail("riya@gmail.com");
        userModel2.setUserAbout("I am Issac Newton");
        userModel2.setUserCreatedAt(GlobalUtilities.formatDateAndTime(LocalDateTime.now()));
        userModel2.setUserUpdatedAt(GlobalUtilities.formatDateAndTime(LocalDateTime.now()));
        userModel2.setUserAge("23");
        userModel2.setUserCity("Gwalior");
        userModel2.setUserContact("123456789");
        userModel2.setUserFirstName("Riya");
        userModel2.setUserLastName("Agarwal");
        userModel2.setUserName("riya@123");
        userModel2.setUserGender(GlobalUtilities.FEMALE_GENDER);
        userModel2.setUserState("Madhya Pradesh");
        userRepository.save(userModel2);
        System.out.println();
        System.out.println();
        System.out.println("ADDED TO RIYA DATABASE");
        System.out.println();
        System.out.println();
        UserModel userModel3 = new UserModel();
        userModel3.setEmail("ritik@gmail.com");
        userModel3.setUserAbout("I am Issac Newton");
        userModel3.setUserCreatedAt(GlobalUtilities.formatDateAndTime(LocalDateTime.now()));
        userModel3.setUserUpdatedAt(GlobalUtilities.formatDateAndTime(LocalDateTime.now()));
        userModel3.setUserAge("23");
        userModel3.setUserCity("Gwalior");
        userModel3.setUserContact("123456789");
        userModel3.setUserFirstName("Ritik");
        userModel3.setUserLastName("Sharma");
        userModel3.setUserName("ritik@123");
        userModel3.setUserGender(GlobalUtilities.FEMALE_GENDER);
        userModel3.setUserState("Madhya Pradesh");
        userRepository.save(userModel3);
        System.out.println();
        System.out.println();
        System.out.println("ADDED TO RITIK DATABASE");
        System.out.println();
        System.out.println();
        FriendsModel friendsModel = new FriendsModel();
        friendsModel.setFriendRequestToId(1L);
        friendsModel.setFriendRequestSentById(2L);
        friendsModel.setStatus(GlobalUtilities.FRIEND_PENDING_REQUEST);
        friendsRequest.save(friendsModel);



    }
}
