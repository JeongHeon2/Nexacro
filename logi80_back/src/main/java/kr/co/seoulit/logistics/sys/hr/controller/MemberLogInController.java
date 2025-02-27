package kr.co.seoulit.logistics.sys.hr.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nexacro17.xapi.data.PlatformData;
import kr.co.seoulit.logistics.logiinfosvc.compinfo.to.CompanyTO;
import kr.co.seoulit.logistics.logiinfosvc.compinfo.to.WorkplaceTO;
import kr.co.seoulit.logistics.sys.hr.service.HRService;
import kr.co.seoulit.logistics.sys.hr.to.EmpInfoTO;
import kr.co.seoulit.logistics.sys.util.DatasetBeanMapper;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/hr/*")
public class MemberLogInController {
    
	
	private HRService hrService;
	private final DatasetBeanMapper datasetBeanMapper;
	
	@RequestMapping(value="/login")
    public void LogInCheck(HttpServletRequest request, HttpServletResponse response) throws Exception{

        
		PlatformData reqData = (PlatformData) request.getAttribute("reqData"); // 넥사에서 들어온거를 서버가 받는거
		PlatformData resData = (PlatformData) request.getAttribute("resData"); // 서버에서 클라이언트로 보내는 거

		String companyCode = datasetBeanMapper.datasetToBean(reqData, CompanyTO.class).getCompanyCode();
		String workplaceCode = datasetBeanMapper.datasetToBean(reqData, WorkplaceTO.class).getWorkplaceCode();
		String userId = reqData.getVariable("userId").getString();
		String userPassword = reqData.getVariable("userPassWord").getString();
		
        EmpInfoTO TO = hrService.accessToAuthority(companyCode, workplaceCode, userId, userPassword);
        
        if (TO != null) {
        	datasetBeanMapper.beanToDataset(resData, TO, EmpInfoTO.class);
        }
    }
	
}
