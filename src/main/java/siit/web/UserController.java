package siit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import siit.ValidationException;
import siit.model.User;
import siit.sevices.UserService;



@Controller
public class UserController {

@Autowired
UserService userService;

    @RequestMapping(method = RequestMethod.GET, path = "/displayUsers")
    public ModelAndView displayAllUsers() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Response");
        mav.addObject("users",userService.getAllUsers());
        return mav;
    }

    @RequestMapping (method= RequestMethod.GET,path = "/displayUsers/addUser")
    public ModelAndView  displayUserAddForm(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("user-add");
       // mav.addObject("users",userService.insertNewUser(user,password));
        return mav;
    }

    @RequestMapping (method= RequestMethod.POST,path = "/displayUsers/addUser")
    public ModelAndView addUser(@RequestParam String name,@RequestParam String password){
        ModelAndView mav = new ModelAndView();
        try {
            userService.insertNewUser(name,password);
            mav.setViewName("redirect:/customers");
        } catch (ValidationException ex) {
            mav.setViewName("user-add");
            mav.addObject("error", ex.getMessage());
        }
        mav.setViewName("redirect:/displayUsers");
        return mav;
    }

    @RequestMapping (method=RequestMethod.GET,path="/displayUsers/{user.name}/deleteUser")
    public ModelAndView deleteUser(@PathVariable ("user.name") String name ){
        ModelAndView mav = new ModelAndView();
        userService.deleteUser(name);
        mav.setViewName("Response");
        mav.addObject("users",userService.getAllUsers());
        return mav;
    }

}
