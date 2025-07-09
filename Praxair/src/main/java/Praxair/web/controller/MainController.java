/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Praxair.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador principal para las páginas generales de la aplicación
 */
@Controller
public class MainController {
    
    /**
     * Página principal / inicio
     */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("pageTitle", "Praxair - Gases Industriales");
        return "index"; // Busca en templates/index.html
    }
    
    /**
     * Página de inicio alternativa
     */
    @GetMapping("/inicio")
    public String inicio(Model model) {
        return "redirect:/";
    }
    
    /**
     * Página de plantilla (si necesitas acceso directo)
     */
    @GetMapping("/plantilla")
    public String plantilla(Model model) {
        model.addAttribute("pageTitle", "Praxair - Inicio");
        return "layout/plantilla"; // Busca en templates/layout/plantilla.html
    }
}