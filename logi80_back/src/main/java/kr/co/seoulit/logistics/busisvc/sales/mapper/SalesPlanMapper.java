package kr.co.seoulit.logistics.busisvc.sales.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.seoulit.logistics.busisvc.sales.to.SalesPlanEntity;
import org.apache.ibatis.annotations.Mapper;

import kr.co.seoulit.logistics.prodcsvc.production.to.SalesPlanInMpsAvailableTO;

@Mapper
public interface SalesPlanMapper {
	public ArrayList<SalesPlanEntity> selectSalesPlanList(HashMap<String, String> map);
			
	public int selectSalesPlanCount(String salesPlanDate);
	
	public ArrayList<SalesPlanInMpsAvailableTO>
		selectSalesPlanListInMpsAvailable(HashMap<String, String> map);
	
	public void insertSalesPlan(SalesPlanEntity TO);

	public void updateSalesPlan(SalesPlanEntity TO);
	
	public void changeMpsStatusOfSalesPlan(HashMap<String, String> map);	
	
	public void deleteSalesPlan(SalesPlanEntity TO);
	
}
