package junbau.tools.ui;

import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.web.*;
import javafx.stage.*;

import java.io.File;

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
    
    public TextField attDisplay;
    public Button attBrowse;

    //Evaluate this

    public void btnSend(MouseEvent mouseEvent) {
        Window owner = usrMsgSend.getScene().getWindow();

        if (smtpAuth.isSelected()) {
            sendTLS(smtpHost,usrMailFrom,usrP2,usrMsgBody,
                    usrMsgSubj,usrMsgTo,usrPass,smtpAuthAcc,
                    usrReplyTo, spoofS2, usrMsgCc, msgHTML, owner);
        } else {
            sendMail(smtpHost,usrMailFrom,usrP2,usrMsgBody,
                    usrMsgSubj,usrMsgTo, spoofS2, usrMsgCc,
                    msgHTML, usrReplyTo, owner);
        }
    }

    public void clickBrowse(MouseEvent mouseEvent) {
        Stage browseStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        File selectedDirectory = fileChooser.showOpenDialog(browseStage);

        if(selectedDirectory == null){
            //No Directory selected
        }else{
            System.out.println(selectedDirectory.getAbsolutePath());
            attDisplay.appendText(selectedDirectory.getName() + "; ");
            getAttachments().add(selectedDirectory.getAbsolutePath());
            getShortName().add(selectedDirectory.getName());

            if (!getAttachments().isEmpty()) {
                for (int i = 0; i < getAttachments().size(); i++) {
                    System.out.println(getAttachments().get(i).toString());
                }
            }
        }
    }
}
