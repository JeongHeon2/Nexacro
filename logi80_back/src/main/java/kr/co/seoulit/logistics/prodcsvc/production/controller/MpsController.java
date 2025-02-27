package kr.co.seoulit.logistics.prodcsvc.production.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import kr.co.seoulit.logistics.busisvc.logisales.dto.ContractDetailInMpsAvailableResDto;
import kr.co.seoulit.logistics.prodcsvc.production.entity.MpsEntity;
import kr.co.seoulit.logistics.prodcsvc.production.to.MpsReqTO;
import kr.co.seoulit.logistics.prodcsvc.production.to.MpsResTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.nexacro17.xapi.data.PlatformData;

import kr.co.seoulit.logistics.busisvc.logisales.to.ContractDetailInMpsAvailableTO;
import kr.co.seoulit.logistics.prodcsvc.production.service.ProductionService;
import kr.co.seoulit.logistics.prodcsvc.production.to.SalesPlanInMpsAvailableTO;
import kr.co.seoulit.logistics.sys.util.DatasetBeanMapper;

@RestController
@RequestMapping("/production/*")
public class MpsController {

	@Autowired
	private ProductionService productionService;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	ModelMap map = null;

	private static Gson gson = new GsonBuilder().serializeNulls().create();

	/**
	 * NOTE: 주생산계획 조회
	 */
	@RequestMapping(value="/mps/Mainlist")
	public void searchMpsMainInfo(@RequestAttribute("reqData") PlatformData reqData,
								  @RequestAttribute("resData") PlatformData resData )throws Exception {

		MpsReqTO mpsReqTO = new MpsReqTO();
		mpsReqTO.setStartDate(reqData.getVariable("startDate").getString());
		mpsReqTO.setEndDate(reqData.getVariable("endDate").getString());
		mpsReqTO.setIncludeMrpApply(reqData.getVariable("includeMrpApply").getString());
		ArrayList<MpsResTO> mpsTOList = productionService.getMpsMainList(mpsReqTO);
		datasetBeanMapper.beansToDataset(resData, mpsTOList, MpsResTO.class);
	}
	/**
	 * NOTE: MRP에서 조회
	 */
	@RequestMapping(value="/mps/list")
	public void searchMpsInfo(@RequestAttribute("reqData") PlatformData reqData,
            					@RequestAttribute("resData") PlatformData resData )throws Exception {

		MpsReqTO mpsReqTO = new MpsReqTO();
		mpsReqTO.setStartDate(reqData.getVariable("startDate").getString());
		mpsReqTO.setEndDate(reqData.getVariable("endDate").getString());
		mpsReqTO.setIncludeMrpApply(reqData.getVariable("includeMrpApply").getString());
		ArrayList<MpsResTO> mpsTOList = productionService.getMpsList(mpsReqTO);
		datasetBeanMapper.beansToDataset(resData, mpsTOList, MpsResTO.class);
	}

	@RequestMapping(value = "/mps/contractdetail-available")
	public void searchContractDetailListInMpsAvailable(@RequestAttribute("reqData") PlatformData reqData,
			@RequestAttribute("resData") PlatformData resData) throws Exception {

		String searchCondition = reqData.getVariable("searchCondition").getString();
		String startDate = reqData.getVariable("startDate").getString();
		String endDate = reqData.getVariable("endDate").getString();

		ArrayList<ContractDetailInMpsAvailableResDto> contractDetailInMpsAvailableList = productionService
				.getContractDetailListInMpsAvailable(searchCondition, startDate, endDate);

		datasetBeanMapper.beansToDataset(resData, contractDetailInMpsAvailableList, ContractDetailInMpsAvailableResDto.class);
	}


	@RequestMapping(value="/mps/salesplan-available", method=RequestMethod.GET)
	public ModelMap searchSalesPlanListInMpsAvailable(HttpServletRequest request, HttpServletResponse response) {
		String searchCondition = request.getParameter("searchCondition");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		map = new ModelMap();
		try {
			ArrayList<SalesPlanInMpsAvailableTO> salesPlanInMpsAvailableList =
					productionService.getSalesPlanListInMpsAvailable(searchCondition, startDate, endDate);

			map.put("gridRowJson", salesPlanInMpsAvailableList);
			map.put("errorCode", 1);
			map.put("errorMsg", "성공");
		} catch (Exception e1) {
			e1.printStackTrace();
			map.put("errorCode", -1);
			map.put("errorMsg", e1.getMessage());
		}
		return map;
	}

	//mps등록
	@RequestMapping(value="mps/contractdetail")
	public void convertContractDetailToMps(@RequestAttribute("reqData") PlatformData reqData,
            @RequestAttribute("resData") PlatformData resData) throws Exception {

		ArrayList<ContractDetailInMpsAvailableTO> contractDetailInMpsAvailableList
		= (ArrayList<ContractDetailInMpsAvailableTO>)datasetBeanMapper.datasetToBeans(reqData, ContractDetailInMpsAvailableTO.class);

		List<MpsEntity> mpsNoList = productionService.convertContractDetailToMps(contractDetailInMpsAvailableList);
		datasetBeanMapper.beansToDataset(resData, mpsNoList, MpsEntity.class);
	}


	@RequestMapping(value="/mps/salesplan", method=RequestMethod.PUT)
	public ModelMap convertSalesPlanToMps(HttpServletRequest request, HttpServletResponse response) {
		String batchList = request.getParameter("batchList");
		map = new ModelMap();
		try {
			ArrayList<SalesPlanInMpsAvailableTO> salesPlanInMpsAvailableList = gson.fromJson(batchList,
					new TypeToken<ArrayList<SalesPlanInMpsAvailableTO>>() {
					}.getType());
			HashMap<String, Object> resultMap = productionService.convertSalesPlanToMps(salesPlanInMpsAvailableList);

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
	/**
	 * 이것은 주석이며 주석이고 주석이다.
	 * TODO: 주석은 주석이며 주석이면서 주석이다. 주석을 주석이라 부르지 못하면 어찌 주석이라 부르는가.
	 *  주석아 주석은 주석이라 주석이니라. 주석아 주석아.
	 *  다시 태어나도 주석으로 태어나 주석으로 살아가리.
	 *  모든 것은 주석에서 시작되어 주석으로 끝난다.
	 *  주석아 주석아 아리따운 주석아
	 *  내 소원을 들어주렴
	 *  주석을 주석처럼 쓸려면 주석을 사용해야 하는가 주석아.
	 *  내 다음 생은 주석이며, 주석은 내 다음 생이다.
	 *  주석같은 주석을 주석처럼 주석같이 사용하자.
	 */
}
