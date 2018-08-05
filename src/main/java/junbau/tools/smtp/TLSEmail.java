package junbau.tools.smtp;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class TLSEmail {

    private EmailUtil send = new EmailUtil();

        public void tlsEmail(String password, String authAccount, UserInput userData) throws Exception {
            Properties props = new Properties();

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
                    userData.getMsgCCFields(), userData.getUsrSubject(), userData.getMsgBody(), userData.getHtmlBody(), session,
                    userData);
        }

        public String getMailStatus() {
            return send.getMailStatus();
        }

}
