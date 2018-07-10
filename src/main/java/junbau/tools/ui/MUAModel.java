package junbau.tools.ui;

import javafx.scene.control.*;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Window;
import junbau.tools.smtp.EmailUtil;
import junbau.tools.smtp.SMTPEmail;
import junbau.tools.smtp.TLSEmail;
import junbau.tools.smtp.UserInput;


public class MUAModel {

    protected void sendMail(TextField smtpHost, TextField usrMailFrom, TextField usrP2,
                            TextArea usrMsgBody, TextField usrMsgSubj, TextField usrMsgTo,
                            CheckBox spoofS2, TextField ccField, HTMLEditor msgHTML) {

        UserInput.setSmtpHost(smtpHost.getText());
        UserInput.setP1Address(usrMailFrom.getText());
        UserInput.setP2Address(usrP2.getText());
        UserInput.setMsgBody(usrMsgBody.getText());
        UserInput.setUsrSubject(usrMsgSubj.getText());
        UserInput.setToAddress(usrMsgTo.getText());
        UserInput.setMsgCCFields(ccField.getText());
        UserInput.setHtmlBody(msgHTML.getHtmlText());

        if (spoofS2.isSelected()) {
            EmailUtil.setMailFromSpoof(true);
        }

        SMTPEmail send = new SMTPEmail();
        send.sendEmail();
        EmailUtil.setMailFromSpoof(false);
    }

    protected void sendTLS(TextField smtpHost, TextField usrMailFrom, TextField usrP2,
                           TextArea usrMsgBody, TextField usrMsgSubj, TextField usrMsgTo,
                           PasswordField usrPass, TextField usrAuthAcc, TextField usrReplyTo,
                           CheckBox spoofS2, TextField ccField, HTMLEditor msgHTML) {

        UserInput.setSmtpHost(smtpHost.getText());
        UserInput.setP1Address(usrMailFrom.getText());
        UserInput.setP2Address(usrP2.getText());
        UserInput.setUsrSubject(usrMsgSubj.getText());
        UserInput.setToAddress(usrMsgTo.getText());
        UserInput.setReplyTo(usrReplyTo.getText());
        UserInput.setMsgCCFields(ccField.getText());
        UserInput.setMsgBody(usrMsgBody.getText());
        UserInput.setHtmlBody(msgHTML.getHtmlText());

        if (spoofS2.isSelected()) {
            EmailUtil.setMailFromSpoof(true);
        }

        TLSEmail send = new TLSEmail();
        send.tlsEmail(usrPass.getText(),usrAuthAcc.getText());

        EmailUtil.setMailFromSpoof(false);
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
