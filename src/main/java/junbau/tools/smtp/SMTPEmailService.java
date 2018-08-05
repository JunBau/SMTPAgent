package junbau.tools.smtp;

import javax.mail.Session;
import java.util.Properties;

public class SMTPEmailService {

    private EmailUtil send = new EmailUtil();

    public void sendEmail(UserInput userData) {
        System.out.println("SimpleEmail Start");
        Properties props = System.getProperties();
        props.put("mail.from", userData.getP1Address());
        props.put("mail.smtp.host", userData.getSmtpHost());
        Session session = Session.getInstance(props, null);
        send.sendEmail(userData.getToAddress(), userData.getP2Address(), userData.getP1Address(), userData.getReplyTo(),
                userData.getMsgCCFields(), userData.getUsrSubject(), userData.getMsgBody(), userData.getHtmlBody(), session,
                userData);
    }

    public String getMailStatus() {
        return send.getMailStatus();
    }

}
