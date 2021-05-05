package controller;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.impl.UserServiceImpl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@SessionAttributes("user")
public class UserController {
    @Autowired
    UserServiceImpl userService;
    @ModelAttribute("user")
    public User setUpUser(){
        return new User();
    }
    @GetMapping("/login")
    public String login(@CookieValue(value = "setUser",defaultValue = "")String setUser, Model model){
        Cookie cookie = new Cookie("setUser", setUser);
        model.addAttribute("cookieValue",cookie);
        return "/user/login";
    }
    @PostMapping("/login/user")
    public String doLogin(@ModelAttribute("setUser") User user, Model model, @CookieValue(value = "setUser",defaultValue = "") String setUser,
                          HttpServletRequest request, HttpServletResponse response){
        List<User> userList = userService.findAll();
        for (int i=0;i<userList.size();i++){
            if(user.getEmail().equals(userList.get(i).getEmail())&&user.getPassword().equals(userList.get(i).getPassword())){
                setUser = user.getEmail();
                //Set cookie to Response
                Cookie cookie = new Cookie("setUser",setUser);
                cookie.setMaxAge(100);
                response.addCookie(cookie);
                //Get cookie with Resquest
                Cookie[] cookies = request.getCookies();
                for(Cookie ck: cookies){
                    if(ck.getName().equals("setUser")){
                        model.addAttribute("cookieValue",ck);
                        break;
                    } else {
                        ck.setValue("");
                        model.addAttribute("cookieValue",ck);
                        break;
                    }
                }
                model.addAttribute("status", "Login success! Welcome: ");
                model.addAttribute("cookieValue",cookie);
            } else {
                model.addAttribute("status", "Login failed! Please try again!!");
                Cookie cookie = new Cookie("setUser", setUser);
                model.addAttribute("cookieValue",cookie);
            }
        }

        return "/user/login";
    }
}
