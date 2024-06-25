package edu.icet.clothify.util.email_to_sms_gatewaylayer;


import javax.mail.*;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.util.Properties;
import java.util.Random;


//adgz kini yqyj jhjp

public class OtpGateway {

    private String generateOtp() {
        Random rand = new Random();
        int optNumber = 100000 + rand.nextInt(1000000);
        return Integer.toString(optNumber);
    }

    public Boolean sendOtp(String emailTo) {
        int otpNumber = Integer.parseInt(generateOtp());
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("lakshitha200087@gmail.com", "adgz kini yqyj jhjp");
            }
        });

        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("lakshitha200087@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, (new InternetAddress(emailTo)));
            message.setSubject("OTP");
            message.setText("Your OTP is: " + otpNumber);
            Transport.send(message);
            String inputOtp = JOptionPane.showInputDialog(
                    null, "Enter your OTP", JOptionPane.INFORMATION_MESSAGE);
            if (inputOtp != null) {
                if (inputOtp.equals(Integer.toString(otpNumber))) {
                    JOptionPane.showMessageDialog(null, "Correct OTP");
                    return true;
                }
                JOptionPane.showMessageDialog(null, "Invalid OTP");
            }
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}

