package junbau.tools.smtp;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;

public class EmailUtil {

    // Code by JunBau

    private static String mailStatus;
    private static boolean mailFromSpoof = false;

    public static void setMailFromSpoof(boolean mailFromSpoof) {
        EmailUtil.mailFromSpoof = mailFromSpoof;
    }

    public static String getMailStatus() {
        return mailStatus;
    }

    public static void setMailStatus(String mailStatus) {
        EmailUtil.mailStatus = mailStatus;
    }

    public void sendEmail(Session session, String toEmail, String subject, String body, String htmlBody) {
        try
        {
            MimeMessage msg = new MimeMessage(session);

            //Set message headers
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");
            msg.setSentDate(new Date());

            //Content
            final Multipart altMP = new MimeMultipart("alternative");
            final MimeBodyPart plainText = new MimeBodyPart();
            final MimeBodyPart htmlText = new MimeBodyPart();

            msg.setSubject(subject, "UTF-8");

            if (htmlBody.isEmpty()) {
                plainText.setContent(body, "text/plain");
                altMP.addBodyPart(plainText);
                System.out.println("touch");
            } else if (body.isEmpty()) {
                htmlText.setContent(htmlBody, "text/html");
                altMP.addBodyPart(htmlText);
                System.out.println("touch2");
            } else {
                plainText.setContent(body, "text/plain");
                altMP.addBodyPart(plainText);
                htmlText.setContent(htmlBody, "text/html");
                altMP.addBodyPart(htmlText);
                System.out.println("touch3");
            }
            msg.setContent(altMP);

            //Setting recipients
            if (!UserInput.getP2Address().equals("")) {
                if (mailFromSpoof==true) {
                    msg.setFrom(new InternetAddress(UserInput.getP1Address(), UserInput.getP2Address()));
                } else {
                    msg.addHeader("From", UserInput.getP2Address());
                }
            }
            msg.setReplyTo(InternetAddress.parse(UserInput.getReplyTo(), false));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            if (!UserInput.getMsgCCFields().equals("")) {
                msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(UserInput.getMsgCCFields(), false));
            }

            System.out.println("Message is ready");
            Transport.send(msg);

            System.out.println(body);

            setMailStatus("Email sent successfully!!");
            System.out.println("Email Sent Successfully!!");

            System.out.println(htmlBody);
        }
        catch (Exception e) {
            setMailStatus(e.getMessage());
            e.printStackTrace();
        }
    }
}