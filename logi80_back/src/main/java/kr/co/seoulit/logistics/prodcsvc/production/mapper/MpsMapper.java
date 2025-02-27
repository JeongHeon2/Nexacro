package kr.co.seoulit.logistics.prodcsvc.production.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import kr.co.seoulit.logistics.prodcsvc.production.entity.MpsEntity;
import kr.co.seoulit.logistics.prodcsvc.production.to.MpsResTO;
import org.apache.ibatis.annotations.Mapper;

import kr.co.seoulit.logistics.prodcsvc.production.to.MpsTO;

@Mapper
public interface MpsMapper {

	public ArrayList<MpsEntity> selectMpsList(MpsResTO mpsResTO);
	
	public List<MpsTO> selectMpsCount(String mpsPlanDate);
	
	public void insertMps(MpsEntity TO);
	
	public void updateMps(MpsEntity TO);
	
	public void changeMrpApplyStatus(HashMap<String, String> map);
	
	public void deleteMps(MpsEntity TO);
	
	MpsEntity getNewMpsNo(String mpsPlanDate);
	
}
