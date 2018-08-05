package junbau.tools.smtp;

public class UserInput {

    private final String p1Address;
    private final String p2Address;
    private final String msgBody;
    private final String htmlBody;
    private final String toAddress;
    private final String smtpHost;
    private final String usrSubject;
    private final String replyTo;
    private final String msgCCFields;
    private final boolean mailFromSpoof;

    public UserInput(String p1Address, String p2Address, String msgBody, String htmlBody, String toAddress, String smtpHost, String usrSubject, String replyTo, String msgCCFields,
                     boolean mailFromSpoof) {
        this.p1Address = p1Address;
        this.p2Address = p2Address;
        this.msgBody = msgBody;
        this.htmlBody = htmlBody;
        this.toAddress = toAddress;
        this.smtpHost = smtpHost;
        this.usrSubject = usrSubject;
        this.replyTo = replyTo;
        this.msgCCFields = msgCCFields;
        this.mailFromSpoof = mailFromSpoof;
    }

    public String getP1Address() {
        return p1Address;
    }

    public String getP2Address() {
        return p2Address;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public String getHtmlBody() {
        return htmlBody;
    }

    public String getToAddress() {
        return toAddress;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public String getUsrSubject() { return usrSubject; }

    public String getReplyTo() { return replyTo; }

    public String getMsgCCFields() {
        return msgCCFields;
    }

    public boolean isMailFromSpoof() {
        return mailFromSpoof;
    }
}
