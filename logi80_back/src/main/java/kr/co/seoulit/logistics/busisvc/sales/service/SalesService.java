package kr.co.seoulit.logistics.busisvc.sales.service;

import kr.co.seoulit.logistics.busisvc.logisales.dto.ContractInfoResDto;
import kr.co.seoulit.logistics.busisvc.logisales.dto.ContractReqDto;
import kr.co.seoulit.logistics.busisvc.sales.dto.*;
import kr.co.seoulit.logistics.busisvc.sales.to.ReleaseInfoTO;
import kr.co.seoulit.logistics.busisvc.sales.to.SalesPlanEntity;
import kr.co.seoulit.logistics.busisvc.sales.to.SalesPlanTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface SalesService {

    // SalesPlanApplicationServiceImpl
    public ArrayList<ContractInfoResDto> getDeliverableContractList(HashMap<String, String> ableSearchConditionInfo);

    public ArrayList<SalesPlanEntity> getSalesPlanList(String startDate, String endDate);

    public HashMap<String, Object> batchSalesPlanListProcess(ArrayList<SalesPlanEntity> salesPlanEntityList);

//    public HashMap<String, Object> batchDeliveryListProcess(List<DeliveryInfoEntity> deliveryTOList);

    public HashMap<String, Object> deliver(String contractDetailNo);

    public ArrayList<DeliveryInfoResDto> getDeliveryInfoList();

    public ArrayList<ReverseResDto> getReturnAbleList(ReverseReqDto reversReqDto);

    public void insertReturnList(List<ReverseReqDto> reverseReqList);

    public ArrayList<QuarterResDto> getSalesQuaChart();

    //출고가능 수주조회
    public ArrayList<ContractInfoResDto> getReleaseContractList(HashMap<String, String> ableSearchConditionInfo);

    //출고 등록
    public HashMap<String, Object> releaseRegist(String contractDetailNo, String vehicleSelected);

    //출고현황 조회
    public ArrayList<ReleaseInfoResDto> getReleaseInfoList();

    //출고현황 수정
    public HashMap<String, Object> updateReleaseInfo(ArrayList<ReleaseInfoTO> releaseInfoList);

    // 판매계획 추가
    public void insertSalesPlan(SalesPlanEntity salesPlanEntity);

    // 판매 계획 조회
    // public ArrayList<SalesPlanTO> selectSalesPlanList();

    public SalesPlanTO selectLastSalesPlan();

    //출고현황 삭제
    public void deleteReleaseInfo(String releaseNo);
}
