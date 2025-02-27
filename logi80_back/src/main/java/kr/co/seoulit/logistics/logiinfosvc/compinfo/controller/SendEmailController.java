package kr.co.seoulit.logistics.logiinfosvc.compinfo.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Properties;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.BodyPart;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.nexacro17.xapi.data.PlatformData;
import com.nexacro17.xapi.data.VariableList;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/compinfo/*")
public class SendEmailController {
   @Autowired
   private javax.sql.DataSource data;
   private Multipart multipart;

   @RequestMapping(value="/estimateReportEmail")
   public void sendEstimateReportEmail(@RequestAttribute("reqData") PlatformData reqData,
                                       @RequestAttribute("resData") PlatformData resData) {
      String iReportFolderPath = "C:\\Users\\wjdgj\\OneDrive\\바탕 화면\\11-18 nexa\\logi80_back\\src\\main\\resources\\report\\Estimate.jrxml";


      HashMap<String, Object> parameters = new HashMap<>();
      // 레포트 이름
      InputStream inputStream = null;
      String to = reqData.getVariable("emailId").getString();
      try {
         String orderDraftNo = reqData.getVariable("orderDraftNo").getString();
         parameters.put("orderDraftNo", orderDraftNo);

         Connection conn = data.getConnection();

         inputStream = new FileInputStream(iReportFolderPath);

         // jrxml 형식으로 읽어옴
         JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
         // jrxml 을 내가 원하는 모양을 가지고옴
         JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
         // 그 틀에 맞춰서 파라메터의 정보를 넣어줌
         JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);

         JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\wjdgj\\OneDrive\\바탕 화면\\11-18 nexa\\logi80_back\\src\\main\\resources\\report\\estimateReport.pdf");

      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         if (inputStream != null) {
            try {
               inputStream.close();
            } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         }
      }
      String fileName="estimateReport.pdf";
      String savePath="C:\\Users\\wjdgj\\OneDrive\\바탕 화면\\11-18 nexa\\logi80_back\\src\\main\\resources\\report";

      String host = "smtp.naver.com";
      final String username = "wjdgjs1230100@naver.com";
      final String password = "R218PY3Y43RU";
                               // VWLYYMUL82JJ

      // Get the session object
      Properties props = new Properties();
      props.put("mail.smtp.ssl.protocols", "TLSv1.2");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.auth", "true");

      Session session = Session.getDefaultInstance(props, new jakarta.mail.Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
         }
      });

      // Compose the message
      try {
         MimeMessage message = new MimeMessage(session);
         message.setFrom(new InternetAddress(username));
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

         // Subject

         message.setSubject("요청하신 견적서 입니다.");
         multipart = new MimeMultipart();

         // Text
         MimeBodyPart mbp1 = new MimeBodyPart();


         mbp1.setText("요청하신 견적서 입니다. ");
         multipart.addBodyPart(mbp1);

         // send the message
         if(fileName != null){
            DataSource source = new FileDataSource(savePath+"\\"+fileName);
            BodyPart messageBodyPart = new MimeBodyPart();
            DataHandler data= new DataHandler(source);
            messageBodyPart.setDataHandler(data);
            messageBodyPart.setFileName(fileName);
            multipart.addBodyPart(messageBodyPart);
         }
         message.setContent(multipart);
         Transport.send(message);
         System.out.println("메일 발송 성공!");

      } catch (MessagingException e) {
         e.printStackTrace();
      }
   }
   @RequestMapping(value="/contractReportEmail")
   public void sendContractReportEmail(@RequestAttribute("reqData") PlatformData reqData,
                                       @RequestAttribute("resData") PlatformData resData) {
      String iReportFolderPath = "C:\\Users\\wjdgj\\OneDrive\\바탕 화면\\11-18 nexa\\logi80_back\\src\\main\\resources\\report\\Contract.jrxml";

      HashMap<String, Object> parameters = new HashMap<>();
      // 레포트 이름
      InputStream inputStream = null;
      String to = reqData.getVariable("emailId").getString();
      try {
         String orderDraftNo = reqData.getVariable("orderDraftNo").getString();
         parameters.put("orderDraftNo", orderDraftNo);

         Connection conn = data.getConnection();

         inputStream = new FileInputStream(iReportFolderPath);

         // jrxml 형식으로 읽어옴
         JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
         // jrxml 을 내가 원하는 모양을 가지고옴
         JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
         // 그 틀에 맞춰서 파라메터의 정보를 넣어줌
         JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);

         JasperExportManager.exportReportToPdfFile(jasperPrint, "\"C:\\Users\\wjdgj\\OneDrive\\바탕 화면\\11-18 nexa\\logi80_back\\src\\main\\resources\\report\\ContractReport.pdf\"");


      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         if (inputStream != null) {
            try {
               inputStream.close();
            } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         }
      }
      String fileName = "ContractReport.pdf";
      String savePath = "C:\\Users\\wjdgj\\OneDrive\\바탕 화면\\11-18 nexa\\logi80_back\\src\\main\\resources\\report";

      String host="smtp.naver.com";
      final String user="qq@naver.com";
      final String password="";//구글은 앱비밀번호 써야됨(다른거는 모르겠음)


      // Get the session object
      Properties props = new Properties();
      props.put("mail.smtp.ssl.protocols", "TLSv1.2");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.auth", "true");

      Session session = Session.getDefaultInstance(props, new jakarta.mail.Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(user, password);
         }
      });

      // Compose the message
      try {
         MimeMessage message = new MimeMessage(session);
         message.setFrom(new InternetAddress(user));
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

         // Subject

         message.setSubject("요청하신 주문서 입니다.");
         multipart = new MimeMultipart();

         // Text
         MimeBodyPart mbp1 = new MimeBodyPart();
         mbp1.setText("요청하신 주문서 입니다. ");
         multipart.addBodyPart(mbp1);

         // send the message
         if(fileName != null){
            DataSource source = new FileDataSource(savePath+"\\"+fileName);
            BodyPart messageBodyPart = new MimeBodyPart();
            DataHandler data= new DataHandler(source);
            messageBodyPart.setDataHandler(data);
            messageBodyPart.setFileName(fileName);
            multipart.addBodyPart(messageBodyPart);
         }
         message.setContent(multipart);
         Transport.send(message);
         System.out.println("메일 발송 성공!");

      } catch (MessagingException e) {
         e.printStackTrace();
      }
   }
}
