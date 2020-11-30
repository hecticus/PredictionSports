package services.mail_servicio;

import com.sun.mail.smtp.SMTPTransport;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.inject.Singleton;
import javax.mail.*;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

@Singleton
public class MailServicio {
    private  final String SMTP_SERVER = "smtp.gmail.com";
    private  final String USERNAME = "alarma@hecticus.com";
    private  final String PASSWORD = "alarma12345";

    private  final String EMAIL_FROM = "alarma@hecticus.com";
    private  final String EMAIL_TO = "soporte.daemons@hecticus.com, palenge@gmail.com";
    private  final String EMAIL_TO_CC = "";

    public void sendMail(String titulo, String body) {

        Properties prop = System.getProperties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop, null);
        Message msg = new MimeMessage(session);

        try {
            msg.setFrom(new InternetAddress(EMAIL_FROM));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(EMAIL_TO, false));
            msg.setSubject(titulo);
            // text
            MimeBodyPart p1 = new MimeBodyPart();
            p1.setText(body);

            Multipart mp = new MimeMultipart();
            mp.addBodyPart(p1);
            msg.setContent(mp);

            SMTPTransport t = (SMTPTransport) session.getTransport("smtp");

            // connect
            t.connect(SMTP_SERVER, USERNAME, PASSWORD);

            // send
            t.sendMessage(msg, msg.getAllRecipients());

            System.out.println("Response: " + t.getLastServerResponse());

            t.close();

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
