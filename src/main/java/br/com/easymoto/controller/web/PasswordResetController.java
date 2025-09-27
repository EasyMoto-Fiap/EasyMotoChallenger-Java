package br.com.easymoto.controller.web;

import br.com.easymoto.exception.InvalidPasswordException;
import br.com.easymoto.exception.ResourceNotFoundException;
import br.com.easymoto.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PasswordResetController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping("/forgot-password")
    public String showForgotPasswordForm() {
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String processForgotPassword(@RequestParam("email") String email, RedirectAttributes redirectAttributes) {
        try {
            funcionarioService.generatePasswordResetToken(email);
        } catch (Exception e) {
            System.err.println("Erro ao processar recuperação de senha: " + e.getMessage());
        }
        redirectAttributes.addFlashAttribute("mensagem", "Se um usuário com este email existir em nosso sistema, um link de recuperação de senha foi enviado.");
        return "redirect:/login";
    }

    @GetMapping("/reset-password")
    public String showResetPasswordForm(@RequestParam("token") String token, Model model, RedirectAttributes redirectAttributes) {
        try {
            funcionarioService.buscarPorToken(token);
            model.addAttribute("token", token);
            return "reset-password";
        } catch (ResourceNotFoundException | InvalidPasswordException e) {
            redirectAttributes.addFlashAttribute("mensagemErro", e.getMessage());
            return "redirect:/login";
        }
    }

    @PostMapping("/reset-password")
    public String processResetPassword(@RequestParam String token,
                                       @RequestParam String password,
                                       @RequestParam String confirmPassword,
                                       RedirectAttributes redirectAttributes) {

        if (!password.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("error", "As senhas não coincidem.");
            return "redirect:/reset-password?token=" + token;
        }

        try {
            funcionarioService.resetPassword(token, password);
            redirectAttributes.addFlashAttribute("mensagem", "Sua senha foi redefinida com sucesso! Você já pode fazer o login.");
            return "redirect:/login";
        } catch (ResourceNotFoundException | InvalidPasswordException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/reset-password?token=" + token;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Ocorreu um erro inesperado ao redefinir sua senha.");
            return "redirect:/reset-password?token=" + token;
        }
    }
}