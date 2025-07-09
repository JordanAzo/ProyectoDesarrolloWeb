/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Praxair.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador para manejar las páginas relacionadas con gases industriales
 */
@Controller
@RequestMapping("/gases")
public class GasesController {
    
    /**
     * Muestra la página de gases industriales (fragmentos)
     * @param model Modelo para pasar datos a la vista
     * @return Nombre del template a renderizar
     */
    @GetMapping("/fragmentos")
    public String fragmentos(Model model) {
        // Puedes agregar datos al modelo si es necesario
        model.addAttribute("pageTitle", "Gases Industriales - Praxair");
        model.addAttribute("pageDescription", "Catálogo completo de gases industriales");
        
        return "gases/fragmentos"; // Busca en templates/gases/fragmentos.html
    }
    
    /**
     * Ruta alternativa sin "fragmentos" en la URL
     */
    @GetMapping("")
    public String gasesIndex(Model model) {
        return "redirect:/gases/fragmentos";
    }
    
    /**
     * Ruta para la página principal de gases (si necesitas una diferente)
     */
    @GetMapping("/index")
    public String gasesIndexPage(Model model) {
        model.addAttribute("pageTitle", "Gases Industriales - Praxair");
        return "gases/fragmentos";
    }
}