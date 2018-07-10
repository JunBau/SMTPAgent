package junbau.tools.smtp;

import javax.mail.Session;
import java.util.Properties;

public class SMTPEmail {

    EmailUtil send = new EmailUtil();

    public void sendEmail() {
        System.out.println("SimpleEmail Start");
        Properties props = System.getProperties();
        props.put("mail.from", UserInput.getP1Address());
        props.put("mail.smtp.host", UserInput.getSmtpHost());
        Session session = Session.getInstance(props, null);
        send.sendEmail(session, UserInput.getToAddress(), UserInput.getUsrSubject(), UserInput.getMsgBody(), UserInput.getHtmlBody());
    }
}
