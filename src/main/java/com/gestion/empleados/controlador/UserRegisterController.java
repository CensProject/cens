package com.gestion.empleados.controlador;

import com.gestion.empleados.controlador.dto.EmpleadoRegisterDTO;
import com.gestion.empleados.entidades.Empleado;
import com.gestion.empleados.servicio.EmpleadoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequestMapping("/registration")
public class UserRegisterController {

    private EmpleadoService empleadoService;

    public UserRegisterController(EmpleadoService empleadoService) {
        super();
        this.empleadoService = empleadoService;
    }

    @ModelAttribute("user")
    public EmpleadoRegisterDTO empleadoRegisterDTO() {
        return new EmpleadoRegisterDTO();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid Empleado registrationDto) {
        empleadoService.save(registrationDto);
        return "redirect:/registration?success";
    }

}