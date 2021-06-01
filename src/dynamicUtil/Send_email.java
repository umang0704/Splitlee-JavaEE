package dynamicUtil;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Send_email {
	
	public static void sendEmail(String email, String subject, String body) {
		try {
			final String SEmail = "projects.umang.web@gmail.com";
			final String SPass = "Password1@projects.umang.web";
			final String REmail = email;
			final String Sub = subject;
			final String Body = body;

			Properties prop = new Properties();
			prop.put("mail.smtp.host", "smtp.gmail.com");
			prop.put("mail.smtp.port", "587");
			prop.put("mail.smtp.auth", "true");
			prop.put("mail.smtp.starttls.enable", "true");

			Session ses = Session.getInstance(prop, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(SEmail, SPass);
				}
			});

			Message message = new MimeMessage(ses);
			message.setFrom(new InternetAddress(SEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(REmail));
			message.setSubject(Sub);
			message.setContent(Body, "text/html");

			Transport.send(message); 
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
	
}
