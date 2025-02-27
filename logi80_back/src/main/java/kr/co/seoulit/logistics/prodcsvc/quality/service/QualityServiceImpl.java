package kr.co.seoulit.logistics.prodcsvc.quality.service;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.seoulit.logistics.prodcsvc.quality.Entity.WorkOrderInfoEntity;
import kr.co.seoulit.logistics.prodcsvc.quality.mapstruct.WorkOrderInfoResMapstruct;
import kr.co.seoulit.logistics.prodcsvc.quality.repository.WorkOrderInfoRepository;
import kr.co.seoulit.logistics.prodcsvc.quality.to.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import kr.co.seoulit.logistics.prodcsvc.quality.mapper.WorkOrderMapper;

@Service
@AllArgsConstructor
@Slf4j
public class QualityServiceImpl implements QualityService {

	private WorkOrderMapper workOrderMapper;
	private final WorkOrderInfoRepository workOrderInfoRepository;
	private final WorkOrderInfoResMapstruct workOrderInfoResMapstruct;

	@Override
	public HashMap<String, Object> getWorkOrderableMrpList() {
		
		HashMap<String, Object> resultMap = new HashMap<>();
		HashMap<String, Object> map = new HashMap<>();
		
		workOrderMapper.getWorkOrderableMrpList(map);
		System.out.println("서비스 테스트"+map.get("RESULT"));
		resultMap.put("gridRowJson", map.get("RESULT"));
        resultMap.put("errorCode",map.get("ERROR_CODE"));
        resultMap.put("errorMsg", map.get("ERROR_MSG"));
		return resultMap;
		
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<WorkOrderSimulationTO> getWorkOrderSimulationList(String mrpNo ,String mrpGatheringNo) {
		
		String mrpNoList = mrpNo.toString().replace("[", "").replace("]", "");
		String mrpGatheringNoList = mrpGatheringNo.toString().replace("[", "").replace("]", "");
		
		// jpa 미구현 - procedure 호출
		HashMap<String, Object> map = new HashMap<>();
		
		map.put("mrpGatheringNoList",mrpGatheringNoList);
		map.put("mrpNoList", mrpNoList);
        
        
		workOrderMapper.getWorkOrderSimulationList(map);

		return (ArrayList<WorkOrderSimulationTO>)map.get("RESULT");
	}
	
	
	@Override
	public void workOrder(String mrpGatheringNo,String workPlaceCode,String productionProcess,String mrpNo) {
		
		mrpGatheringNo=mrpGatheringNo.replace("[", "").replace("]", "").replace("{", "").replace("}", "").replace("\"", "");
		mrpNo=mrpNo.replace("[", "").replace("]", "").replace("{", "").replace("}", "").replace("\"", "");

		ModelMap resultMap = new ModelMap();
		
		HashMap<String, String> map = new HashMap<>();

		map.put("mrpGatheringNo", mrpGatheringNo);
		map.put("workPlaceCode", workPlaceCode);
		map.put("productionProcess", productionProcess);
		map.put("mrpNo", mrpNo);
        
		workOrderMapper.workOrder(map);
		
		resultMap.put("errorCode", map.get("ERROR_CODE"));
		resultMap.put("errorMsg", map.get("ERROR_MSG"));

	}

	/**
	 * TODO: 작업장조회수정
	 *  return workOrderMapper.selectWorkOrderInfoList();
	 *  -> 	return workOrderInfoResMapstruct.toDto(workOrderInfoRepository.findByOperationCompletedIsNull());
	 */
	@Override
	public ArrayList<WorkOrderInfoResTO> getWorkOrderInfoList() {
		return workOrderInfoResMapstruct.toDto(workOrderInfoRepository.findByOperationCompletedIsNull());
//		return workOrderMapper.selectWorkOrderInfoList();

	}

	@Override
	public HashMap<String, Object> workOrderCompletion(String workOrderNo, String actualCompletionAmount) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("workOrderNo", workOrderNo);
		map.put("actualCompletionAmount", actualCompletionAmount);

		System.out.println("매퍼 실행 전 데이터: " + map); // 매퍼 호출 전 로그 추가
		workOrderMapper.workOrderCompletion(map);
		System.out.println("매퍼 실행 후 반환된 데이터: " + map); // 매퍼 호출 후 로그 추가

		return map;
	}


	@Override
	public ArrayList<ProductionPerformanceInfoTO> getProductionPerformanceInfoList() {

		ArrayList<ProductionPerformanceInfoTO> productionPerformanceInfoList = null;

		productionPerformanceInfoList = workOrderMapper.selectProductionPerformanceInfoList();

		return productionPerformanceInfoList;

	}

	@Override
	public HashMap<String, Object> showWorkSiteSituation(String workSiteCourse,String workOrderNo,String itemClassIfication) {

		HashMap<String,Object> map = new HashMap<String, Object>();
		
		HashMap<String, Object> resultMap = new ModelMap();
		
		map.put("workOrderNo", workOrderNo);
		map.put("workSiteCourse", workSiteCourse);
		map.put("itemClassIfication", itemClassIfication);
		
		workOrderMapper.selectWorkSiteSituation(map);

		resultMap.put("gridRowJson", map.get("RESULT"));
		resultMap.put("errorCode", map.get("ERROR_CODE"));
		resultMap.put("errorMsg", map.get("ERROR_MSG"));
		
		return resultMap;
	}

	@Override
	public void workCompletion(String workOrderNo, String itemCode ,  ArrayList<String> itemCodeListArr) {

		String itemCodeList=itemCodeListArr.toString().replace("[", "").replace("]", "");
		
		HashMap<String, String> map = new HashMap<>();

		map.put("workOrderNo", workOrderNo);
		map.put("itemCode", itemCode);
		map.put("itemCodeList", itemCodeList);

		workOrderMapper.updateWorkCompletionStatus(map);

	}

	@Override
	public HashMap<String, Object> workSiteLogList(String workSiteLogDate) {
		
		ArrayList<WorkSiteLog> list = workOrderMapper.workSiteLogList(workSiteLogDate);
		HashMap<String, Object> resultMap = new HashMap<String, Object>();		
		resultMap.put("gridRowJson",list);
		return resultMap;
		
	}

}
