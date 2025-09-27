package br.com.easymoto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendPasswordResetEmail(String to, String token) {
        String subject = "EasyMoto - Redefinição de Senha";
        String resetUrl = "http://localhost:8080/reset-password?token=" + token;
        String message = "Você solicitou a redefinição da sua senha.\n\n"
                + "Clique no link abaixo para criar uma nova senha:\n"
                + resetUrl + "\n\n"
                + "Se você não solicitou isso, por favor, ignore este e-mail.";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(to);
        email.setSubject(subject);
        email.setText(message);
        mailSender.send(email);
    }
}