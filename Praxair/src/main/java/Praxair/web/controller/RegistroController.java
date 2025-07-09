/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Praxair.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controlador para manejar el registro de usuarios
 */
@Controller
@RequestMapping("/registro")
public class RegistroController {
    
    /**
     * Muestra la página de registro
     * @param model Modelo para pasar datos a la vista
     * @return Nombre del template a renderizar
     */
    @GetMapping("")
    public String mostrarRegistro(Model model) {
        model.addAttribute("pageTitle", "Registro - Praxair");
        model.addAttribute("pageDescription", "Crear una nueva cuenta en Praxair");
        
        return "registro"; // Busca en templates/registro.html
    }
    
    /**
     * Ruta alternativa para mostrar registro
     */
    @GetMapping("/form")
    public String mostrarFormularioRegistro(Model model) {
        return "redirect:/registro";
    }
    
    /**
     * Procesa el formulario de registro
     * @param nombre Nombre del usuario
     * @param apellido Apellido del usuario
     * @param email Correo electrónico
     * @param password Contraseña
     * @param confirmPassword Confirmación de contraseña
     * @param telefono Teléfono del usuario
     * @param direccion Dirección del usuario
     * @param redirectAttributes Atributos para redirección
     * @return Redirección después del registro
     */
    @PostMapping("/procesar")
    public String procesarRegistro(
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            @RequestParam String telefono,
            @RequestParam String direccion,
            RedirectAttributes redirectAttributes) {
        
        try {
            // Validaciones básicas
            if (!validarCamposRequeridos(nombre, apellido, email, password, confirmPassword, telefono, direccion)) {
                redirectAttributes.addFlashAttribute("error", "Todos los campos son obligatorios");
                return "redirect:/registro";
            }
            
            if (!password.equals(confirmPassword)) {
                redirectAttributes.addFlashAttribute("error", "Las contraseñas no coinciden");
                return "redirect:/registro";
            }
            
            if (!validarPassword(password)) {
                redirectAttributes.addFlashAttribute("error", "La contraseña debe tener al menos 8 caracteres, incluir mayúsculas, minúsculas y números");
                return "redirect:/registro";
            }
            
            if (!validarEmail(email)) {
                redirectAttributes.addFlashAttribute("error", "Formato de email inválido");
                return "redirect:/registro";
            }
            
            // Aquí iría la lógica para guardar el usuario en la base de datos
            // Por ejemplo:
            // Usuario nuevoUsuario = new Usuario(nombre, apellido, email, password, telefono, direccion);
            // usuarioService.guardarUsuario(nuevoUsuario);
            
            // Simular proceso de registro exitoso
            boolean registroExitoso = registrarUsuario(nombre, apellido, email, password, telefono, direccion);
            
            if (registroExitoso) {
                redirectAttributes.addFlashAttribute("success", "¡Registro exitoso! Te hemos enviado un correo de confirmación.");
                // Podrías redirigir a una página de confirmación o login
                return "redirect:/registro?success=true";
            } else {
                redirectAttributes.addFlashAttribute("error", "Error en el registro. El email ya está en uso.");
                return "redirect:/registro";
            }
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error interno del servidor. Intenta nuevamente.");
            return "redirect:/registro";
        }
    }
    
    /**
     * Página de confirmación después del registro exitoso
     */
    @GetMapping("/confirmacion")
    public String confirmacionRegistro(Model model) {
        model.addAttribute("pageTitle", "Registro Exitoso - Praxair");
        model.addAttribute("mensaje", "¡Registro completado exitosamente!");
        
        return "registro-confirmacion"; // Busca en templates/registro-confirmacion.html
    }
    
    /**
     * Validar que todos los campos requeridos estén presentes
     */
    private boolean validarCamposRequeridos(String... campos) {
        for (String campo : campos) {
            if (campo == null || campo.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Validar formato de email
     */
    private boolean validarEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }
    
    /**
     * Validar contraseña (mínimo 8 caracteres, al menos 1 mayúscula, 1 minúscula, 1 número)
     */
    private boolean validarPassword(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        
        boolean tieneMayuscula = password.matches(".*[A-Z].*");
        boolean tieneMinuscula = password.matches(".*[a-z].*");
        boolean tieneNumero = password.matches(".*\\d.*");
        
        return tieneMayuscula && tieneMinuscula && tieneNumero;
    }
    
    /**
     * Validar formato de teléfono costarricense
     */
    private boolean validarTelefono(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            return false;
        }
        
        // Formato: +506 1234 5678 o 1234-5678 o 12345678
        String telefonoRegex = "^(\\+506\\s?)?[0-9]{4}[\\s-]?[0-9]{4}$";
        return telefono.replaceAll("\\s", "").matches(telefonoRegex);
    }
    
    /**
     * Simular el proceso de registro (aquí conectarías con tu servicio de usuario)
     * @return true si el registro fue exitoso, false si hay errores
     */
    private boolean registrarUsuario(String nombre, String apellido, String email, 
                                   String password, String telefono, String direccion) {
        
        // Simular verificación de email existente
        if (email.equals("test@praxair.com")) {
            return false; // Email ya existe
        }
        
        // Aquí iría la lógica real de registro:
        // 1. Hashear la contraseña
        // 2. Guardar en base de datos
        // 3. Enviar email de confirmación
        // 4. Crear logs de auditoría
        
        // Por ahora, simular que el registro siempre es exitoso
        System.out.println("Registrando usuario: " + nombre + " " + apellido + " - " + email);
        
        return true;
    }
    
    /**
     * Endpoint para verificar si un email ya existe (AJAX)
     */
    @GetMapping("/verificar-email")
    public String verificarEmail(@RequestParam String email, Model model) {
        boolean existe = email.equals("test@praxair.com"); // Simular verificación
        
        model.addAttribute("existe", existe);
        return "fragments/email-verificacion"; // Fragment para respuesta AJAX
    }
}