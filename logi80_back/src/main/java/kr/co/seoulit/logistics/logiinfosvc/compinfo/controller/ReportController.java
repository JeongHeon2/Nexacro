package kr.co.seoulit.logistics.logiinfosvc.compinfo.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@Controller
@RequestMapping("/compinfo/*")
public class ReportController {

	@Autowired
	private DataSource data;
	@Autowired
	private ResourceLoader resourceLoader;

	@RequestMapping(value = "/report/estimate")
	public void estimateReport(HttpServletRequest request, HttpServletResponse response) {

		/** src 경로 찾아주는 소스 */
		Resource resource = resourceLoader.getResource("classpath:");
		String projectPath;
		try {
			projectPath = resource.getURI().getPath();
			String[] pathParts = projectPath.split("/");
			int buildPathIndex = -1;
			for (int i = 0; i < pathParts.length; i++) {
				if ("build".equals(pathParts[i])) {
					buildPathIndex = i;
					break;
				}
			}
			StringBuilder resultPath = new StringBuilder();
			for (int i = 0; i <= buildPathIndex-1; i++) {
				resultPath.append(pathParts[i]);
				if (i < buildPathIndex-1) {
					resultPath.append("/");
				}
			}
			System.out.println("조작된 경로: " + resultPath);
			projectPath = resultPath.toString();
		} catch (IOException e) {
			projectPath = "프로젝트파일 경로를 읽는데 실패하였습니다.";
			e.printStackTrace();
		}
		/*------------------------*/

		String iReportFolderPath = projectPath + "/src/main/resources/report/Estimate.jrxml";
		System.out.println(iReportFolderPath);
		HashMap<String, Object> parameters = new HashMap<>();
		// 레포트 이름
		InputStream inputStream = null;
		ServletOutputStream out = null;
		try {

			response.setCharacterEncoding("UTF-8");
			String orderDraftNo = request.getParameter("orderDraftNo");
			parameters.put("orderDraftNo", orderDraftNo);

			Connection conn = data.getConnection();

			inputStream = new FileInputStream(iReportFolderPath);

			// jrxml 형식으로 읽어옴
			JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
			// jrxml 을 내가 원하는 모양을 가지고옴
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			// 그 틀에 맞춰서 파라미터의 정보를 넣어줌
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);

			out = response.getOutputStream();
			response.setContentType("application/pdf");
			JasperExportManager.exportReportToPdfStream(jasperPrint, out);
			out.println();
			out.flush();

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
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@RequestMapping(value = "/report/contract")
	public void contractReport(HttpServletRequest request, HttpServletResponse response) {
		String iReportFolderPath = "C:\\Users\\wjdgj\\OneDrive\\바탕 화면\\11-18 nexa\\logi80_back\\src\\main\\resources\\report\\Contract.jrxml";

		HashMap<String, Object> parameters = new HashMap<>();
		// 레포트 이름
		InputStream inputStream = null;
		ServletOutputStream out = null;
		try {

			response.setCharacterEncoding("UTF-8");
			String orderDraftNo = request.getParameter("orderDraftNo");
			parameters.put("orderDraftNo", orderDraftNo);

			Connection conn = data.getConnection();

			inputStream = new FileInputStream(iReportFolderPath);

			// jrxml 형식으로 읽어옴
			JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
			// jrxml 을 내가 원하는 모양을 가지고옴
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			// 그 틀에 맞춰서 파라메터의 정보를 넣어줌
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);

			out = response.getOutputStream();
			response.setContentType("application/pdf");
			JasperExportManager.exportReportToPdfStream(jasperPrint, out);
			out.println();
			out.flush();

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
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
