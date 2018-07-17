package junbau.tools.smtp;

public interface EmailService {

    void setMsgProps(String msg, String html, String subj, String rcpt, String cc);
    void setSenderAddr(String fromAddr, String headerAddr, String replyTo);
    void setSMTPhost(String host);

}
