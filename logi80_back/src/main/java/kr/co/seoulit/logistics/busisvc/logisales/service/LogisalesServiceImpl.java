package kr.co.seoulit.logistics.busisvc.logisales.service;

import kr.co.seoulit.logistics.busisvc.logisales.dto.*;
import kr.co.seoulit.logistics.busisvc.logisales.entity.EstimateDetailEntity;
import kr.co.seoulit.logistics.busisvc.logisales.entity.EstimateEntity;
import kr.co.seoulit.logistics.busisvc.logisales.mapper.ContractMapper;
import kr.co.seoulit.logistics.busisvc.logisales.mapper.EstimateMapper;
import kr.co.seoulit.logistics.busisvc.logisales.mapstruct.*;
import kr.co.seoulit.logistics.busisvc.logisales.repository.ContractRepository;
import kr.co.seoulit.logistics.busisvc.logisales.repository.EstimateDetailRepository;
import kr.co.seoulit.logistics.busisvc.logisales.repository.EstimateRepository;
import kr.co.seoulit.logistics.busisvc.logisales.entity.ContractDetailEntity;
import kr.co.seoulit.logistics.busisvc.logisales.entity.ContractEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class LogisalesServiceImpl implements LogisalesService {
	
	private final ContractMapper contractMapper;
	private final EstimateMapper estimateMapper;
	private final EstimateRepository estimateRepository;
	private final EstimateDetailRepository estimateDetailRepository;
	private final ContractRepository contractRepository;
	private final EstimateReqMapstruct estimateEntityList;
	private final EstimateReqMapstruct estimateReqMapstruct;
	private final EstimateResMapstruct estimateResMapstruct;
	private final EstimateDetailReqMapstruct estimateDetailReqMapstruct;
	private final EstimateDetailResMapstruct estimateDetailResMapstruct;
	private final ContractInfoReqMapstruct contractInfoReqMapstruct;
	private final ContractInfoResMapstruct contractInfoResMapstruct;
	private final ContractReqMapstruct contractReqMapstruct;
	private final ContractResMapstruct contractResMapstruct;


	@Override // dateSearchCondition
	public ArrayList<EstimateResDto> getEstimateList(EstimateReqDto estimateReqDto) {

//		MyBatis
		return estimateMapper.selectEstimateList(estimateReqDto);

//		JPA
//		ArrayList<EstimateEntity> estimateEntities = new ArrayList<>();
//		if (estimateReqDto.getDateSearchCondition().equals("estimateDate")) {
//			estimateEntities = estimateRepository.findByEstimateDateBetween(estimateReqDto.getStartDate(), estimateReqDto.getEndDate());
//
//
//		} else if (estimateReqDto.getDateSearchCondition().equals("effectiveDate")) {
//			estimateEntities = estimateRepository.findByEffectiveDateBetween(estimateReqDto.getStartDate(), estimateReqDto.getEndDate());
//		}
//
//		ArrayList<EstimateResDto> estimateResDtos = null;
//		estimateResDtos = estimateResMapstruct.toDto(estimateEntities);
//		return estimateResDtos;
	}

	@Override
	public ArrayList<EstimateDetailResDto> getEstimateDetailList(EstimateReqDto estimateReqDto) {

		ArrayList<EstimateDetailResDto> estimateDetailResDtoList = null;

		estimateDetailResDtoList = estimateMapper.selectEstimateDetailList(estimateReqDto.getEstimateNo());
		return estimateDetailResDtoList;
	}

	//견적등록
	@Override
	public HashMap<String, Object> addNewEstimate(EstimateReqDto estimateReqDto) {

		ModelMap resultMap = new ModelMap();

		int cnt = 1;

		// 새로운 견적일련번호 생성(ex. ES20230816-49)
		String newEstimateNo = getNewEstimateNo(estimateReqDto.getEstimateDate());
		// 뷰에서 보내온 EstimateDto에 새로운 견적일련번호 set,  뷰에서 전달받을때는 NULL인 상태

		estimateReqDto.setEstimateNo(newEstimateNo);
		EstimateEntity estimateEntity = estimateReqMapstruct.toEntity(estimateReqDto);
		// 견적상세 Bean 을 Insert
		ArrayList<EstimateDetailEntity> estimateDetailEntityList = new ArrayList<>();
		
		StringBuffer newEstimateDetailNoInsert = new StringBuffer();

		// 견적상세 List - 견적상세 bean 
		for (EstimateDetailReqDto estimateDetailReqDto : estimateReqDto.getEstimateDetailReqDtoList()) {
			
			// 견적상세일번호 생성
			StringBuffer newEstimateDetailNo = new StringBuffer();
			newEstimateDetailNo.append("ES");
			newEstimateDetailNo.append(newEstimateNo);
			newEstimateDetailNo.append("-"); 
			newEstimateDetailNo.append(String.format("%02d", cnt++));	

			estimateDetailReqDto.setEstimateDetailNo(newEstimateDetailNo.toString());
			estimateDetailReqDto.setEstimateNo(newEstimateNo);

			// 새로 생성된 견적상세일련번호를 저장
			newEstimateDetailNoInsert.append(newEstimateDetailNo.toString()+",");
			EstimateDetailEntity estimateDetailEntity = estimateDetailReqMapstruct.toEntity(estimateDetailReqDto);
			estimateDetailEntityList.add(estimateDetailEntity);
		}
		estimateEntity.setEstimateDetailEntityList(estimateDetailEntityList);
		// jpa 사용
		estimateRepository.save(estimateEntity);

		resultMap.put("newEstimateDetailNo", newEstimateDetailNoInsert);

		resultMap.put("newEstimateNo", newEstimateNo);

		return resultMap;
	}

	public String getNewEstimateNo(String estimateDate) {

		StringBuffer newEstimateNo = null;

		int i = estimateMapper.selectEstimateCount(estimateDate);

		newEstimateNo = new StringBuffer();
		newEstimateNo.append("ES");
		newEstimateNo.append(estimateDate.replace("-", ""));
		newEstimateNo.append(String.format("%02d", i)); 
			
		return newEstimateNo.toString();
	}
	
	public String getNewEstimateDetailNo(String estimateNo) {

		StringBuffer newEstimateDetailNo = null;

		int i = estimateMapper.selectEstimateDetailSeq(estimateNo);

		newEstimateDetailNo = new StringBuffer();
		newEstimateDetailNo.append("ES");
		newEstimateDetailNo.append(estimateNo.replace("-", ""));
		newEstimateDetailNo.append("-"); 
		newEstimateDetailNo.append(String.format("%02d", i));		   

		return newEstimateDetailNo.toString();
	}

	@Override
	public ModelMap removeEstimate(String estimateNo, String status) {

		ModelMap resultMap = null;

//		estimateMapper.deleteEstimate(estimateNo);
//		ArrayList<EstimateDetailEntity> estimateDetailEntityList = getEstimateDetailList(estimateNo);

//		for (EstimateDetailEntity bean : estimateDetailEntityList) {
//			bean.setStatus(status);
//		}

//		resultMap = batchEstimateDetailListProcess(estimateDetailEntityList);

		resultMap.put("removeEstimateNo", estimateNo);

		return resultMap;
	}

	@Override
	public void batchEstimateDetailListProcess(ArrayList<EstimateDetailReqDto> estimateDetailReqDtos) {
		
		for (EstimateDetailReqDto estimateDetailReqDto : estimateDetailReqDtos) {
			EstimateDetailEntity estimateDetail = estimateDetailReqMapstruct.toEntity(estimateDetailReqDto);
			
			String status = estimateDetail.getStatus();
			switch (status) {

			case "update":
				estimateDetailRepository.save(estimateDetail); // Entity로 변환하여 Repo에 저장
				break;

				//기존의 값을 수정했을 경우
			case "delete":
				//jpa 적용
				estimateDetailRepository.delete(estimateDetail);
				break;
			}
		}
	}

	
	@Override
	public ArrayList<ContractInfoResDto> getContractList(
			ContractInfoReqDto contractInfoReqDto) {
		ArrayList<ContractInfoResDto> contractInfoResDtoList = null;
		contractInfoResDtoList = contractMapper.selectContractList(contractInfoReqDto);
		return contractInfoResDtoList;

//		ArrayList<ContractInfoDto> contractInfoDtoList = null;
//		if (contractInfoReqDto.getSearchCondition().equals("searchByPeriod")) {
//			contractInfoDtoList = contractInfoRepository.findByContractDateBetween(contractInfoReqDto.getStartDate(), contractInfoReqDto.getEndDate());
//
//		} else if (contractInfoReqDto.getSearchCondition().equals("searchByCompany")) {
//			contractInfoDtoList = contractInfoRepository.findByCustomerCode(contractInfoReqDto.getCustomerCode());
//		}
//
//		ArrayList<ContractInfoResDto> contractInfoResDtoList = contractInfoResMapstruct.toDto(contractInfoDtoList);
//		return contractInfoResDtoList;
	}

	
	@Override
	public ArrayList<ContractDetailEntity> getContractDetailList(String contractNo) {

//		ArrayList<ContractDetailEntity> contractDetailTOList = null;
//		contractDetailTOList = contractMapper.selectContractDetailList(contractNo);
//		return contractDetailTOList;
		return contractMapper.selectContractDetailList(contractNo);
	}

	@Override
	public ArrayList<EstimateResDto> getEstimateListInContractAvailable(EstimateReqDto estimateReqDto) {


		ArrayList<EstimateResDto> estimateListInContractAvailable = null;
		estimateListInContractAvailable = contractMapper.selectEstimateListInContractAvailable(estimateReqDto);

		for (EstimateResDto bean : estimateListInContractAvailable) {
			bean.setEstimateDetailResDtoList(estimateMapper.selectEstimateDetailList(bean.getEstimateNo()));//ES2022011360
		}

		return estimateListInContractAvailable;
	}

	@Override
	public HashMap<String, Object> addNewContract(ContractReqDto contractReqDto) {

		HashMap<String, Object> resultMap = new HashMap<>();
		
		// 새로운 수주일련번호 생성
		String newContractNo = getNewContractNo(contractReqDto.getContractDate()); //CO + contractDate + 01 <= 01은 첫번째라는 뜻 2번째이며 02 로 부여가 됨
		contractReqDto.setContractNo(newContractNo); // 새로운 수주일련번호 세팅

		ContractEntity contractEntity = contractReqMapstruct.toEntity(contractReqDto);
		contractRepository.save(contractEntity);

//		EstimateEntity estimate = new EstimateEntity();

		// 견적 테이블에 수주여부 "Y" 로 수정
		Optional<EstimateEntity> estimateEntity = estimateRepository.findByEstimateNo(contractEntity.getEstimateNo());
			estimateEntity.ifPresent(estimateToUpdate ->{ // estimateEntity가 존재할 경우에만 람다식 실행
				estimateToUpdate.setContractStatus("Y");
				estimateRepository.save(estimateToUpdate);
			});

		// ContractDetail 저장
		// jpa 미구현 - procedure 호출
		HashMap<String, Object> map = new HashMap<>();
		map.put("estimateNo", contractReqDto.getEstimateNo()); // 견적상세테이블 조회시 사용
		System.out.println("😶‍🌫️map.get(\"estimateNo\") = " + map.get("estimateNo"));
		map.put("contractNo", newContractNo); // 수주상세번호 만들때 사용
		System.out.println("🤯map.get(\"contractNo\") = " + map.get("contractNo"));
		map.put("contractType", contractEntity.getContractType()); //STOCK_AMOUNT 구하기위해
		System.out.println("😒map.get(\"contractType\") = " + map.get("contractType"));
		map.put("customerCode", contractEntity.getCustomerCode()); //STOCK_AMOUNT 구하기위해
		System.out.println("😒map.get(\"customerCode\") = " + map.get("customerCode"));

		// map.put();
		// Entity로 보내야 정상실행됨, dto로 보내면 Null뜸, Entity로 저장을 하므로 받아올때의 데이터인 dto에는 contractType이 없고 저장한 Entity에만 contractType이 있어서 그런듯?


		contractMapper.insertContractDetail(map);
		
		resultMap.put("errorCode", map.get("ERROR_CODE"));
		resultMap.put("errorMsg", map.get("ERROR_MSG"));
		
		return resultMap;
	}
	

	public String getNewContractNo(String contractDate) {
		
		StringBuffer newContractNo = null;

		int i = contractMapper.selectContractCount(contractDate);
		newContractNo = new StringBuffer();
		newContractNo.append("CO"); //CO
		newContractNo.append(contractDate.replace("-", "")); 
		newContractNo.append(String.format("%02d", i));

		return newContractNo.toString();
	}
	
	@Override
	public ModelMap batchContractDetailListProcess(ArrayList<ContractDetailEntity> contractDetailEntityList) {

		ModelMap resultMap = new ModelMap();

		ArrayList<String> insertList = new ArrayList<>();
		ArrayList<String> updateList = new ArrayList<>();
		ArrayList<String> deleteList = new ArrayList<>();
		
		for (ContractDetailEntity bean : contractDetailEntityList) {

			String status = bean.getStatus();

			switch (status) {

			case "INSERT":
				
				//contractMapper.insertContractDetail(bean);
				insertList.add(bean.getContractDetailNo());

				break;

			case "UPDATE":
				
				//contractMapper.updateContractDetail(bean);
				updateList.add(bean.getContractDetailNo());

				break;
					
			case "DELETE":

				contractMapper.deleteContractDetail(bean);
				deleteList.add(bean.getContractDetailNo());

				break;

			}

		}

		resultMap.put("INSERT", insertList);
		resultMap.put("UPDATE", updateList);
		resultMap.put("DELETE", deleteList);

		return resultMap;
	}

	@Override // 앞단에서 처리
	public void changeContractStatusInEstimate(String estimateNo, String contractStatus) {

		HashMap<String, String> map = new HashMap<>();

		map.put("estimateNo", estimateNo);
		map.put("contractStatus", contractStatus);
		
		estimateMapper.changeContractStatusOfEstimate(map);

	}

	public void cancelEstimate(EstimateReqDto estimateReqDto) {

		EstimateEntity estimateEntity = estimateReqMapstruct.toEntity(estimateReqDto);
		List<EstimateDetailEntity> estimateDetailEntityList
				= estimateDetailReqMapstruct.toEntity(estimateReqDto.getEstimateDetailReqDtoList());
		estimateEntity.setEstimateDetailEntityList(estimateDetailEntityList);

		estimateRepository.deleteByEstimateNo(estimateEntity.getEstimateNo());
	}
	
}
