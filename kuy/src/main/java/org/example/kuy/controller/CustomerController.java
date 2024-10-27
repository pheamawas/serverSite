package org.example.kuy.controller;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.kuy.entity.Customer;
import org.example.kuy.service.CustomerService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    private CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("")
    public String getAllCustomer(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, ModelMap modelMap) {
        modelMap.addAttribute("page", customerService.getAllCustomers(PageRequest.of(page,size)));
        return "customer_list";
    }
    @GetMapping("/search-customer")
    public String searchCustomerByName(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "") String param, ModelMap modelMap) {
        modelMap.addAttribute("page", customerService.findCustomerByName(PageRequest.of(page, size), param));
        return "customer_list";
    }
    @GetMapping("/detail")
    public String customerDetail(@RequestParam Integer customerNumber, ModelMap modelMap) {
        modelMap.addAttribute("customer", customerService.getCustomer(customerNumber));
        return "customer_detail";
    }
    @GetMapping("/create")
    public String createCustomerForm() {
        return "customer_form";
    }
    @PostMapping("/create")
    public String createCustomer(Customer customer, ModelMap modelMap) {
        Customer newCustomer = customerService.createCustomer(customer);
        modelMap.addAttribute("customer", newCustomer);
        return "customer_detail";
    }
    @GetMapping("/update")
    public String updateCustomerForm(@RequestParam Integer customerNumber, ModelMap modelMap) {
        Customer customer = customerService.getCustomer(customerNumber);
        modelMap.addAttribute("customer", customer);
        return "customer_update_form";
    }
    @PostMapping("/update")
    public void updateCustomer(Customer newCustomer, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        Customer newCustomer = customerService.getCustomer(Integer.valueOf(request.getParameter("customerNumber")));
//        newCustomer.setId(Integer.valueOf(request.getParameter("customerNumber")));
//        newCustomer.setCustomerName(request.getParameter("customerName"));
//        newCustomer.setContactLastName(request.getParameter("contactLastName"));
//        newCustomer.setContactFirstName(request.getParameter("contactFirstName"));
//        newCustomer.setPhone(request.getParameter("phone"));
//        newCustomer.setAddressLine1(request.getParameter("addressLine1"));
//        newCustomer.setCity(request.getParameter("city"));
//        newCustomer.setCountry(request.getParameter("country"));

        customerService.updateCustomer(newCustomer);
        response.sendRedirect("/customers");
    }
    @GetMapping("/delete")
    public void deleteCustomer(@RequestParam Integer customerNumber, @RequestParam int page, HttpServletResponse httpServletResponse) throws IOException {
        customerService.deleteCustomer(customerNumber);
        httpServletResponse.sendRedirect("/customers?page="+page);
    }
}
