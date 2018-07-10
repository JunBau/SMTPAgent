package junbau.tools.smtp;

public class UserInput {

    private static String p1Address;
    private static String p2Address;
    private static String msgBody;
    private static String htmlBody;
    private static String toAddress;
    private static String smtpHost;
    private static String usrSubject;
    private static String replyTo;
    private static String msgCCFields;


    public static String getHtmlBody() {
        return htmlBody;
    }

    public static void setHtmlBody(String htmlBody) {
        UserInput.htmlBody = htmlBody;
    }

    public static void setMsgCCFields(String msgCCFields) {
        UserInput.msgCCFields = msgCCFields;
    }

    public static String getMsgCCFields() {
        return msgCCFields;
    }

    public static String getReplyTo() {
        return replyTo;
    }

    public static void setReplyTo(String replyTo) {
        UserInput.replyTo = replyTo;
    }

    public static String getToAddress() {
        return toAddress;
    }

    public static void setToAddress(String toAddress) {
        UserInput.toAddress = toAddress;
    }

    public static String getUsrSubject() {
        return usrSubject;
    }

    public static void setUsrSubject(String usrSubject) {
        UserInput.usrSubject = usrSubject;
    }

    public static String getSmtpHost() {
        return smtpHost;
    }

    public static void setSmtpHost(String smtpHost) {
        UserInput.smtpHost = smtpHost;
    }

    public static String getP1Address() {
        return p1Address;
    }

    public static void setP1Address(String p1Address) {
        UserInput.p1Address = p1Address;
    }

    public static String getP2Address() {
        return p2Address;
    }

    public static void setP2Address(String p2Address) {
        UserInput.p2Address = p2Address;
    }

    public static String getMsgBody() {
        return msgBody;
    }

    public static void setMsgBody(String msgBody) {
        UserInput.msgBody = msgBody;
    }
}
