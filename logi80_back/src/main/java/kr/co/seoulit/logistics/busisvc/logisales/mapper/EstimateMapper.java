package kr.co.seoulit.logistics.busisvc.logisales.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.seoulit.logistics.busisvc.logisales.dto.EstimateDetailReqDto;
import kr.co.seoulit.logistics.busisvc.logisales.dto.EstimateDetailResDto;
import kr.co.seoulit.logistics.busisvc.logisales.dto.EstimateReqDto;
import kr.co.seoulit.logistics.busisvc.logisales.dto.EstimateResDto;
import kr.co.seoulit.logistics.busisvc.logisales.entity.EstimateEntity;
import kr.co.seoulit.logistics.busisvc.logisales.entity.EstimateDetailEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EstimateMapper {
	public ArrayList<EstimateResDto> selectEstimateList(EstimateReqDto estimateReqDto);
	
	public EstimateResDto selectEstimate(String estimateNo);

	public int selectEstimateCount(String estimateDate);

	public void insertEstimate(EstimateReqDto estimateReqDto);

	public void updateEstimate(EstimateEntity TO);
	
	public void deleteEstimate(String estimateNo);

	public void changeContractStatusOfEstimate(HashMap<String, String> map);
	
	//EstimateDetail

	public ArrayList<EstimateDetailResDto> selectEstimateDetailList(String estimateNo);

	public ArrayList<EstimateDetailResDto> selectEstimateDetailCount(String estimateNo);

	public void insertEstimateDetail(EstimateDetailReqDto estimateDetailReqDto);

	public void updateEstimateDetail(EstimateDetailEntity TO);

	public void deleteEstimateDetail(EstimateDetailEntity TO);

	public int selectEstimateDetailSeq(String estimateDate);
	
	public void initDetailSeq();

	public void reArrangeEstimateDetail(HashMap<String, String> map);

}
