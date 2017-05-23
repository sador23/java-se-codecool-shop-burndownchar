package com.codecool.shop.controller;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


/**
 * Email sending and configuration
 */
public class Email {

    /**
     * Given the inputs, it sends the email using smtp
     * @param msg
     * @param subject
     */
    public static void sendEmail(String msg, String subject) {
        String from="x.com";
        String to="y.com";
        String host="localhost";
        Properties properties = System.getProperties();
        //properties.setProperty("mail.smtp.host", host);
        properties.put("mail.user","x.com");
        properties.put("mail.password","hm");

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(properties);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(msg);

            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
