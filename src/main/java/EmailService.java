import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class EmailService {

    private String host;
    private int port;
    private String username;
    private String password;

    public EmailService(String host, int port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public void sendMail(String name, String email,String who,String bio) {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", port);
        prop.put("mail.smtp.ssl.trust", host);
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("youremail@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Secret Santa Assigned Person");
            String msg="You got "+who+" who wrote in their bio: "+bio;

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);
            Transport.send(message);
            String data = "Email has been sent to:"+name+", "+email;
            File file = new File("log.txt");//just for tracking successful emails
            FileWriter fr = null;
            try {
                fr = new FileWriter(file,true);
                fr.append(data+"\n");
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("DONE");
        } catch (Exception e) {
            System.out.println("FAILED");
            e.printStackTrace();
        }
    }

}