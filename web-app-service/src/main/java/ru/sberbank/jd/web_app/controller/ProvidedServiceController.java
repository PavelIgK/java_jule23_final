package ru.sberbank.jd.web_app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.jd.dto.schedule.ProvidedServiceDto;
import ru.sberbank.jd.web_app.service.ProvidedServiceService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/services")
public class ProvidedServiceController {

    private final ProvidedServiceService providedServiceService;

    @GetMapping
    public String getAllServices(Model model) {
        List<ProvidedServiceDto> serviceDtoList = providedServiceService.findAllServices();
        model.addAttribute("services", serviceDtoList);
        return "services";
    }

    @GetMapping("/create")
    public String showCreateServiceForm(Model model) {
        ProvidedServiceDto serviceDto = new ProvidedServiceDto();
        model.addAttribute("service", serviceDto);
        return "service_create";
    }

    @PostMapping
    public String createService(@ModelAttribute("service") ProvidedServiceDto serviceDto) {
        providedServiceService.saveService(serviceDto);
        return "redirect:/services";
    }

    @PostMapping("/update")
    public String updateService(@ModelAttribute("service") ProvidedServiceDto serviceDto) {
        providedServiceService.updateService(serviceDto);
        return "redirect:/services";
    }

    @GetMapping("/update/{id}")
    public String showUpdateServiceForm(@PathVariable(value = "id") String id, Model model) {
        ProvidedServiceDto serviceDto = providedServiceService.getServiceById(id);
        model.addAttribute("service", serviceDto);
        return "update_service";
    }

    @GetMapping("/delete/{id}")
    public String deleteService(@PathVariable(value = "id") String id) {
        providedServiceService.deleteServiceById(id);
        return "redirect:/services";
    }
}
