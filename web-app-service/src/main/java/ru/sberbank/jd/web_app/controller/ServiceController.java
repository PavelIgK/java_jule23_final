package ru.sberbank.jd.web_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.jd.web_app.entity.ServiceDTO;
import ru.sberbank.jd.web_app.service.ServiceService;
import java.util.List;

@Controller
@RequestMapping("/services")
public class ServiceController {

    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public String getAllServices(Model model) {
        List<ServiceDTO> serviceList = serviceService.findAllServices();
        model.addAttribute("services", serviceList);
        return "services";
    }

    @GetMapping("/create")
    public String showCreateServiceForm(Model model) {
        ServiceDTO service = new ServiceDTO();
        model.addAttribute("service", service);
        return "create_service";
    }

    @PostMapping
    public String createService(@ModelAttribute("service") ServiceDTO service) {
        serviceService.saveService(service);
        return "redirect:/services";
    }

    @PostMapping("/update")
    public String updateService(@ModelAttribute("service") ServiceDTO service) {
        serviceService.updateService(service);
        return "redirect:/services";
    }

    @GetMapping("/update/{id}")
    public String showUpdateServiceForm(@PathVariable (value = "id") String id, Model model) {
        ServiceDTO service = serviceService.getServiceById(id);
        model.addAttribute("service", service);
        return "update_service";
    }

    @GetMapping("/delete/{id}")
    public String deleteService(@PathVariable(value = "id") String id) {
        serviceService.deleteServiceById(id);
        return "redirect:/services";
    }
}
