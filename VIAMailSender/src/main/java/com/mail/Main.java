package com.mail;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class Main {
	
	static final String CSV_PATH = "data/";
	static final String INPUT_CSV = CSV_PATH + "input.csv";
	static final String SENDER_EMAIL = "MBFS_Insights@daimler.com";
	static final String SUBJECT_EMAIL = "Mercedes-Benz Financial Services USA invites you to join the MBFScommunity";
	
	private static void readAndSend(EmailService emailService){
		
		
		System.out.println("starting csv reader..");
        CSVReader reader = null;
        CSVWriter writer = null;
        
        try{
        	reader = new CSVReader(new FileReader(INPUT_CSV));
        	String outputFileName = CSV_PATH+"output_"+ new SimpleDateFormat("yyyy_MM_dd_HH_mm").format(new Date()).toString()+".csv";
        	
        	System.out.println("For successful emails, check the output file = "+outputFileName);
            writer = new CSVWriter(new FileWriter(outputFileName));
            String[] line;
        
        while ((line = reader.readNext()) != null) {
            writer.writeNext(line);
            EmailData emailData = new EmailData();
            emailData.setRecipientEmail(line[0]);
            emailData.setSenderEmail(SENDER_EMAIL);
            if(StringUtils.isEmpty(line[1])){
            	
            	String name = line[0].split("\\.")[0]; //get the firstname from email
            	emailData.setRecipientname(name);
            	
            }else{
            	emailData.setRecipientname(line[1]);            	
            }
            
            emailData.setSubject(SUBJECT_EMAIL);
            emailService.sendEmail(new Locale("en"), emailData);
            
            System.out.println("sent email to "+emailData.getRecipientEmail());
            
        }
        }catch(IOException e){
        	e.printStackTrace();
        } catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
        	try {
				reader.close();
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        
		
	}
	
	
	public static void main(String[] args) {
		
		
		ApplicationContext context = new ClassPathXmlApplicationContext("context/rest-servlet.xml");
		EmailService mailService = (EmailService) context.getBean("emailService");
		
		readAndSend(mailService);
			
	}

}
