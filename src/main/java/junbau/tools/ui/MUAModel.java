package junbau.tools.ui;

import javafx.scene.control.*;
import javafx.scene.web.*;
import javafx.stage.*;
import junbau.tools.smtp.EmailUtil;
import junbau.tools.smtp.SMTPEmail;
import junbau.tools.smtp.TLSEmail;


public class MUAModel {


    protected void sendMail(TextField smtpHost, TextField usrMailFrom, TextField usrP2,
                            TextArea usrMsgBody, TextField usrMsgSubj, TextField usrMsgTo,
                            CheckBox spoofS2, TextField ccField, HTMLEditor msgHTML, TextField usrReplyTo) {


        if (spoofS2.isSelected()) {
            EmailUtil.setMailFromSpoof(true);
        }

        SMTPEmail send = new SMTPEmail();
        send.setMsgProps(usrMsgBody.getText(), msgHTML.getHtmlText(), usrMsgSubj.getText(), usrMsgTo.getText(), ccField.getText());
        send.setSenderAddr(usrMailFrom.getText(), usrP2.getText(), usrReplyTo.getText());
        send.setSMTPhost(smtpHost.getText());
        try {
            send.sendEmail();
            EmailUtil.setMailFromSpoof(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void sendTLS(TextField smtpHost, TextField usrMailFrom, TextField usrP2,
                           TextArea usrMsgBody, TextField usrMsgSubj, TextField usrMsgTo,
                           PasswordField usrPass, TextField usrAuthAcc, TextField usrReplyTo,
                           CheckBox spoofS2, TextField ccField, HTMLEditor msgHTML) {

        if (spoofS2.isSelected()) {
            EmailUtil.setMailFromSpoof(true);
        }

        TLSEmail send = new TLSEmail();
        send.setMsgProps(usrMsgBody.getText(), msgHTML.getHtmlText(), usrMsgSubj.getText(), usrMsgTo.getText(), ccField.getText());
        send.setSenderAddr(usrMailFrom.getText(), usrP2.getText(), usrReplyTo.getText());
        send.setSMTPhost(smtpHost.getText());
        try {
            send.tlsEmail(usrPass.getText(), usrAuthAcc.getText());
            EmailUtil.setMailFromSpoof(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

}
