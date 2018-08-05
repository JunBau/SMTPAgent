package junbau.tools.smtp;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

public class EmailUtil {

    // Code by JunBau

    private String mailStatus;

    public String getMailStatus() {
        return mailStatus;
    }

    public String sendEmail(String toEmail, String headerAddress, String fromAddress, String replyTo,
                            String ccField, String subject, String body, String htmlBody, Session session,
                            UserInput userData) {
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
            final BodyPart messageBodyPart = new MimeBodyPart();

            msg.setSubject(subject, "UTF-8");

            //Logic for HTML body....

            if (!htmlBody.contains("<p>")) {
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

            if (!userData.getAttachments().isEmpty()) {
                for (int i = 0; i < userData.getAttachments().size(); i++) {
                    addAttachment(messageBodyPart, userData.getAttachments().get(i).toString(),
                            altMP, userData.getShortName().get(i).toString());
                }
            }

            msg.setContent(altMP);

            setSender(msg, headerAddress, fromAddress, replyTo, userData);
            setRcpts(msg, toEmail, ccField);

            System.out.println("Message is ready");
            Transport.send(msg);

            System.out.println(body);

            mailStatus = "Email sent successfully!!";
            System.out.println("Email Sent Successfully!!");

            System.out.println(htmlBody);
            return mailStatus;
        }
        catch (Exception e) {
            mailStatus = e.getMessage();
            e.printStackTrace();
            return e.getMessage();
        }
    }

    private void addAttachment(BodyPart messageBodyPart, String attPath, Multipart altMP, String shortName) {
        try {
            messageBodyPart = new MimeBodyPart();
            String filename = attPath;
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(shortName);
            altMP.addBodyPart(messageBodyPart);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void setSender(MimeMessage msg, String headerAddress, String fromAddress, String replyTo, UserInput userData) throws Exception {
        //Setting recipients
        if (!headerAddress.equals("")) {
            if (userData.isMailFromSpoof()==true) {
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