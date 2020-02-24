package com.jxa.blog.web.admin;

import com.jxa.blog.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class LoginController {

    @GetMapping()
    public String index(){
//        String blog = null;
/*        if (blog == null){
            throw new NotFoundException("该内容消失在了千里之外了");
        }*/
        System.out.println("%%%%%%%%%%%%%%%index%%%%%%%%%%%%%");
        return "index";
    }
}
