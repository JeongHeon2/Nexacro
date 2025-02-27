package kr.co.seoulit.logistics.prodcsvc.production.service;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.seoulit.logistics.busisvc.logisales.dto.ContractDetailInMpsAvailableResDto;
import kr.co.seoulit.logistics.busisvc.logisales.to.ContractDetailInMpsAvailableTO;
import kr.co.seoulit.logistics.prodcsvc.production.entity.MpsEntity;
import kr.co.seoulit.logistics.prodcsvc.production.to.*;

public interface ProductionService {

	public ArrayList<MpsResTO> getMpsList(MpsReqTO mpsReqTO);
	public ArrayList<MpsResTO> getMpsMainList(MpsReqTO mpsReqTO);
	
	public ArrayList<ContractDetailInMpsAvailableResDto>
		getContractDetailListInMpsAvailable(String searchCondition, String startDate, String endDate);

	public ArrayList<SalesPlanInMpsAvailableTO> 
		getSalesPlanListInMpsAvailable(String searchCondition, String startDate, String endDate);

	public java.util.List<MpsEntity> convertContractDetailToMps(
			ArrayList<ContractDetailInMpsAvailableTO> contractDetailInMpsAvailableList);

	public HashMap<String, Object> convertSalesPlanToMps(
			ArrayList<SalesPlanInMpsAvailableTO> salesPlanInMpsAvailableList);
	
	public HashMap<String, Object> batchMpsListProcess(ArrayList<MpsEntity> mpsTOList);

	public ArrayList<MrpTO> searchMrpList(String mrpGatheringStatusCondition);
	
	public ArrayList<MrpTO> selectMrpListAsDate(String dateSearchCondtion, String startDate, String endDate);
	
	public ArrayList<MrpTO> searchMrpListAsMrpGatheringNo(String mrpGatheringNo);
	
	public ArrayList<MrpGatheringTO> searchMrpGatheringList(String dateSearchCondtion, String startDate, String endDate);
	
	public HashMap<String, Object> openMrp(ArrayList<String> mpsNoArr);

	public HashMap<String, Object> registerMrp(String mrpRegisterDate, 
			String mpsList);
	
	public HashMap<String, Object> batchMrpListProcess(ArrayList<MrpTO> mrpTOList);
	
	public ArrayList<MrpGatheringTO> getMrpGathering(ArrayList<String> mrpNoArr);
	
	public HashMap<String, Object> registerMrpGathering(String mrpGatheringRegisterDate, ArrayList<String> mrpNoArr,HashMap<String, String> mrpNoAndItemCodeMap);

}

