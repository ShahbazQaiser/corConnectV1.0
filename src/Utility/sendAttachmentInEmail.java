package Utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class sendAttachmentInEmail {
    protected WebDriver driver;
    // public static void main(String[] args) {
    public void sentemail() {
        // Recipient's email ID needs to be mentioned.
        String to = "shahbaz.qaiser@tenpearls.com";

        // Sender's email ID needs to be mentioned
        String from = "sqa10pcc@gmail.com";

        final String username = "sqa10pcc@gmail.com";//change accordingly
        final String password = "tenpearls";//change accordingly

        // Assuming you are sending email through relay.jangosmtp.net
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "25");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("Testing Subject");

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            String string1 = "Please see the attached HTML report of Testcases \n";
            String string2 = "Regards \n";
            String string3 = "Shahbaz Qaiser";
            System.out.println(string1 + string2 + string3);
            messageBodyPart.setText(string1 + string2 + string3);

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            String filename = "C:\\Users\\Sami\\Desktop\\corConnectV1.0\\test-output\\emailable-report.html";
           // C:/Users/Sami/Desktop/corConnectV1.0/test-output/emailable-report.html
            //C:/Users/Sami/workspace/corconnectv2/test-output/Suite/ChromeTest.html
            // C:\\Users\\Sami\\workspace\\corconnectv2\\test-output\\index.html
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    public sendAttachmentInEmail(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
}