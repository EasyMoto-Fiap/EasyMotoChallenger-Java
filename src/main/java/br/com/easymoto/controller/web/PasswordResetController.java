package br.com.easymoto.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PasswordResetController {

    @GetMapping("/forgot-password")
    public String showForgotPasswordForm() {
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String processForgotPassword(@RequestParam("email") String email, RedirectAttributes redirectAttributes) {
        // AQUI VIRIA A LÓGICA DE NEGÓCIO:
        // 1. Procurar o usuário pelo email.
        // 2. Gerar um token de reset.
        // 3. Enviar o email para o usuário com o link de reset.

        System.out.println("Solicitação de reset de senha para o email: " + email);

        redirectAttributes.addFlashAttribute("mensagem", "Se um usuário com este email existir, um link de recuperação de senha foi enviado.");
        return "redirect:/login";
    }
}