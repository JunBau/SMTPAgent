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

    public void sendEmail(String toEmail, String headerAddress, String fromAddress, String replyTo,
                          String ccField, String subject, String body, String htmlBody, Session session) {
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

            //Logic for HTML body....

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

            setSender(msg, headerAddress, fromAddress, replyTo);
            setRcpts(msg, toEmail, ccField);

            System.out.println("Message is ready");
            Transport.send(msg);

            System.out.println(body);

            mailStatus = "Email sent successfully!!";
            System.out.println("Email Sent Successfully!!");

            System.out.println(htmlBody);
        }
        catch (Exception e) {
            mailStatus = e.getMessage();
            e.printStackTrace();
        }
    }

    private void setSender(MimeMessage msg, String headerAddress, String fromAddress, String replyTo) throws Exception {
        //Setting recipients
        if (!headerAddress.equals("")) {
            if (mailFromSpoof==true) {
                msg.setFrom(new InternetAddress(fromAddress, headerAddress));
            } else {
                msg.addHeader("From", headerAddress);
            }
        }
        msg.setReplyTo(InternetAddress.parse(replyTo, false));
        }

    private void setRcpts(MimeMessage msg, String toEmail, String ccField) throws Exception {
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
        if (!ccField.equals("")) {
            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccField, false));
        }

    }
}