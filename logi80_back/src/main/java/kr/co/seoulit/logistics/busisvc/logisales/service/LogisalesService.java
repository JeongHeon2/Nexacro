package kr.co.seoulit.logistics.busisvc.logisales.service;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.seoulit.logistics.busisvc.logisales.dto.*;
import kr.co.seoulit.logistics.busisvc.logisales.entity.*;

public interface LogisalesService {

	
	// EstimateApplicationServiceImpl
	public ArrayList<EstimateResDto> getEstimateList(EstimateReqDto estimateReqDto);

	public ArrayList<EstimateDetailResDto> getEstimateDetailList(EstimateReqDto estimateReqDto);
	
	public HashMap<String, Object> addNewEstimate(EstimateReqDto estimateReqDto);
	
	public HashMap<String, Object> removeEstimate(String estimateNo, String status);

	public void batchEstimateDetailListProcess(ArrayList<EstimateDetailReqDto> estimateDetailReqDtos);
	
	
	// ContractApplicationServiceImpl
//	public ArrayList<ContractInfoResDto> getContractList(ContractInfoReqDto contractInfoReqDto);
	public ArrayList<ContractInfoResDto> getContractList(ContractInfoReqDto contractInfoReqDto);

	public ArrayList<ContractDetailEntity> getContractDetailList(String estimateNo);
	
	public ArrayList<EstimateResDto> getEstimateListInContractAvailable(EstimateReqDto estimateReqDto);

	public HashMap<String, Object> addNewContract(ContractReqDto contractReqDto);

	public HashMap<String, Object> batchContractDetailListProcess(ArrayList<ContractDetailEntity> contractDetailEntityList);
	
	public void changeContractStatusInEstimate(String estimateNo , String contractStatus);

	public void cancelEstimate(EstimateReqDto estimateReqDto);
	
}
