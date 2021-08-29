package jian.he.controllers;

import jian.he.model.Owner;
import jian.he.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import javax.validation.Valid;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("owners")
public class OwnerController {
    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";
    private final OwnerService ownerService;
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/find")
    public String findOwners(Model model){
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model){
//        allow parameterless GET request for /owners to return all the records.
        if(owner.getLastName() == null) {
            //empty String signifies broadest possible search
            owner.setLastName("");
        }
        //find Owner by Last Name.
        System.out.println("||||| Total Owners:  "+ ownerService.findAll().size());
//        System.out.println("||||||" + ownerService.findById(1L).toString());
//        System.out.println("|||||" + ownerService.findByLastName("Warwick").toString());
//
        List<Owner> results = ownerService.findAllByLastNameLike("%"+ owner.getLastName() +"%");

        if(results.isEmpty()){
            result.rejectValue("lastName","notFound","not found");
            return "owners/findOwners";
        }
        else if(results.size() == 1){
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        }
        else{
            //multi owners found.
            model.addAttribute("selections",results);
            return "owners/ownersList";
        }

    }


    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId){
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }

    @GetMapping("/new")
    public String initOwnerCreationForm(Model model) {
        model.addAttribute("owner",Owner.builder().build());
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid Owner owner, BindingResult result){
        if(result.hasErrors()){
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        }
        else {
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }

    @GetMapping("/{ownerId}/edit") //direct to spe
    public String initUpdateOwnerForm(Model model, @PathVariable Long ownerId){
        model.addAttribute(ownerService.findById(ownerId));
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerForm(@Valid Owner owner,
                                         BindingResult result,  @PathVariable Long ownerId){
        if(result.hasErrors()){
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        }
        else{
            owner.setId(ownerId); //set the ownerID
            Owner savedOwner = ownerService.save(owner); //save the specific owner.
            return "redirect:/owners/" + savedOwner.getId();
        }

    }
}
