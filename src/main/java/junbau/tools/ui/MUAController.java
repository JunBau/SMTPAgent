package junbau.tools.ui;

import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Window;
import junbau.tools.smtp.EmailUtil;


public class MUAController extends MUAModel {

    //Settings Pane
    public Tab usrConf;
    public TextField smtpHost;
    public TextField usrMailFrom;
    public TextField usrP2;
    public PasswordField usrPass;
    public CheckBox smtpAuth;
    public TextField smtpAuthAcc;
    public TextField usrReplyTo;


    //Agent Pane
    public Tab usrMua;
    public TextArea usrMsgBody;
    public TextField usrMsgSubj;
    public TextField usrMsgCc;
    public TextField usrMsgTo;
    public Button usrMsgSend;
    public CheckBox spoofS2;
    public HTMLEditor msgHTML;


    public void btnSend(MouseEvent mouseEvent) {
        Window owner = usrMsgSend.getScene().getWindow();
        if (smtpAuth.isSelected()) {
            sendTLS(smtpHost,usrMailFrom,usrP2,usrMsgBody,
                    usrMsgSubj,usrMsgTo,usrPass,smtpAuthAcc,
                    usrReplyTo, spoofS2, usrMsgCc, msgHTML);
            showAlert(Alert.AlertType.INFORMATION, owner, "Status", EmailUtil.getMailStatus());
        } else {
            sendMail(smtpHost,usrMailFrom,usrP2,usrMsgBody,
                    usrMsgSubj,usrMsgTo, spoofS2, usrMsgCc,
                    msgHTML);
            showAlert(Alert.AlertType.INFORMATION, owner, "Status", EmailUtil.getMailStatus());
        }
    }
}
