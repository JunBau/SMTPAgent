package junbau.tools.smtp;

public class UserInput {

    private String p1Address;
    private String p2Address;
    private String msgBody;
    private String htmlBody;
    private String toAddress;
    private String smtpHost;
    private String usrSubject;
    private String replyTo;
    private String msgCCFields;

    public UserInput() {

    }

    public UserInput(String p1Address, String p2Address, String msgBody, String htmlBody, String toAddress, String smtpHost, String usrSubject, String replyTo, String msgCCFields) {
        this.p1Address = p1Address;
        this.p2Address = p2Address;
        this.msgBody = msgBody;
        this.htmlBody = htmlBody;
        this.toAddress = toAddress;
        this.smtpHost = smtpHost;
        this.usrSubject = usrSubject;
        this.replyTo = replyTo;
        this.msgCCFields = msgCCFields;
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

    public String getUsrSubject() {
        return usrSubject;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public String getMsgCCFields() {
        return msgCCFields;
    }

    public void setP1Address(String p1Address) {
        this.p1Address = p1Address;
    }

    public void setP2Address(String p2Address) {
        this.p2Address = p2Address;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }

    public void setHtmlBody(String htmlBody) {
        this.htmlBody = htmlBody;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public void setUsrSubject(String usrSubject) {
        this.usrSubject = usrSubject;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    public void setMsgCCFields(String msgCCFields) {
        this.msgCCFields = msgCCFields;
    }
}
