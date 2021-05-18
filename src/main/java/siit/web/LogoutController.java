package siit.web;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import siit.model.User;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/logout")
public class LogoutController {
 @RequestMapping(method = RequestMethod.GET )
  protected ModelAndView performLogout(HttpSession session){
       ModelAndView mav = new ModelAndView();
       if(session != null){
           session.removeAttribute("logged_user");
           session.invalidate();
           mav.setViewName("/login");
       }
       return  mav;
   }
}
