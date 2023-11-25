package ru.sberbank.jd.web_app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.jd.dto.schedule.ProvidedServiceDto;
import ru.sberbank.jd.web_app.service.ProvidedServiceService;

import java.util.List;

/**
 * Контроллер для обработки запросов о предоставляемых услугах.
 *
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/services")
public class ProvidedServiceController {

    private final ProvidedServiceService providedServiceService;

    /**
     * Получить инфо о всех услугах.
     *
     * @param model модель
     * @return шаблон со списком всех услуг
     */
    @GetMapping
    public String getAllServices(Model model) {
        List<ProvidedServiceDto> serviceDtoList = providedServiceService.findAllServices();
        model.addAttribute("services", serviceDtoList);
        return "services";
    }

    /**
     * Отобразить страницу для создания новой записи услуги.
     *
     * @param model модель
     * @return шаблон для создания новой услуги
     */
    @GetMapping("/create")
    public String showCreateServiceForm(Model model) {
        ProvidedServiceDto serviceDto = new ProvidedServiceDto();
        model.addAttribute("service", serviceDto);
        return "service_create";
    }

    /**
     * Создание новой услуги.
     *
     * @param serviceDto инфо о новой услуге
     * @return шаблон со списком всех услуг
     */
    @PostMapping
    public String createService(@ModelAttribute("service") ProvidedServiceDto serviceDto) {
        providedServiceService.saveService(serviceDto);
        return "redirect:/services";
    }

    /**
     * Обновить услугу.
     *
     * @param serviceDto инфо об услуге
     * @return шаблон со списком всех услуг
     */
    @PostMapping("/update")
    public String updateService(@ModelAttribute("service") ProvidedServiceDto serviceDto) {
        providedServiceService.updateService(serviceDto);
        return "redirect:/services";
    }

    /**
     * Отобразить страницу для редактирования инфо об услуге.
     *
     * @param id id услуги
     * @param model модель
     * @return шаблон для редактирования услуги
     */
    @GetMapping("/update/{id}")
    public String showUpdateServiceForm(@PathVariable(value = "id") String id, Model model) {
        ProvidedServiceDto serviceDto = providedServiceService.getServiceById(id);
        model.addAttribute("service", serviceDto);
        return "update_service";
    }

    /**
     * Удалить услугу.
     *
     * @param id id услуги
     * @return шаблон со списком всех услуг
     */
    @GetMapping("/delete/{id}")
    public String deleteService(@PathVariable(value = "id") String id) {
        providedServiceService.deleteServiceById(id);
        return "redirect:/services";
    }
}
