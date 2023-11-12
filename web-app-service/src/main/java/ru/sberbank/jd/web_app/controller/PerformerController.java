package ru.sberbank.jd.web_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.jd.web_app.entity.Performer;
import ru.sberbank.jd.web_app.service.PerformerService;

import java.util.List;

@Controller
@RequestMapping("/performers")
public class PerformerController {

    private final PerformerService performerService;

    public PerformerController(PerformerService performerService) {
        this.performerService = performerService;
    }

    @GetMapping
    public String getAllPerformers(Model model) {
        List<Performer> performerList = performerService.findAllPerformers();
        model.addAttribute("performers", performerList);
        return "performers";
    }

    @GetMapping("/create")
    public String showCreatePerformerForm(Model model) {
        Performer performer = new Performer();
        model.addAttribute("performer", performer);
        return "create_performer";
    }

    @PostMapping
    public String createPerformer(@ModelAttribute("performer") Performer performer) {
        performerService.savePerformer(performer);
        return "redirect:/performers";
    }

    @PostMapping("/update")
    public String updatePerformer(@ModelAttribute("performer") Performer performer) {
        performerService.updatePerformer(performer);
        return "redirect:/performers";
    }

    @GetMapping("/update/{id}")
    public String showUpdatePerformerForm(@PathVariable (value = "id") String id, Model model) {
        Performer performer = performerService.getPerformerById(id);
        model.addAttribute("performer", performer);
        return "update_performer";
    }

    @GetMapping("/delete/{id}")
    public String deletePerformer(@PathVariable(value = "id") String id) {
        performerService.deletePerformerById(id);
        return "redirect:/performers";
    }
}
