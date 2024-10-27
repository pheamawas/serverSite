package sit.int202.demo.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sit.int202.demo.entities.Office;
import sit.int202.demo.services.OfficeService;

import java.util.List;

@Controller
@RequestMapping("/offices")
public class OfficeController {
    private OfficeService officeService;
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @GetMapping("/all")
    public String getAllOffices(ModelMap modelMap) {
        List<Office> offices = officeService.getAllOffices();
        modelMap.addAttribute("offices", offices);
        return "office_list";
    }

    @GetMapping("")
    public String getOfficesById(@RequestParam String officeCode, ModelMap modelMap) {
        Office office = officeService.getOfficeByCode(officeCode);
        modelMap.addAttribute("office", office);
        return "office_detail";
    }

    @GetMapping("/delete-office")
    public String deleteOffice(@RequestParam String officeCode, ModelMap modelMap) {
        Office office = officeService.deleteOffice(officeCode);
        modelMap.addAttribute("office", office);
        return "office_detail";
    }

    @PostMapping("/create-office")
    public String createOffice(Office office ,ModelMap modelMap) {
//        Office office = new Office();
//        office.setOfficeCode(request.getParameter("officeCode"));
//        office.setCity(request.getParameter("city"));
//        office.setCountry(request.getParameter("country"));
//        office.setPhone(request.getParameter("phone"));
//        office.setPostalCode(request.getParameter("postalCode"));
//        office.setAddressLine1(request.getParameter("addressLine1"));
//        office.setTerritory(request.getParameter("territory"));
//        Office newOffice = officeService.createOffice(office);
        Office newOffice = officeService.createOffice(office);
        modelMap.addAttribute("office", newOffice);
        return "office_detail";
    }
    @PostMapping("update-office")
    public String updateOffice(HttpServletRequest request, ModelMap modelMap) {
        Office office = new Office();
        office.setOfficeCode(request.getParameter("officeCode"));
        office.setCity(request.getParameter("city"));
        office.setCountry(request.getParameter("country"));
        office.setPhone(request.getParameter("phone"));
        office.setPostalCode(request.getParameter("postalCode"));
        office.setAddressLine1(request.getParameter("addressLine1"));
        office.setTerritory(request.getParameter("territory"));
        Office newOffice = officeService.updateOffice(office);
        modelMap.addAttribute("office", newOffice);

        return "office_detail";
    }
    @GetMapping("/update-form")
    public String getUpdateForm(@RequestParam String officeCode, ModelMap modelMap) {
        Office office = officeService.getOfficeByCode(officeCode);
        modelMap.addAttribute("office", office);
        return "update_form";
    }

    @GetMapping("/office-form")
    public String getOfficeForm() {
        return "office_form";
    }
}
