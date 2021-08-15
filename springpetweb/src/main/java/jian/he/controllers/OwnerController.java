package jian.he.controllers;

import jian.he.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OwnerController {
//    private final OwnerService ownerService;
//
//    public OwnerController(OwnerService ownerService) {
//        this.ownerService = ownerService;
//    }
    @Autowired
    OwnerService ownerService;

    @GetMapping({"/owners","/owners/index","/owners/index.html"})

    public String listOwners(Model model){
        model.addAttribute("owners",ownerService.findAll());
        return "owners/index";
    }
}
