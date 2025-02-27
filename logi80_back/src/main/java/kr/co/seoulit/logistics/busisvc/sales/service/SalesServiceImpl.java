package kr.co.seoulit.logistics.busisvc.sales.service;

import kr.co.seoulit.logistics.busisvc.logisales.dto.ContractDetailResDto;
import kr.co.seoulit.logistics.busisvc.logisales.dto.ContractInfoResDto;
import kr.co.seoulit.logistics.busisvc.logisales.mapper.ContractMapper;
import kr.co.seoulit.logistics.busisvc.sales.dto.*;
import kr.co.seoulit.logistics.busisvc.sales.mapper.DeliveryMapper;
import kr.co.seoulit.logistics.busisvc.sales.mapper.ReleaseMapper;
import kr.co.seoulit.logistics.busisvc.sales.mapper.SalesPlanMapper;
import kr.co.seoulit.logistics.busisvc.sales.mapstruct.SalesPlanMapstruct;
import kr.co.seoulit.logistics.busisvc.sales.repository.ReleaseInfoRepository;
import kr.co.seoulit.logistics.busisvc.sales.repository.SalesPlanRepository;
import kr.co.seoulit.logistics.busisvc.sales.to.ReleaseInfoTO;
import kr.co.seoulit.logistics.busisvc.sales.to.SalesPlanEntity;
import kr.co.seoulit.logistics.busisvc.sales.to.SalesPlanTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
public class SalesServiceImpl implements SalesService {

    @Autowired
    private ContractMapper contractMapper;
    @Autowired
    private SalesPlanMapper salesPlanMapper;
    @Autowired
    private DeliveryMapper deliveryMapper;
    @Autowired
    private ReleaseMapper releaseMapper;
    @Autowired
    private ReleaseInfoRepository releaseInfoRepository;
    @Autowired
    private SalesPlanRepository salesPlanRepository; // Repository to handle DB operations
    @Autowired
    private SalesPlanMapstruct salesPlanMapstruct;


    @Override
    public ArrayList<ContractInfoResDto> getDeliverableContractList(HashMap<String, String> map) {

        ArrayList<ContractInfoResDto> contractInfoResDtoList = contractMapper.selectDeliverableContractList(map);
        for (ContractInfoResDto bean : contractInfoResDtoList) { // 해당 수주의 수주상세 리스트 세팅

            bean.setContractDetailResDtoList(contractMapper.selectDeliverableContractDetailList(bean.getContractNo()));

        }

        return contractInfoResDtoList;
    }


    @Override
    public ArrayList<SalesPlanEntity> getSalesPlanList(String startDate, String endDate) {

        ArrayList<SalesPlanEntity> salesPlanEntityList = null;

        HashMap<String, String> map = new HashMap<>();

        // map.put("dateSearchCondition", dateSearchCondition);
        map.put("startDate", startDate);
        map.put("endDate", endDate);

        salesPlanEntityList = salesPlanMapper.selectSalesPlanList(map);

        return salesPlanEntityList;
    }

    @Override
    public ModelMap batchSalesPlanListProcess(ArrayList<SalesPlanEntity> salesPlanEntityList) {

        ModelMap resultMap = new ModelMap();

        ArrayList<String> insertList = new ArrayList<>();
        ArrayList<String> updateList = new ArrayList<>();
        ArrayList<String> deleteList = new ArrayList<>();

        for (SalesPlanEntity bean : salesPlanEntityList) {

            String status = bean.getStatus();

            switch (status) {

                case "INSERT":

                    String newSalesPlanNo = getNewSalesPlanNo(bean.getSalesPlanDate());

                    bean.setSalesPlanNo(newSalesPlanNo);

                    salesPlanMapper.insertSalesPlan(bean);

                    insertList.add(newSalesPlanNo);

                    break;

                case "UPDATE":

                    salesPlanMapper.updateSalesPlan(bean);

                    updateList.add(bean.getSalesPlanNo());

                    break;

                case "DELETE":

                    salesPlanMapper.deleteSalesPlan(bean);

                    deleteList.add(bean.getSalesPlanNo());

                    break;

            }

        }

        resultMap.put("INSERT", insertList);
        resultMap.put("UPDATE", updateList);
        resultMap.put("DELETE", deleteList);

        return resultMap;
    }

    public String getNewSalesPlanNo(String salesPlanDate) {

        StringBuffer newEstimateNo = null;

        int newNo = salesPlanMapper.selectSalesPlanCount(salesPlanDate);

        newEstimateNo = new StringBuffer();

        newEstimateNo.append("SA");
        newEstimateNo.append(salesPlanDate.replace("-", ""));
        newEstimateNo.append(String.format("%02d", newNo)); // 2자리 숫자

        return newEstimateNo.toString();
    }

    @Override
//    @Cacheable("getDeliveryInfoList") // radis 설정
    public ArrayList<DeliveryInfoResDto> getDeliveryInfoList() {
        System.out.println("============최초실행");
        ArrayList<DeliveryInfoResDto> deliveryInfoResDtoList = deliveryMapper.selectDeliveryInfoList();
        System.out.println("deliveryInfoResDtoList = " + deliveryInfoResDtoList);
        return deliveryInfoResDtoList;
    }

    /*
    @Override
    public ModelMap batchDeliveryListProcess(List<DeliveryInfoEntity> deliveryTOList) {

        ModelMap resultMap = new ModelMap();

        ArrayList<String> insertList = new ArrayList<>();
        ArrayList<String> updateList = new ArrayList<>();
        ArrayList<String> deleteList = new ArrayList<>();

        for (DeliveryInfoEntity bean : deliveryTOList) {

            String status = bean.getStatus();

            switch (status.toUpperCase()) {

                case "INSERT":

                    String newDeliveryNo = "새로운";

                    bean.setDeliveryNo(newDeliveryNo);
                    deliveryMapper.insertDeliveryResult(bean);
                    insertList.add(newDeliveryNo);

                    break;

                case "UPDATE":

                    deliveryMapper.updateDeliveryResult(bean);

                    updateList.add(bean.getDeliveryNo());

                    break;

                case "DELETE":

                    deliveryMapper.deleteDeliveryResult(bean);

                    deleteList.add(bean.getDeliveryNo());

                    break;

            }

        }

        resultMap.put("INSERT", insertList);
        resultMap.put("UPDATE", updateList);
        resultMap.put("DELETE", deleteList);

        return resultMap;
    }
    */

    @Override
    public HashMap<String, Object> deliver(String contractDetailNo) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("contractDetailNo", contractDetailNo);

        deliveryMapper.deliver(map);

        HashMap<String, Object> resultMap = new ModelMap();
        resultMap.put("errorCode", map.get("ERROR_CODE"));
        resultMap.put("errorMsg", map.get("ERROR_MSG"));

        return resultMap;
    }

    @Override
    public ArrayList<ReverseResDto> getReturnAbleList(ReverseReqDto reverseReqDto) {

        ArrayList<ReverseResDto> ReturnAbleList = null;

        ReturnAbleList = deliveryMapper.selectReturnAbleList(reverseReqDto);

        return ReturnAbleList;
    }

    @Override
    public void insertReturnList(List<ReverseReqDto> reverseReqDtoList) {

        for (ReverseReqDto reverseReqDto : reverseReqDtoList) {
            if ("1".equals(reverseReqDto.getChecked())) {
                HashMap<String, String> map = new HashMap<>();
                map.put("deliveryNO", reverseReqDto.getDeliveryNO());
                map.put("itemCode", reverseReqDto.getItemCode());
                map.put("returnUnit", reverseReqDto.getReturnUnit());
                deliveryMapper.insertReturnList(map);
            }
        }
    }

    @Override
    public ArrayList<QuarterResDto> getSalesQuaChart() {

        return deliveryMapper.selectSalesQuaChart();
    }

    //출고가능 수주조회
    @Override
    public ArrayList<ContractInfoResDto> getReleaseContractList(HashMap<String, String> map) {
        // 로그 추가: 입력된 조건 확인
        System.out.println("입력된 조건을 확인할껀데 뭐가 나올라나?: " + map);

        ArrayList<ContractInfoResDto> contractInfoResDtoList = contractMapper.selectReleaseContractList(map);

        // 반환된 리스트에 대한 검증 및 로그 추가
        if (contractInfoResDtoList == null || contractInfoResDtoList.isEmpty()) {
            System.out.println("값이 없어!!! 정보도!!!.");
        } else {
            System.out.println("Contract information list size: " + contractInfoResDtoList.size());
            for (ContractInfoResDto contract : contractInfoResDtoList) {
                System.out.println("Contract No: " + contract.getContractNo());
            }
        }

        // 상세 리스트 조회 및 예외 처리 추가
        for (ContractInfoResDto bean : contractInfoResDtoList) {
            try {
                System.out.println("계약 번호에 대한 상세 정보 조회 시작: " + bean.getContractNo());
                ArrayList<ContractDetailResDto> detailList = contractMapper.selectReleaseContractDetailList(bean.getContractNo());

                if (detailList == null || detailList.isEmpty()) {
                    System.out.println("해당 계약 번호에 대한 수주 상세 정보가 없습니다: " + bean.getContractNo());
                } else {
                    System.out.println("계약 번호 " + bean.getContractNo() + "의 상세 정보 개수: " + detailList.size());
                }

                bean.setContractDetailResDtoList(detailList);
            } catch (Exception e) {
                System.err.println("계약 상세 조회 중 오류 발생: " + e.getMessage());
                // 예외가 발생해도 계속 처리할지, 중단할지 결정
            }
        }

        return contractInfoResDtoList;
    }



    //출고 등록
    @Override
    public HashMap<String, Object> releaseRegist(String contractDetailNo, String vehicleSelected) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("contractDetailNo", contractDetailNo);
        map.put("vehicleSelected", vehicleSelected);

        releaseMapper.releaseRegist(map);

        HashMap<String, Object> resultMap = new ModelMap();
        resultMap.put("errorCode", map.get("ERROR_CODE"));
        resultMap.put("errorMsg", map.get("ERROR_MSG"));

        return resultMap;
    }

    //출고 현황
    @Override
    public ArrayList<ReleaseInfoResDto> getReleaseInfoList() {

        ArrayList<ReleaseInfoResDto> releaseInfoResDtoList = releaseMapper.selectReleaseInfoList();

        return releaseInfoResDtoList;
    }

    //출고 수정 : 저장
    @Override
    public HashMap<String, Object> updateReleaseInfo(ArrayList<ReleaseInfoTO> releaseInfoList) {
        HashMap<String, Object> resultMap = new HashMap<>();
        ArrayList<String> updateList = new ArrayList<>();

        for (ReleaseInfoTO bean : releaseInfoList) {
            String status = bean.getStatus();
            switch (status) {
                case "update":
                    releaseMapper.updateReleaseInfo(bean);
                    updateList.add(bean.getReleaseNo());
                    break;
            }
        }
        resultMap.put("UPDATE", updateList);
        return resultMap;
    }
    @Override
    public void insertSalesPlan(SalesPlanEntity salesPlanEntity){
        System.out.println("실행 완료 되었습니다.");
        salesPlanRepository.save(salesPlanEntity);
    }
    // 민성 14 11 05 전체 코드 조회하는 거라 코드 변경이 필요함
//    @Override
//    public ArrayList<SalesPlanTO> selectSalesPlanList(){
//        // SalesPlan 레파지토리에서 조회
//        ArrayList<SalesPlanEntity> salesPlanList = salesPlanRepository.findAll();
//        ArrayList<SalesPlanTO> salesPlanTOList = new ArrayList<>();
//        for (SalesPlanEntity salesPlanEntity : salesPlanList) {
//            SalesPlanTO dto = salesPlanMapstruct.toDto(salesPlanEntity);
//            salesPlanTOList.add(dto);
//        }
//        return salesPlanTOList;
//    }

    // 민성 24 11 05 전체 코드 조회 대신 일부만 조회하는 코드로 변경
    @Override
    public SalesPlanTO selectLastSalesPlan() {
//         SalesPlan 레파지토리에서 마지막 일련번호만 조회
//        SalesPlanEntity lastSalesPlanEntity = salesPlanRepository.findTopByOrderBySalesPlanNoDesc();
//        if (lastSalesPlanEntity != null) {
//            return salesPlanMapstruct.toDto(lastSalesPlanEntity);
//        } else {
//            return null; // 데이터가 없을 경우 null 반환
//        }
        String lastSalesPlanNo = salesPlanRepository.findTopSalesPlanNo();
        if (lastSalesPlanNo != null) {
            // 문자열을 정수로 변환하여 1을 더합니다.
            int nextSalesPlanNo = Integer.parseInt(lastSalesPlanNo) + 1;

            // SalesPlanTO 객체 생성 및 필요한 데이터 설정
            SalesPlanTO salesPlanTO = new SalesPlanTO();
            salesPlanTO.setSalesPlanNo(String.valueOf(nextSalesPlanNo)); // 다시 문자열로 변환하여 설정
            // 나머지 필드는 빈 값으로 남겨두거나 추가 로직을 통해 설정할 수 있습니다.

            return salesPlanTO;
        } else {
            return null; // 데이터가 없을 경우 null 반환
        }
    }

    // 출고 삭제
    @Override
    public void deleteReleaseInfo(String releaseNo) {
        releaseMapper.deleteReleaseInfo(releaseNo);
    }
}

