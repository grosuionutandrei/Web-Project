package siit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import siit.model.User;
import siit.sevices.OrderService;
import siit.sevices.UserService;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class RandomController {
    @Autowired
    OrderService orderService ;
    @Autowired
    UserService userService;
    @GetMapping("/api/value")
    public String displayValue(@RequestParam("customerId") int customerId){
        return "This is the value" + orderService.getValue(customerId);
    }

    @GetMapping ("/api/displayUsers")
    public List<User> displayUser (){
        return userService.getAllUsers();
    }

}
