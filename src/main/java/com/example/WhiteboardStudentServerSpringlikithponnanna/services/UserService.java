package com.example.WhiteboardStudentServerSpringlikithponnanna.services;

import com.example.WhiteboardStudentServerSpringlikithponnanna.model.User;
import com.example.WhiteboardStudentServerSpringlikithponnanna.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "*", allowCredentials= "true", allowedHeaders ="*")
public class UserService  {

  //private User alice = new User((long) 123, "alice", "pass123", "Alice", "Wonderland", "FACULTY","def@gmail.com", "816225");
  //private User bob = new User((long) 234, "bob", "pass245", "Bob", "Marley", "FACULTY","new@gmail.com", "123141");
  //protected ArrayList <User> users = new ArrayList <>(Arrays.asList(alice, bob));

  @Autowired
  UserRepository userRepository;


  @PostMapping("/api/register")
  public User register(@RequestBody User user,
                       HttpSession session) {

//    users.add(user);
//    return user;
    for (User u:userRepository.findAll()){
      if(u.getUsername()!=null) {
        if (u.getUsername().equals(user.getUsername())) {
          User dup = new User();
          dup.setUsername("duplicate_777");
          dup.setUserId((long) 1111);
          return dup;
        }
      }
    }

    int randomInt = (int)(1000.0 * Math.random());
    user.setUserId((long) randomInt);
    User test = userRepository.save(user);


    session.setAttribute("currentUser", test);

    return test;


  }

  @GetMapping("/api/profile")
  public User profile(HttpSession session) {
    User currentUser = (User)
            session.getAttribute("currentUser");
    if(currentUser !=null){
      return userRepository.findById(currentUser.getUserId()).get();
    }
    return new User();
  }



  @PostMapping("/api/login")
  public User login(@RequestBody User credentials,
                      HttpSession session) {
    for (User user : userRepository.findAll()) {
      if( user.getUsername().equals(credentials.getUsername())
              && user.getPassword().equals(credentials.getPassword())) {
        session.setAttribute("currentUser", user);
        return user;
      }
    }
    return new User();
  }

  @PostMapping("/api/logout")
  public void logout
          (HttpSession session) {
    session.removeAttribute("currentUser");
    session.invalidate();
  }


  @GetMapping("/api/users")
  public ArrayList <User> findAllUsers(HttpSession session) {

    return (ArrayList<User>) userRepository.findAll();
  }

  @GetMapping("/api/users/{id}")
  public User findUserById(@PathVariable("id") Long id,  HttpSession session) {
  return userRepository.findById(id).get();
  }

  @PostMapping("/api/users")
  public User createUser(@RequestBody User user) {

    return userRepository.save(user);
  }

  @DeleteMapping("/api/delete/{userId}")
  public void deleteUser(@PathVariable("userId") Long id) {

    userRepository.deleteById(id);
  }

  @PutMapping("/api/update/{id}")
  public User updateUser(@PathVariable("id") Long id, @RequestBody User user) {
    User dbUser = userRepository.findById(id).get();

      if (dbUser.getUserId().equals(id)) {
        if (user.getPassword() != null) {
          dbUser.setPassword(user.getPassword());
        }
        if (user.getFirstName() != null) {
          dbUser.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null) {
          dbUser.setLastName(user.getLastName());
        }
        if (user.getUsername() != null) {
          dbUser.setUsername(user.getUsername());
        }
        if (user.getRole() != null) {
          dbUser.setRole(user.getRole());
        }
        if (user.getEmail() !=null){
          dbUser.setEmail(user.getEmail());
        }
        if (user.getPhoneNo() !=null){
          dbUser.setPhoneNo(user.getPhoneNo());
        }


        return  userRepository.save(dbUser);

      }

    return new User();
  }

  @PostMapping("/api/user/search")
  public ArrayList <User> searchUsers(@RequestBody User user) {
    ArrayList <User> usernameList = new ArrayList <>();
    ArrayList <User> passwordList = new ArrayList <>();
    ArrayList <User> firstNameList = new ArrayList <>();
    ArrayList <User> lastNameList = new ArrayList <>();
    ArrayList <User> roleList = new ArrayList <>();

    ArrayList <User> tempUser = new ArrayList <>();

    //tempUser.addAll(users);

    if (!user.getUsername().equals("")) {
      for (int i = 0; i < tempUser.size(); i++) {
        if (tempUser.get(i).getUsername().equals(user.getUsername())) {
          usernameList.add(tempUser.get(i));
        }
      }
      tempUser = usernameList;
    }


    if (!user.getPassword().equals("")) {
      for (int i = 0; i < tempUser.size(); i++) {
        if (tempUser.get(i).getPassword().equals(user.getPassword())) {
          passwordList.add(tempUser.get(i));
        }
      }
      tempUser = passwordList;
    }

    if (!user.getFirstName().equals("")) {
      for (int i = 0; i < tempUser.size(); i++) {
        if (tempUser.get(i).getFirstName().equals(user.getFirstName())) {
          firstNameList.add(tempUser.get(i));
        }
      }
      tempUser = firstNameList;
    }


    if (!user.getLastName().equals("")) {
      for (int i = 0; i < tempUser.size(); i++) {
        if (tempUser.get(i).getLastName().equals(user.getLastName())) {
          lastNameList.add(tempUser.get(i));
        }
      }
      tempUser = lastNameList;
    }


    if (!user.getRole().equals("")) {
      for (int i = 0; i < tempUser.size(); i++) {
        if (tempUser.get(i).getRole().equals(user.getRole())) {
          roleList.add(tempUser.get(i));
        }
      }
      tempUser = roleList;
    }


    //System.out.println(tempUser);

    return tempUser;
  }

}
