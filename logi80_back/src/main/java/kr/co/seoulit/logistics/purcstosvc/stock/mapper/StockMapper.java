package kr.co.seoulit.logistics.purcstosvc.stock.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.ui.ModelMap;

import kr.co.seoulit.logistics.purcstosvc.stock.to.StockChartTO;
import kr.co.seoulit.logistics.purcstosvc.stock.to.StockLogTO;
import kr.co.seoulit.logistics.purcstosvc.stock.entity.StockEntity;

@Mapper
public interface StockMapper {
	
	public ArrayList<StockEntity> selectStockList();
	
	public ArrayList<StockEntity> selectWarehouseStockList(String warehouseCode);
	
	public ArrayList<StockLogTO> selectStockLogList(HashMap<String, String> map);
	
	public void warehousing(HashMap<String, String> map);
	
	public ModelMap updatesafetyAllowance(HashMap<String, String> map);
	
	public ArrayList<StockChartTO> selectStockChart();
	
	public void insertStock(StockEntity bean);
	
	public void updateStock(StockEntity bean);
	
	public void deleteStock(StockEntity bean);

}
