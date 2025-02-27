package kr.co.seoulit.logistics.busisvc.sales.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.nexacro17.xapi.data.PlatformData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import kr.co.seoulit.logistics.busisvc.logisales.dto.ContractInfoResDto;
import kr.co.seoulit.logistics.busisvc.logisales.to.ContractDetailInMpsAvailableTO;
import kr.co.seoulit.logistics.busisvc.sales.to.SalesPlanEntity;
import kr.co.seoulit.logistics.busisvc.sales.to.SalesPlanTO;
import kr.co.seoulit.logistics.logiinfosvc.compinfo.to.CompanyTO;
import kr.co.seoulit.logistics.sys.util.DatasetBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import kr.co.seoulit.logistics.busisvc.sales.service.SalesService;

@RestController
@RequestMapping("/sales/*")
public class SalesPlanController {

	@Autowired
	private SalesService salesService;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	ModelMap map = null;

	private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 json 변환

	@RequestMapping(value = "/saleplan/list")
	// get 데이터 조회, 데이터를 가져오는 작업을 수행
	public void searchSalesPlanInfo(@RequestAttribute("reqData") PlatformData reqData,
									@RequestAttribute("resData") PlatformData resData) throws Exception{
	// public ModelMap searchSalesPlanInfo(HttpServletRequest request, HttpServletResponse response) {
		String startDate = reqData.getVariableList().getString("startDate");
		String endDate = reqData.getVariableList().getString("endDate");
		// String dateSearchCondition = request.getParameter("dateSearchCondition");

		System.out.println("\n" + startDate);
		System.out.println(endDate + "\n");

		ArrayList<SalesPlanEntity> salesPlanEntityList = salesService.getSalesPlanList(startDate, endDate);

		datasetBeanMapper.beansToDataset(resData, salesPlanEntityList, SalesPlanEntity.class);


//		try {
//			ArrayList<SalesPlanEntity> salesPlanEntityList = salesService.getSalesPlanList(startDate, endDate);
//
//			// map.put("gridRowJson", salesPlanEntityList);
//			map.put("errorCode", 1);
//			map.put("errorMsg", "성공");
//		} catch (Exception e1) {
//			e1.printStackTrace();
//			map.put("errorCode", -1);
//			map.put("errorMsg", e1.getMessage());
//		}
//		return map;

	}

	@RequestMapping(value = "/saleplan/batch", method = RequestMethod.POST)
	// post 데이터 생성, 서버에 변경을 가하는 작업 수행
	public ModelMap batchSalesPlanListProcess(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("\n코드가 정상적으로 실행되었습니다.\n");
		String batchList = request.getParameter("batchList");
		try {
			ArrayList<SalesPlanEntity> salesPlanEntityList = gson.fromJson(batchList, new TypeToken<ArrayList<SalesPlanEntity>>() {
			}.getType());
			HashMap<String, Object> resultMap = salesService.batchSalesPlanListProcess(salesPlanEntityList);

			map.put("result", resultMap);
			map.put("errorCode", 1);
			map.put("errorMsg", "성공");
		} catch (Exception e1) {
			e1.printStackTrace();
			map.put("errorCode", -1);
			map.put("errorMsg", e1.getMessage());
		}
		return map;
	}
	// 민성 24 11 05 코드 추가 (계획 저장 버튼 클릭시 sql 에 저장됨)
	// 변경 시 salesPlanEntity, salesPlanTo 이 부분 salesAmount unitPriceOfSales sumPriceOfSales string에서 int 로 변경
	// 마찬가지로 넥사크로 gds_salesplan 가서 테이블 타입 string -> int 로 변경

	@RequestMapping(value = "/salesplan/batch")
	// post 데이터 생성, 서버에 변경을 가하는 작업 수행
	public void batchsalesplan(@RequestAttribute("reqData") PlatformData reqData) throws Exception {
		System.out.println("\n코드가 정상적으로 실행되었습니다.\n");
		// PlatformData reqData = (PlatformData) request.getAttribute("reqData");
		// SalesPlanEntity salesPlanEntityList = datasetBeanMapper.datasetToBean(reqData, SalesPlanEntity.class);

		ArrayList<SalesPlanEntity> salesPlanEntityList
				= (ArrayList<SalesPlanEntity>) datasetBeanMapper.datasetToBeans(reqData, SalesPlanEntity.class);

		for (SalesPlanEntity salesPlan : salesPlanEntityList) {
			System.out.println(salesPlan);
			salesService.insertSalesPlan(salesPlan);
		}
		System.out.println("\n" + salesPlanEntityList + "\n");
	}
	// 민성 24 11 05 코드 추가

	//	@RequestMapping(value = "/selectsalesplan/batch")
	//	public void selectsalesplan(@RequestAttribute("reqData") PlatformData reqData,
	//								@RequestAttribute("resData") PlatformData resData) throws Exception {
	//		ArrayList<SalesPlanTO> selectSalesPlanEntityList = salesService.selectSalesPlanList();
	//		datasetBeanMapper.beansToDataset(resData, selectSalesPlanEntityList, SalesPlanTO.class);
	//	}

	// 민성 24 11 06 회원 번호 조회 코드 추가
	@RequestMapping(value = "/selectsalesplan/batch")
	public void selectsalesplan(@RequestAttribute("reqData") PlatformData reqData,
								@RequestAttribute("resData") PlatformData resData) throws Exception {
		SalesPlanTO selectSalesPlanEntityList = salesService.selectLastSalesPlan();

		SalesPlanTO salesPlanTO = salesService.selectLastSalesPlan();
		List<SalesPlanTO> salesPlanEntityList = new ArrayList<>();
		salesPlanEntityList.add(salesPlanTO);

		datasetBeanMapper.beansToDataset(resData, salesPlanEntityList, SalesPlanTO.class);
	}
}