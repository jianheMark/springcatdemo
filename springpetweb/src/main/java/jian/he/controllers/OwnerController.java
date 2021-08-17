package jian.he.controllers;

import jian.he.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("owners")
public class OwnerController {
//    private final OwnerService ownerService;
//
//    public OwnerController(OwnerService ownerService) {
//        this.ownerService = ownerService;
//    }
    @Autowired
    OwnerService ownerService;
//    http://localhost:8080/owners/find

    @GetMapping({"","/","/index","/index.html","/find"})
    public String listOwners(Model model){
        model.addAttribute("owners",ownerService.findAll());
        return "owners/index";
    }

//    @GetMapping("/find")
//    public String findOwner(){
//        return "notimplemented";
//    }

}
