package kr.co.seoulit.logistics.purcstosvc.stock.service;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.seoulit.logistics.purcstosvc.stock.dto.StockResDto;
import org.springframework.ui.ModelMap;

import kr.co.seoulit.logistics.purcstosvc.stock.to.BomDeployTO;
import kr.co.seoulit.logistics.purcstosvc.stock.to.BomInfoTO;
import kr.co.seoulit.logistics.purcstosvc.stock.to.BomTO;
import kr.co.seoulit.logistics.purcstosvc.stock.to.StockChartTO;
import kr.co.seoulit.logistics.purcstosvc.stock.to.StockLogTO;
import kr.co.seoulit.logistics.purcstosvc.stock.entity.StockEntity;



public interface StockService {

	public ArrayList<BomDeployTO> getBomDeployList(String deployCondition, String itemCode, String itemClassificationCondition);
	
	public ArrayList<BomInfoTO> getBomInfoList(String parentItemCode);
	
	public ArrayList<BomInfoTO> getAllItemWithBomRegisterAvailable();
	
	public HashMap<String, Object> batchBomListProcess(ArrayList<BomTO> batchBomList);
	
	public HashMap<String,Object> warehousing(ArrayList<String> orderNoArr);
	
	public ArrayList<StockResDto> getStockList();
	
	public void batchStockProcess(ArrayList<StockEntity> stockEntityList);
	
	public ArrayList<StockEntity> getWarehouseStockList(String warehouseCode);
	
	public ArrayList<StockLogTO> getStockLogList(String startDate,String endDate);
	
	public ModelMap changeSafetyAllowanceAmount(String itemCode, String itemName,
			String safetyAllowanceAmount);
	
	public ArrayList<StockChartTO> getStockChart();
}
