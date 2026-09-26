package com.hecticus.gpaapi.service;

import com.hecticus.gpaapi.config.GpaApiProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServicio {

    private static final Logger log = LoggerFactory.getLogger(MailServicio.class);

    private final JavaMailSender mailSender;
    private final GpaApiProperties properties;

    public MailServicio(JavaMailSender mailSender, GpaApiProperties properties) {
        this.mailSender = mailSender;
        this.properties = properties;
    }

    public void sendMail(String titulo, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(properties.getMail().getFrom());
            message.setTo(properties.getMail().getTo().split(","));
            message.setSubject(titulo);
            message.setText(body);
            mailSender.send(message);
        } catch (Exception e) {
            log.error("Error enviando correo '{}'", titulo, e);
        }
    }
}
