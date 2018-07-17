package junbau.tools.smtp;

import javax.mail.Session;
import java.util.Properties;

public class SMTPEmail extends UserInput implements EmailService {

    private EmailUtil send = new EmailUtil();
    private static Properties props = System.getProperties();
    private static UserInput userData = new UserInput();

    public void sendEmail() {
        System.out.println("SimpleEmail Start");
        props.put("mail.from", userData.getP1Address());
        props.put("mail.smtp.host", userData.getSmtpHost());
        Session session = Session.getInstance(props, null);
        send.sendEmail(userData.getToAddress(), userData.getP2Address(), userData.getP1Address(), userData.getReplyTo(),
                userData.getMsgCCFields(), userData.getUsrSubject(), userData.getMsgBody(), userData.getHtmlBody(), session);
    }

    @Override
    public void setMsgProps(String msg, String html, String subj, String rcpt, String cc) {
        userData.setMsgBody(msg);
        userData.setUsrSubject(subj);
        userData.setToAddress(rcpt);
        userData.setMsgCCFields(cc);
        userData.setHtmlBody(html);
    }

    @Override
    public void setSenderAddr(String fromAddr, String headerAddr, String replyTo) {
        userData.setP1Address(fromAddr);
        userData.setP2Address(headerAddr);
        userData.setReplyTo(replyTo);
    }

    @Override
    public void setSMTPhost(String host) {
        userData.setSmtpHost(host);
    }
}
