package kr.co.seoulit.logistics.busisvc.logisales.controller;

import com.nexacro17.xapi.data.PlatformData;
import kr.co.seoulit.logistics.busisvc.logisales.dto.EstimateDetailReqDto;
import kr.co.seoulit.logistics.busisvc.logisales.dto.EstimateDetailResDto;
import kr.co.seoulit.logistics.busisvc.logisales.dto.EstimateReqDto;
import kr.co.seoulit.logistics.busisvc.logisales.dto.EstimateResDto;
import kr.co.seoulit.logistics.busisvc.logisales.entity.EstimateDetailEntity;
import kr.co.seoulit.logistics.busisvc.logisales.entity.EstimateEntity;
import kr.co.seoulit.logistics.busisvc.logisales.mapstruct.EstimateDetailResMapstruct;
import kr.co.seoulit.logistics.busisvc.logisales.mapstruct.EstimateReqMapstruct;
import kr.co.seoulit.logistics.busisvc.logisales.mapstruct.EstimateResMapstruct;
import kr.co.seoulit.logistics.busisvc.logisales.service.LogisalesService;
import kr.co.seoulit.logistics.sys.util.DatasetBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/logisales/*")
public class EstimateController {

    @Autowired
    private DatasetBeanMapper datasetBeanMapper;
    @Autowired
    private LogisalesService logisalesService;
    @Autowired
    private EstimateDetailResMapstruct estimateDetailResMapstruct;
    @Autowired
    private EstimateResMapstruct estimateResMapstruct;
    @Autowired
    private EstimateReqMapstruct estimateReqMapstruct;

    @Description(value = "견적조회/수정 : 견적조회")
    @RequestMapping(value = "/estimate/list")
    public void searchEstimateInfo(@RequestAttribute("reqData") PlatformData reqData,
                                   @RequestAttribute("resData") PlatformData resData) throws Exception {
//        MaBatis
        EstimateReqDto estimateReqDto = new EstimateReqDto();
        estimateReqDto.setDateSearchCondition(reqData.getVariable("dateSearchCondition").getString());
        estimateReqDto.setStartDate(reqData.getVariable("startDate").getString());
        estimateReqDto.setEndDate(reqData.getVariable("endDate").getString());

        ArrayList<EstimateResDto> estimateResDtoList = logisalesService.getEstimateList(estimateReqDto);
        ArrayList<EstimateDetailResDto> estimateDetailResDtoList = new ArrayList<>();

        // logisalesService.getEstimateList: 설정된 조회 조건을 바탕으로 견적 리스트를 가져옵니다

        for (EstimateResDto estimateResDto : estimateResDtoList) { // estimate 개수만큼 돌려서 //각 견적에 대해
            for (EstimateDetailResDto estimateDetailResDto : estimateResDto.getEstimateDetailResDtoList()) {
                estimateDetailResDtoList.add(estimateDetailResDto); // 빈 디테일리스트에 estimateResDto의 EstimateDetailList를 넣어줌 // 세부 정보를 리스트에 추가
            }
        }
        https://www.youtube.com/watch?v=n9lsZ3e_vGY
        datasetBeanMapper.beansToDataset(resData, estimateResDtoList, EstimateResDto.class);
        datasetBeanMapper.beansToDataset(resData, estimateDetailResDtoList, EstimateDetailResDto.class);

//        JPA
//        EstimateReqDto estimateReqDto = new EstimateReqDto();
//        estimateReqDto.setDateSearchCondition(reqData.getVariable("dateSearchCondition").getString());
//        estimateReqDto.setStartDate(reqData.getVariable("startDate").getString());
//        estimateReqDto.setEndDate(reqData.getVariable("endDate").getString());
//
//        ArrayList<EstimateEntity> estimateEntityList = logisalesService.getEstimateList(estimateReqDto);
//        ArrayList<EstimateDetailResDto> estimateDetailResDtoList = new ArrayList<>();
//
//        for (EstimateEntity estimateEntity : estimateEntityList) {
//            ArrayList<EstimateDetailResDto> estimateDetailResDtos =
//                    (ArrayList<EstimateDetailResDto>) estimateDetailResMapstruct.toDto(estimateEntity.getEstimateDetailEntityList());
//            estimateDetailResDtoList.addAll(estimateDetailResDtos);
//        }
//        ArrayList<EstimateResDto> estimateResDtoList = estimateResMapstruct.toDto(estimateEntityList);
//
//        datasetBeanMapper.beansToDataset(resData, estimateResDtoList, EstimateResDto.class);
//        datasetBeanMapper.beansToDataset(resData, estimateDetailResDtoList, EstimateDetailResDto.class);
    }

    @Description(value = "견적등록 : 일괄저장")
    @RequestMapping(value = "/estimate/new")
    public void addNewEstimate(@RequestAttribute("reqData") PlatformData reqData,
                               @RequestAttribute("resData") PlatformData resData ) throws Exception {

        EstimateReqDto newEstimateReqDto = datasetBeanMapper.datasetToBean(reqData, EstimateReqDto.class);
        List<EstimateDetailReqDto> newEstimateDetailReqDto = datasetBeanMapper.datasetToBeans(reqData, EstimateDetailReqDto.class);

        newEstimateReqDto.setEstimateDetailReqDtoList(newEstimateDetailReqDto);
        HashMap<String, Object> resultList = logisalesService.addNewEstimate(newEstimateReqDto);

        EstimateResDto estimateResDto = new EstimateResDto();
        estimateResDto.setEstimateNo(resultList.get("newEstimateNo").toString());

        resData.getVariableList().add("EstimateDtNo", resultList.get("newEstimateDetailNo").toString());
        datasetBeanMapper.beanToDataset(resData, estimateResDto, EstimateResDto.class);

        /*
        EstimateEntity newEstimateEntity = datasetBeanMapper.datasetToBean(reqData, EstimateEntity.class);
        List<EstimateDetailEntity> newEstimateDeatailEntity = datasetBeanMapper.datasetToBeans(reqData, EstimateDetailEntity.class);
        String estimateDate = newEstimateEntity.getEstimateDate();

        newEstimateEntity.setEstimateDetailEntityList(newEstimateDeatailEntity);
        // HashMap<String, Object> resultList =
        logisalesService.addNewEstimate(estimateDate, newEstimateEntity);

        // EstimateTO estimateTO = new EstimateTO();
        // estimateTO.setEstimateNo(resultList.get("newEstimateNo").toString());

        // resData.getVariableList().add("EstimateDtNo", resultList.get("INSERT").toString());
        // datasetBeanMapper.beanToDataset(resData, estimateTO, EstimateTO.class);
        */
    }

    @Description(value = "견적조회/수정 : 일괄저장")
    @RequestMapping(value = "/estimatedetail/batch")
    public void batchListProcess(@RequestAttribute("reqData") PlatformData reqData) throws Exception {

        ArrayList<EstimateDetailReqDto> estimateDetailReqDtos =
                (ArrayList<EstimateDetailReqDto>) datasetBeanMapper.datasetToBeans(reqData, EstimateDetailReqDto.class);

        logisalesService.batchEstimateDetailListProcess(estimateDetailReqDtos);
    }
}
