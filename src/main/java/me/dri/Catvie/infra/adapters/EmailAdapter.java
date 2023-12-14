package me.dri.Catvie.infra.adapters;

import jakarta.mail.MessagingException;
import me.dri.Catvie.infra.ports.email.EmailServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;


@Service
public class EmailAdapter implements EmailServicePort {


    private final MailSender mailSender;


    @Autowired
    public EmailAdapter(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendMessage(String to) throws MessagingException {

    }

    @Override
    public void  sendWelcomeMessage(String to)  {
       SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
       simpleMailMessage.setFrom("heenrikk3@gmail.com");
       simpleMailMessage.setTo(to);
       simpleMailMessage.setSubject("Seja bem-vindo | Catvie");
       simpleMailMessage.setText("Muito obrigado pelo cadastro");
       this.mailSender.send(simpleMailMessage);
    }
}
