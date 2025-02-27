package kr.co.seoulit.logistics.busisvc.logisales.controller;

import com.nexacro17.xapi.data.PlatformData;
import kr.co.seoulit.logistics.busisvc.logisales.dto.*;
import kr.co.seoulit.logistics.busisvc.logisales.entity.*;
import kr.co.seoulit.logistics.busisvc.logisales.mapstruct.EstimateReqMapstruct;
import kr.co.seoulit.logistics.busisvc.logisales.mapstruct.EstimateResMapstruct;
import kr.co.seoulit.logistics.busisvc.logisales.service.LogisalesService;
import kr.co.seoulit.logistics.sys.util.DatasetBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/logisales/*")
public class ContractController {

	@Autowired
	private LogisalesService logisalesService;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	@Autowired
	private EstimateReqMapstruct estimateReqMapstruct;
	@Autowired
	private EstimateResMapstruct estimateResMapstruct;

	public ContractController() {
	}

	// 수주조회
	@Description(value = "수주조회")
	@RequestMapping(value = "/contract/list")
	public void searchContract(@RequestAttribute("reqData") PlatformData reqData,
							   @RequestAttribute("resData") PlatformData resData) throws Exception {

		ContractInfoReqDto contractInfoReqDto = new ContractInfoReqDto();
		contractInfoReqDto.setSearchCondition(reqData.getVariableList().getString("searchCondition"));
		contractInfoReqDto.setStartDate(reqData.getVariableList().getString("firstDate"));
		contractInfoReqDto.setEndDate(reqData.getVariableList().getString("endDate"));
		contractInfoReqDto.setCustomerCode(reqData.getVariableList().getString("customerCode"));

		ArrayList<ContractInfoResDto> contractInfoResDtoList = logisalesService.getContractList(contractInfoReqDto);

		List<ContractDetailResDto> contractDetailResDtoList = new ArrayList<>();

		for (ContractInfoResDto contractInfoResDto : contractInfoResDtoList) {
			for (ContractDetailResDto contractDetailResDto : contractInfoResDto.getContractDetailResDtoList()) {
				contractDetailResDtoList.add(contractDetailResDto);
			}
		}

		datasetBeanMapper.beansToDataset(resData, contractInfoResDtoList, ContractInfoResDto.class);
		datasetBeanMapper.beansToDataset(resData, contractDetailResDtoList, ContractDetailResDto.class);

	}

	@Description(value = "수주가능한 견적조회")
	@RequestMapping(value = "/estimate/list/contractavailable")
	public void searchEstimateInContractAvailable(@RequestAttribute("reqData") PlatformData reqData,
												  @RequestAttribute("resData") PlatformData resData) throws Exception {

		EstimateReqDto estimateReqDto = new EstimateReqDto();
		estimateReqDto.setStartDate(reqData.getVariable("startDate").getString());
		estimateReqDto.setEndDate(reqData.getVariable("endDate").getString());

		ArrayList<EstimateResDto> estimateListInContractAvailable = logisalesService.getEstimateListInContractAvailable(estimateReqDto);

		List<EstimateDetailResDto> estimateDetailList = new ArrayList<>();

		for (EstimateResDto estimateResDto : estimateListInContractAvailable) {
			for (EstimateDetailResDto estimateDetailResDto : estimateResDto.getEstimateDetailResDtoList()) {
				estimateDetailList.add(estimateDetailResDto);
			}
		}

		datasetBeanMapper.beansToDataset(resData, estimateListInContractAvailable, EstimateResDto.class);
		datasetBeanMapper.beansToDataset(resData, estimateDetailList, EstimateDetailResDto.class);

	}

	@Description(value = "수주등록")
	@RequestMapping(value = "/contract/new")
	public void addNewContract(@RequestAttribute("reqData") PlatformData reqData,
							   @RequestAttribute("resData") PlatformData resData) throws Exception {

		ContractReqDto contractReqDto = datasetBeanMapper.datasetToBean(reqData, ContractReqDto.class);
		System.out.println("contractReqDto1 = " + contractReqDto);
		contractReqDto.setContractDate(reqData.getVariable("contractDate").getString());
		contractReqDto.setPersonCodeInCharge(reqData.getVariable("personCodeInCharge").getString());
		List<ContractDetailReqDto> contractDetailReqDtoList = datasetBeanMapper.datasetToBeans(reqData, ContractDetailReqDto.class);
		contractReqDto.setContractDetailReqDtoList(contractDetailReqDtoList);
		System.out.println("contractDetailReqDtoList = " + contractDetailReqDtoList);

		HashMap<String, Object> map = logisalesService.addNewContract(contractReqDto);

		resData.getVariableList().add("g_procedureCode", map.get("errorCode"));
		resData.getVariableList().add("g_procedureMsg", map.get("errorMsg"));
	}

	@Description(value = "견적삭제")
	@RequestMapping(value = "/estimate/list/cancelEstimate")
	public void deleteEstimate(@RequestAttribute("reqData") PlatformData reqData,
							   @RequestAttribute("resData") PlatformData resData) throws Exception {

		EstimateReqDto estimateReqDto = new EstimateReqDto();
		List<EstimateDetailReqDto> estimateDetailReqDtoList = datasetBeanMapper.datasetToBeans(reqData, EstimateDetailReqDto.class);
		estimateReqDto.setEstimateNo(reqData.getVariableList().getString("estimateNo"));
		estimateReqDto.setEstimateDetailReqDtoList(estimateDetailReqDtoList);
		logisalesService.cancelEstimate(estimateReqDto);
	}
}
