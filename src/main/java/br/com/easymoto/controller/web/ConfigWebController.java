package br.com.easymoto.controller.web;

import br.com.easymoto.exception.InvalidPasswordException;
import br.com.easymoto.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/web/config")
public class ConfigWebController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public String showConfigPage() {
        return "config/index";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam String oldPassword,
                                 @RequestParam String newPassword,
                                 @RequestParam String confirmPassword,
                                 RedirectAttributes redirectAttributes) {

        if (newPassword == null || newPassword.length() < 6) {
            redirectAttributes.addFlashAttribute("error", "A nova senha deve ter no mínimo 6 caracteres.");
            return "redirect:/web/config";
        }

        if (!newPassword.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("error", "A nova senha e a confirmação não correspondem.");
            return "redirect:/web/config";
        }

        try {
            funcionarioService.changePassword(oldPassword, newPassword);
            redirectAttributes.addFlashAttribute("success", "Senha alterada com sucesso!");
        } catch (InvalidPasswordException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Ocorreu um erro ao tentar alterar a senha.");
        }

        return "redirect:/web/config";
    }
}