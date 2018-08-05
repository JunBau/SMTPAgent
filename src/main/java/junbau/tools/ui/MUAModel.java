package junbau.tools.ui;

import javafx.scene.control.*;
import javafx.scene.web.*;
import javafx.stage.*;
import junbau.tools.smtp.SMTPEmailService;
import junbau.tools.smtp.TLSEmail;
import junbau.tools.smtp.UserInput;

import java.util.ArrayList;


public class MUAModel {

    private ArrayList attachments = new ArrayList();
    private ArrayList shortName = new ArrayList();

    public ArrayList getAttachments() {
        return attachments;
    }

    public ArrayList getShortName() {
        return shortName;
    }

    protected void sendMail(TextField smtpHost, TextField usrMailFrom, TextField usrP2,
                            TextArea usrMsgBody, TextField usrMsgSubj, TextField usrMsgTo,
                            CheckBox spoofS2, TextField ccField, HTMLEditor msgHTML, TextField usrReplyTo,
                            Window owner) {

        SMTPEmailService send = new SMTPEmailService();

        if (spoofS2.isSelected()) {
            UserInput userData = new UserInput(usrMailFrom.getText(),usrP2.getText(),usrMsgBody.getText(),
                    msgHTML.getHtmlText(),usrMsgTo.getText(),smtpHost.getText(),usrMsgSubj.getText(),usrReplyTo.getText(),ccField.getText(),
                    true, attachments, shortName);
            try {
                send.sendEmail(userData);
                showAlert(Alert.AlertType.INFORMATION, owner, "Status", send.getMailStatus());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            UserInput userData = new UserInput(usrMailFrom.getText(),usrP2.getText(),usrMsgBody.getText(),
                    msgHTML.getHtmlText(),usrMsgTo.getText(),smtpHost.getText(),usrMsgSubj.getText(),usrReplyTo.getText(),ccField.getText(),
                    false, attachments, shortName);
            try {
                send.sendEmail(userData);
                showAlert(Alert.AlertType.INFORMATION, owner, "Status", send.getMailStatus());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    protected void sendTLS(TextField smtpHost, TextField usrMailFrom, TextField usrP2,
                           TextArea usrMsgBody, TextField usrMsgSubj, TextField usrMsgTo,
                           PasswordField usrPass, TextField usrAuthAcc, TextField usrReplyTo,
                           CheckBox spoofS2, TextField ccField, HTMLEditor msgHTML, Window owner) {

        TLSEmail send = new TLSEmail();

        if (spoofS2.isSelected()) {
            UserInput userData = new UserInput(usrMailFrom.getText(),usrP2.getText(),usrMsgBody.getText(),
                    msgHTML.getHtmlText(),usrMsgTo.getText(),smtpHost.getText(),usrMsgSubj.getText(),usrReplyTo.getText(),ccField.getText(),
                    true, attachments, shortName);
            try {
                send.tlsEmail(usrPass.getText(), usrAuthAcc.getText(), userData);
                showAlert(Alert.AlertType.INFORMATION, owner, "Status", send.getMailStatus());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

            UserInput userData = new UserInput(usrMailFrom.getText(), usrP2.getText(), usrMsgBody.getText(),
                    msgHTML.getHtmlText(), usrMsgTo.getText(), smtpHost.getText(), usrMsgSubj.getText(), usrReplyTo.getText(), ccField.getText(),
                    false, attachments, shortName);

            try {
                send.tlsEmail(usrPass.getText(), usrAuthAcc.getText(), userData);
                showAlert(Alert.AlertType.INFORMATION, owner, "Status", send.getMailStatus());
            } catch (Exception e) {
                e.printStackTrace();
            }
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
