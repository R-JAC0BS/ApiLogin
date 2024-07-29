package br.login.api.apilogin.services;

import br.login.api.apilogin.entitys.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.datasource.username}")
    private String username;
    public String sendEmail (String to, String subject, String body) {
        UsuarioEntity user = new UsuarioEntity();
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(username);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);
            return "Email sent";
        }catch (Exception e) {
            return "Email not sent";
        }
    }

}
