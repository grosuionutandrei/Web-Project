package siit.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siit.ValidationException;
import siit.db.UserDao;
import siit.model.User;

import javax.servlet.http.HttpSession;
import java.util.List;


@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public List<User> getAllUsers(){
        List<User>  users = userDao.getUsers();
        return users;
    }

    public User  getUser(String user,List<User> users){
        User userTemp =new User();
        for(User usr: users){
            if(usr.getName().equals(user)){
                userTemp=usr;
            }
        }
        return userTemp;
    }
    public void insertNewUser(String name,String password){
        List<User> usersList = userDao.getUsers();
        User user = new User();
        if(password.matches("\\w{2}\\d{3}\\w{1}\\d{2}")){
            user.setPassword(password);
            for(User user1:usersList){
                if(name!=user1.getName()){
                    user.setName(name);
                }
            }
        }else{
            throw new ValidationException("Password do not match");
        }
        userDao.insertUser(user);
    }

    public void deleteUser(String name) {
        userDao.deleteUser(name);
    }

}
