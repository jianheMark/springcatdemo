package jian.he.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping({"index.html","","/","index"})
    public String index(){
        return "index";
    }

    @GetMapping("oups")
    public String ooops(){
        return "notimplemented";
    }



}
