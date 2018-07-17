package junbau.tools.smtp;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class TLSEmail implements EmailService {

    private EmailUtil send = new EmailUtil();
    private static Properties props = new Properties();
    private static UserInput userData = new UserInput();

        public void tlsEmail(String password, String authAccount) throws Exception {

            final String smtpAuth = authAccount;
            final String pass = password;

            System.out.println("TLS Email start.");

            props.put("mail.smtp.host", userData.getSmtpHost()); //SMTP Host
            props.put("mail.smtp.from", userData.getP1Address());
            props.put("mail.smtp.port", "587"); //TLS Port
            props.put("mail.smtp.auth", "true"); //enable authentication
            props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

            //create Authenticator object to pass in Session.getInstance argument
            Authenticator auth = new Authenticator() {
                //override the getPasswordAuthentication method
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(smtpAuth, pass);
                }
            };

            Session session = Session.getInstance(props, auth);
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
