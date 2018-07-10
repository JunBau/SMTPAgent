package junbau.tools.smtp;

import junbau.tools.ui.MUAController;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class TLSEmail {

    EmailUtil send = new EmailUtil();

        public void tlsEmail(String password, String authAccount) {

            final String smtpAuth = authAccount;
            final String pass = password;

            final String toEmail = UserInput.getToAddress();

            System.out.println("TLS Email start.");

            Properties props = new Properties();
            props.put("mail.smtp.host", UserInput.getSmtpHost()); //SMTP Host
            props.put("mail.smtp.from", UserInput.getP1Address());
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

            send.sendEmail(session, toEmail, UserInput.getUsrSubject(), UserInput.getMsgBody(), UserInput.getHtmlBody());

        }

}
