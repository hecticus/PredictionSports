package utils;

import play.libs.mailer.Email;
import play.libs.mailer.MailerClient;
import javax.inject.Inject;
import java.io.File;
import org.apache.commons.mail.EmailAttachment;
import play.libs.mailer.MailerPlugin;

public class Mailer {
    //@Inject MailerClient mailerClient;
/*
    public void sendEmail() {

        String cid = "1234";
        Email email = new Email()
                .setSubject("Info Mail")
                .setFrom("Mister FROM <alarma.rk@gmail.com>")
                .addTo("Miss TO <palenge@gmail.com>")
                // adds attachment
                // sends text, HTML or both...
                .setBodyText("A text message")
                .setBodyHtml("<html><body><p>An <b>html</b> empezo el correito</p></body></html>");
        mailerClient.send(email);
    }*/

    public static void SendError(String title, String body)
    {
        String to = "fernando.alvarez@hecticus.com";
        try {
            Email email = new Email()
                    .setSubject(title)
                    .setFrom("Mister FROM <alarma.rk@gmail.com>")
                    .addTo("Miss TO <"+ to +">")
                    // adds attachment
                    // sends text, HTML or both...
                    .setBodyText(body);
            MailerPlugin mp = new MailerPlugin();
                mp.send(email);
        }catch(Exception e)
        {

        }

    }
}
