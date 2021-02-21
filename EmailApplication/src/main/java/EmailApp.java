//import java.net.PasswordAuthentication;

import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;

import javax.mail.Session;
import javax.mail.Transport;
//import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailApp{
	
	public static void main(String arg[]) 
	{
		
		//Add recipient details
		//String to = "fazlulhaquue.jony@gmail.com";
		String to = "manik.roy@metafour.com ";
		
		//Add sender details
		
		String from = "fazlul.haque@metafour.com";
		final String username = "fazlul.haque@metafour.com" ;
		final String password ="Fazlul%1234";
		String  host = "smtp.gmail.com";                                 
		
		//put details to the property
		Properties props = new Properties();
		//props.put("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.user", "fazlul.haque@metafour.com");
		props.setProperty("mail.smtp.password", "Fazlul%1234");
		props.setProperty("mail.smtp.auth", "true"); 
		props.put("mail.smtp.host",host);
		props.put("mail.smtp.port", "465");
	    props.put("mail.smtp.socketFactory.port", "465");
	    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    props.put("mail.smtp.socketFactory.fallback", "false");
		
		//***** Password Authentication Showing error***********8
		
		// get the session object with authentication
	    
	    Session session = Session.getInstance(props,
	    	    new Authenticator() {
	    	        protected PasswordAuthentication  getPasswordAuthentication() {
	    	        return new PasswordAuthentication(
	    	        		username, password);
	    	                }
	    	    });
	    
	    
		//default session
		//Session session = Session.getDefaultInstance(props);
		
		try {
		// create a default MimeMessge object
		Message message = new MimeMessage(session);
		
		// Add form details
		message.setFrom(new InternetAddress(from));
		
		// Add Recipient details
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		
		// Add mail's Subject
		message.setSubject("Test Mail");
		
		
	    //** add massage body **
		//mimeBody Part is for add mail body helper
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setText("Its a test mail. It is also body part of the mail. and it has four attachment");
		
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		
        // Part two is attachment
      //  messageBodyPart = new MimeBodyPart();
        
        //for multiple Attachment
		String[] filename = new String[4];
		filename[0] = "/home/internpc-4/Downloads/job-report-19122019-103051.pdf";
		filename[1] = "/home/internpc-4/Documents/abc.jpeg";
		filename[2] = "/home/internpc-4/Documents/klm.txt";
		filename[3] = "/home/internpc-4/Documents/block.png";
		
		
		if(filename != null && filename.length > 0){
		    for (String filePath : filename) {
		        MimeBodyPart attachPart = new MimeBodyPart();
		        try {
		        	System.out.println(filePath);
		            attachPart.attachFile(filePath);
		        } catch (IOException ex) {
		            ex.printStackTrace();
		        }
		        
		        multipart.addBodyPart(attachPart);
		    }
		}

		
		// for single Attachment
		/*
        DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);
        multipart.addBodyPart(messageBodyPart);
        multipart.addBodyPart(messageBodyPart);
         */
		
		
		
        // Send the complete message parts
        message.setContent(multipart); 
        
        // Send message
        Transport.send(message);
		}
		catch(MessagingException e)
		{
			 throw new RuntimeException(e);
		}
		
		
	}



}
