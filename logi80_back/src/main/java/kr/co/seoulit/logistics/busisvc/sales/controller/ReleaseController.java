package kr.co.seoulit.logistics.busisvc.sales.controller;

import com.nexacro17.xapi.data.DataSet;
import com.nexacro17.xapi.data.PlatformData;
import kr.co.seoulit.logistics.busisvc.logisales.dto.ContractDetailResDto;
import kr.co.seoulit.logistics.busisvc.logisales.dto.ContractInfoResDto;
import kr.co.seoulit.logistics.busisvc.sales.dto.*;
import kr.co.seoulit.logistics.busisvc.sales.service.SalesService;
import kr.co.seoulit.logistics.busisvc.sales.to.ReleaseInfoTO;
import kr.co.seoulit.logistics.sys.util.DatasetBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/sales/*")
public class ReleaseController {

    @Autowired
    private SalesService salesService;
    @Autowired
    private DatasetBeanMapper datasetBeanMapper;

//    ModelMap map = null;
//    private static Gson gson = new GsonBuilder().serializeNulls().create();

    @Description(value = "출고가능수주조회")
    @RequestMapping(value = "/release/list/contractAvailable")
    public void searchReleaseContractList(@RequestAttribute("reqData") PlatformData reqData,
                                          @RequestAttribute("resData") PlatformData resData) throws Exception {
        System.out.println("나 데이터 잘 받았다!!!");
        HashMap<String, String> map = new HashMap<>();

        // 변수의 null 체크 추가
        String searchCondition = (reqData.getVariable("searchCondition") != null)
                ? reqData.getVariable("searchCondition").getString()
                : "";
        String startDate = (reqData.getVariable("startDate") != null)
                ? reqData.getVariable("startDate").getString()
                : "";
        String endDate = (reqData.getVariable("endDate") != null)
                ? reqData.getVariable("endDate").getString()
                : "";
        String customerCode = (reqData.getVariable("customerCode") != null)
                ? reqData.getVariable("customerCode").getString()
                : "";

        map.put("searchCondition", searchCondition);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("customerCode", customerCode);

        System.out.println("어떤 값이 담겼는지 궁금해"+map);

        // 서비스 호출
        ArrayList<ContractInfoResDto> releaseInfoResDtoList = salesService.getReleaseContractList(map);

        // 계약 상세 정보 리스트 생성
        ArrayList<ContractDetailResDto> releaseContractDetailList = new ArrayList<>();
        for (ContractInfoResDto contract : releaseInfoResDtoList) {
            for (ContractDetailResDto contractDetailResDto : contract.getContractDetailResDtoList()) {
                releaseContractDetailList.add(contractDetailResDto);
            }
        }

        // 데이터셋 초기화 및 추가 로직 개선
        if (resData.getDataSet("releaseContractDetailList") == null) {
            resData.addDataSet(new DataSet("releaseContractDetailList"));
        } else {
            resData.getDataSet("releaseContractDetailList").clearData();
        }

        if (resData.getDataSet("releaseInfoResDtoList") == null) {
            resData.addDataSet(new DataSet("releaseInfoResDtoList"));
        } else {
            resData.getDataSet("releaseInfoResDtoList").clearData();
        }

        // 데이터셋 매핑
        datasetBeanMapper.beansToDataset(resData, releaseInfoResDtoList, ContractInfoResDto.class);

        try {
            datasetBeanMapper.beansToDataset(resData, releaseContractDetailList, ContractDetailResDto.class);
        } catch (IllegalStateException e) {
            System.err.println("Dataset에 중복된 컬럼이 추가되었습니다: " + e.getMessage());
            throw e; // 예외를 다시 던지거나 적절히 처리
        }
    }



    @Description(value = "출고등록")
    @RequestMapping(value = "/releaseRegist")
    public void releaseRegist(@RequestAttribute("reqData") PlatformData reqData,
                             @RequestAttribute("resData") PlatformData resData) throws Exception {

        String contractDetailNo = reqData.getVariable("contractDetailNo").getString();
        String vehicleSelected = reqData.getVariable("vehicleSelected").getString();



        System.out.println(contractDetailNo); // 수주 상세 일련번호
        System.out.println(vehicleSelected); // 운송 수단


//        ArrayList<OutputInfoReqDto> outputInfoReqDtoList = salesService.insertOutputInfoList();
//        datasetBeanMapper.datasetToBean(reqData, outputInfoReqDtoList, OutputInfoReqDto.class);
        HashMap<String, Object> resultMap = salesService.releaseRegist(contractDetailNo, vehicleSelected);
        resData.getVariableList().add("g_procedureMsg", resultMap.get("errorMsg"));
    }

    @Description(value = "출고현황조회")
    @RequestMapping(value = "/releaseRegist/list")
    public void searchReleaseInfoList(@RequestAttribute("reqData") PlatformData reqData,
                                      @RequestAttribute("resData") PlatformData resData) throws Exception {

       ArrayList<ReleaseInfoResDto> releaseInfoResDtoList = salesService.getReleaseInfoList();
        datasetBeanMapper.beansToDataset(resData, releaseInfoResDtoList, ReleaseInfoResDto.class);
    }

    @Description(value = "출고현황수정 : 저장")
    @RequestMapping(value = "/update/releaseInfo")
    public void updateReleaseInfo(@RequestAttribute("reqData") PlatformData reqData,
                                  @RequestAttribute("resData") PlatformData resData) throws Exception {

        String releaseNo = reqData.getVariableList().getString("releaseNo");

        ArrayList<ReleaseInfoTO> releaseInfoList = (ArrayList<ReleaseInfoTO>) datasetBeanMapper.datasetToBeans(reqData, ReleaseInfoTO.class);
            salesService.updateReleaseInfo(releaseInfoList);
    }

    @Description(value = "출고현황삭제")
    @RequestMapping(value = "/delete/releaseInfo")
    public void deleteReleaseInfo(@RequestAttribute("reqData") PlatformData reqData,
                                  @RequestAttribute("resData") PlatformData resData) throws Exception {


        String releaseNo = reqData.getVariable("releaseNo").getString();

        System.out.println("releaseNo = " + releaseNo);

        salesService.deleteReleaseInfo(releaseNo);
    }
}
