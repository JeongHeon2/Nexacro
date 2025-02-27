package kr.co.seoulit.logistics.busisvc.logisales.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.seoulit.logistics.busisvc.logisales.dto.*;
import kr.co.seoulit.logistics.busisvc.logisales.entity.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContractMapper {

	public ArrayList<EstimateResDto> selectEstimateListInContractAvailable(EstimateReqDto estimateReqDto);

	public ArrayList<ContractInfoResDto> selectContractList(ContractInfoReqDto contractInfoReqDto);

	public ArrayList<ContractInfoResDto> selectDeliverableContractList(HashMap<String, String> ableSearchConditionInfo);

	public int selectContractCount(String contractDate);

	public void insertContract(ContractEntity TO);

	public void updateContract(ContractEntity TO);

	public void deleteContract(ContractEntity TO);


	//ContractDetail
	public ArrayList<ContractDetailEntity> selectContractDetailList(String contractNo);

	public ArrayList<ContractInfoDto> selectDeliverableContractListByCondition(HashMap<String,String> map);

	public ArrayList<ContractDetailResDto> selectDeliverableContractDetailList(String contractNo);

	public int selectContractDetailCount(String contractNo);

	public ArrayList<ContractDetailInMpsAvailableResDto> selectContractDetailListInMpsAvailable(
			HashMap<String, String> map);

	public void changeMpsStatusOfContractDetail(HashMap<String, String> map);

	public void deleteContractDetail(ContractDetailEntity TO);

	public void insertContractDetail(HashMap<String,Object>  workingContractList);

	//출고를 위한 수주현황
	public ArrayList<ContractInfoResDto> selectReleaseContractList(HashMap<String, String> ableSearchConditionInfo);

	//출고를 위한 수주상세
	public ArrayList<ContractDetailResDto> selectReleaseContractDetailList(String contractNo);
}
