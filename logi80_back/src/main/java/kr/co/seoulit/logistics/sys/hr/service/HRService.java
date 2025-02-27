package kr.co.seoulit.logistics.sys.hr.service;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.seoulit.logistics.busisvc.sales.to.ReleaseInfoTO;
import kr.co.seoulit.logistics.sys.hr.exception.IdNotFoundException;
import kr.co.seoulit.logistics.sys.hr.exception.PwMissMatchException;
import kr.co.seoulit.logistics.sys.hr.exception.PwNotFoundException;
import org.springframework.ui.ModelMap;

import kr.co.seoulit.logistics.sys.hr.to.AuthorityGroupTO;
import kr.co.seoulit.logistics.sys.hr.to.AuthorityInfoGroupTO;
import kr.co.seoulit.logistics.sys.hr.to.EmpInfoTO;
import kr.co.seoulit.logistics.sys.hr.to.EmployeeBasicTO;
import kr.co.seoulit.logistics.sys.hr.to.EmployeeDetailTO;
import kr.co.seoulit.logistics.sys.hr.to.EmployeeSecretTO;
import kr.co.seoulit.logistics.sys.hr.to.EmployeeTO;
import kr.co.seoulit.logistics.sys.hr.to.MenuAuthorityTO;

public interface HRService {

	public ArrayList<EmpInfoTO> getAllEmpList(String searchCondition, String[] paramArray);

	public EmpInfoTO getEmpInfo(String companyCode, String empCode);

	public String getNewEmpCode(String companyCode);

	public Boolean checkEmpCodeDuplication(String companyCode, String newEmpCode);

	public Boolean checkUserIdDuplication(String companyCode, String newUserId);

	public ModelMap batchEmpBasicListProcess(ArrayList<EmployeeBasicTO> empBasicList);

	public ModelMap batchEmpDetailListProcess(ArrayList<EmployeeDetailTO> empDetailList);

	public ModelMap batchEmpSecretListProcess(ArrayList<EmployeeSecretTO> empSecretList);

	//권한
	public EmpInfoTO accessToAuthority(String companyCode, String workplaceCode, String inputId, String inputPassWord)
			throws IdNotFoundException, PwMissMatchException, PwNotFoundException;

	public String[] getAllMenuList();

	public ArrayList<AuthorityGroupTO> getUserAuthorityGroup(String empCode);

	public ArrayList<AuthorityInfoGroupTO> getAuthorityGroup();

	public ArrayList<MenuAuthorityTO> getMenuAuthority(String authorityGroupCode);

	public void insertEmployeeAuthorityGroup(String empCode, String authorityGroupCode);

	public void insertMenuAuthority(String authorityGroupCode, ArrayList<MenuAuthorityTO> menuAuthorityTOList);

	public ArrayList<EmployeeTO> getEmployeeList();

	public HashMap<String,Object> updateImage(String image, String empCode);
}
