import junbau.tools.smtp.*;
import org.junit.Test;
import org.mockito.Mock;

import javax.mail.Session;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


public class SMTPTest {

    @Mock
    UserInput userDataMock;

    @Mock
    Session session;

    @Test
    public void testEmail() {
        EmailUtil test0 = mock(EmailUtil.class);

        when(test0.sendEmail("test@test.com", "", "from@test.com", "",
                "", "Testing", "This is a test", "", session, userDataMock)).thenReturn("Success!");
        

        assertEquals(test0.sendEmail("test@test.com", "", "from@test.com", "",
                "", "Testing", "This is a test", "", session, userDataMock), "Success!");
    }

    @Test
    public void sendNormalEmail () {
        SMTPEmailService test = mock(SMTPEmailService.class);

        doNothing().when(test).sendEmail(userDataMock);
    }

    @Test
    public void checkMailStatus () {
        UserInput userData = new UserInput("","", "", "",
                "", "", "","", "", false);

        SMTPEmailService test2 = mock(SMTPEmailService.class);
        test2.sendEmail(userData);

        when(test2.getMailStatus()).thenReturn(("False"));

        assertEquals(test2.getMailStatus(), "False");

    }
}
