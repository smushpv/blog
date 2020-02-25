package com.jxa.blog.web.admin;
import com.jxa.blog.po.User;
import com.jxa.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @GetMapping
    public String loginPage(){
        return "admin/login";
    }
    @PostMapping("/login")
    public String login(String username,
                        String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        /*@RequestParam()
        * 请求中如果没有对应属性，就必须添加
        * */
        User user = userService.getOneUser(username,password);
        if (user != null){
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        }else {
            attributes.addFlashAttribute("message","用户名或密码错误");
            return "redirect:/admin";
        }
    }

    /*登出*/
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "admin/login";
    }
}
