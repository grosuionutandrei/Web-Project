package siit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import siit.db.UserDao;
import siit.model.User;
import siit.sevices.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {

@Autowired
    UserService userservice;
    private int value = 0;

    @RequestMapping(method = RequestMethod.GET)
    protected String displayLoginForm() {
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    protected ModelAndView performLogin(HttpSession session,
                                        @RequestParam String user, @RequestParam String password) {
        List<User> users = userservice.getAllUsers();
        ModelAndView mav = new ModelAndView();
        User temp = new User(user,password);
        User user1 = userservice.getUser(user,users);
        if (user1.equals(temp)) {
            session.setAttribute("logged_user", user);
                mav.setViewName("redirect:/customers");
            } else {
                value++;
                String error = "User and password do not match! " + value;
                mav.setViewName("login");
                mav.addObject("error", error);
            }

        return mav;
    }
}
