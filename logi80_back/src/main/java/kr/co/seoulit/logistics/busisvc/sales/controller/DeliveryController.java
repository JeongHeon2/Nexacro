package kr.co.seoulit.logistics.busisvc.sales.controller;

import com.nexacro17.xapi.data.PlatformData;
import kr.co.seoulit.logistics.busisvc.logisales.dto.ContractDetailResDto;
import kr.co.seoulit.logistics.busisvc.logisales.dto.ContractInfoResDto;
import kr.co.seoulit.logistics.busisvc.sales.dto.*;
import kr.co.seoulit.logistics.busisvc.sales.service.SalesService;
import kr.co.seoulit.logistics.busisvc.sales.to.DeliveryInfoEntity;
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
@RequestMapping("/sales/*")
public class DeliveryController {

    @Autowired
    private SalesService salesService;
    @Autowired
    private DatasetBeanMapper datasetBeanMapper;

//    ModelMap map = null;
//    private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 변환

    @Description(value = "납품가능수주조회")
    @RequestMapping(value = "/deliver/list/contractavailable")
    public void searchDeliverableContractList(@RequestAttribute("reqData") PlatformData reqData,
                                              @RequestAttribute("resData") PlatformData resData) throws Exception {

        String a = reqData.getVariable("searchCondition").getString();
        String b = reqData.getVariable("customerCode").getString();

        // 넥사크로 버튼의 조건에 따라 거래처 검색 버튼을 클릭하면 searchByCustomer, 기간 검색 버튼을 클릭하면 searchByDate

        // 거래처 검색 = searchByCustomer
        // 기간 검색 = searchByDate

        System.out.println("\n"+ a +"\n");
        System.out.println("\n"+ b +"\n");

        HashMap<String, String> map = new HashMap<>();
        map.put("searchCondition", reqData.getVariable("searchCondition").getString());
        map.put("startDate", reqData.getVariable("startDate").getString());
        map.put("endDate", reqData.getVariable("endDate").getString());
        map.put("customerCode", reqData.getVariable("customerCode").getString());
        // 넥사크로 g_customerCode 값이 넥사크로에 정의 된다 DTN-01



        ArrayList<ContractInfoResDto> deliveryInfoResDtoList = salesService.getDeliverableContractList(map);

        ArrayList<ContractDetailResDto> deliverableContractDetailList = new ArrayList<>();
        for (ContractInfoResDto contract : deliveryInfoResDtoList) {
            for (ContractDetailResDto contractDetailResDto : contract.getContractDetailResDtoList()) {
                deliverableContractDetailList.add(contractDetailResDto);
            }
        }

        datasetBeanMapper.beansToDataset(resData, deliveryInfoResDtoList, ContractInfoResDto.class);
        datasetBeanMapper.beansToDataset(resData, deliverableContractDetailList, ContractDetailResDto.class);
    }

    @Description(value = "납품")
    @RequestMapping(value = "/deliver")
    public void deliver(@RequestAttribute("reqData") PlatformData reqData,
                        @RequestAttribute("resData") PlatformData resData) throws Exception {

        String contractDetailNo = reqData.getVariable("contractDetailNo").getString();
        HashMap<String, Object> resultMap = salesService.deliver(contractDetailNo);
        resData.getVariableList().add("g_procedureMsg", resultMap.get("errorMsg"));
    }

    @Description(value = "납품현황조회")
    @RequestMapping(value = "/delivery/list")
    public void searchDeliveryInfoList(@RequestAttribute("resData") PlatformData resData,
                                       @RequestAttribute("reqData") PlatformData reqData) throws Exception {

        ArrayList<DeliveryInfoResDto> deliveryInfoResDtoList = salesService.getDeliveryInfoList();
        System.out.println("deliveryInfoResDtoList = " + deliveryInfoResDtoList);
        datasetBeanMapper.beansToDataset(resData, deliveryInfoResDtoList, DeliveryInfoResDto.class);

    }

    @Description(value = "반품가능한 목록조회")
    @RequestMapping(value = "/ReturnAbleList")
    public void getReturnAbleList(@RequestAttribute("reqData") PlatformData reqData,
                                  @RequestAttribute("resData") PlatformData resData) throws Exception {
        ReverseReqDto reverseReqDto = new ReverseReqDto();
        reverseReqDto.setStartDate(reqData.getVariable("startDate").getString());
        reverseReqDto.setEndDate(reqData.getVariable("endDate").getString());
        ArrayList<ReverseResDto> returnList = salesService.getReturnAbleList(reverseReqDto);
        datasetBeanMapper.beansToDataset(resData, returnList, ReverseResDto.class);
    }

    @Description(value = "반품")
    @RequestMapping(value = "/ReturnRegister")
    public void registerReturn(@RequestAttribute("reqData") PlatformData reqData,
                               @RequestAttribute("resData") PlatformData resData) throws Exception {

        List<ReverseReqDto> reverseDtoReqList = datasetBeanMapper.datasetToBeans(reqData, ReverseReqDto.class);
        salesService.insertReturnList(reverseDtoReqList);
    }

    @Description(value = "???")
    @RequestMapping(value = "/totalQuaChart")
    public void getSalesQuaChart(@RequestAttribute("reqData") PlatformData reqData,
                                 @RequestAttribute("resData") PlatformData resData) throws Exception {
        ArrayList<QuarterResDto> salesQuaResDtoList = salesService.getSalesQuaChart();
        datasetBeanMapper.beansToDataset(resData, salesQuaResDtoList, QuarterResDto.class);
    }

    /*
    @RequestMapping(value = "/delivery/batch", method = RequestMethod.POST)
    public ModelMap deliveryBatchListProcess(HttpServletRequest request, HttpServletResponse response) {
        String batchList = request.getParameter("batchList");
        map = new ModelMap();
        try {
            List<DeliveryInfoEntity> deliveryTOList = gson.fromJson(batchList, new TypeToken<ArrayList<DeliveryInfoEntity>>() {
            }.getType());
            HashMap<String, Object> resultMap = salesService.batchDeliveryListProcess(deliveryTOList);

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
    */
}
