package registrationForm;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.AuthenticationFailedException;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.swing.JOptionPane;

import message.errorMessage;
import message.successMessage;
public class testEmail {
    static final String FROM = "kxzcvtbot@gmail.com";
    static final String FROMNAME = "이메일 테스트";
    static final String TO = "hjkim2@robogates.com";
    static final String SMTP_USERNAME = "kxzcvtbot@gmail.com";
    static final String SMTP_PASSWORD ="ads485912^^!";
    
    static final String HOST = "smtp.gmail.com";
    static final int PORT = 25;
    
    static final String SUBJECT = "java E-mail testing";
    
    private Multipart multipart;
    StringBuilder sb;
    
    static final String BODY = String.join(
        System.getProperty("line.separator"),
        "<h1>JAVA send E-mail test</h1>",
        "<p>test</p>",
        "<p>ggggggggggggggg</p>",
        "<p>한글한글한글</p>",
        "<p>가나다라마바사아자차카타파하</p>"
    );
//    public static void main(String[] args){
//        try {
//			new testEmail("견적서 양식.xlsx");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    }
    public testEmail(String fileName) throws Exception {
    	
    	Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props);
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM, FROMNAME));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
        msg.setSubject(SUBJECT);
        addFile("excelOutput", fileName);
        // text/plain
        //msg.setContent(BODY, "text/html;charset=utf-8");
        MimeBodyPart textbody = new MimeBodyPart();
        textbody.setContent(BODY, "text/html;charset=utf-8");
        multipart.addBodyPart(textbody);
        msg.setContent(multipart);
        
        Transport transport = session.getTransport();
        try {
            System.out.println("Sending...");
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);
            transport.sendMessage(msg, msg.getAllRecipients());
            System.out.println("이메일 전송 완료.");
            JOptionPane.showMessageDialog(null, "E-mail이 성공적으로 전송되었습니다.","Success",JOptionPane.INFORMATION_MESSAGE);
        } catch (AuthenticationFailedException e) {
			System.out.println("정보 불일치.");
			JOptionPane.showMessageDialog(null, "입력하신 ID와 Password가 일치하지 않습니다.","Wrong Data",JOptionPane.ERROR_MESSAGE);
		}catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("The email was not sent.");
            System.out.println("Error message: " + ex.getMessage());
            new errorMessage("send a E-mail");
        } 
        finally {
            transport.close();
        }
	}
    public void addFile(String savePath,String fileName){
        try{
            //파일 첨부
        	sb=new StringBuilder();
        	multipart = new MimeMultipart();
            sb.append("File : " + fileName).append("\n");
            if(multipart!=null&&fileName != null){

                DataSource source = new FileDataSource(savePath+"/"+fileName);
                BodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(MimeUtility.encodeText(fileName,"EUC-KR","B"));
                multipart.addBodyPart(messageBodyPart);
            }
        }catch (AddressException addr_e) {  //주소를 입력하지 않을 경우
            JOptionPane.showMessageDialog(null, "메일을 입력해주세요", "메일주소입력", JOptionPane.ERROR_MESSAGE);
            addr_e.printStackTrace();
        }catch (MessagingException msg_e) { //메시지에 이상이 있을 경우
            JOptionPane.showMessageDialog(null, "메일을 제대로 입력해주세요.", "오류발생", JOptionPane.ERROR_MESSAGE);
            msg_e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
