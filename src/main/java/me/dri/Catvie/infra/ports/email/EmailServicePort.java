package me.dri.Catvie.infra.ports.email;

import jakarta.mail.MessagingException;

public interface EmailServicePort {


    void sendMessage(String to) throws MessagingException;

    void sendWelcomeMessage(String to);
}
