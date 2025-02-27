package kr.co.seoulit.logistics.busisvc.sales.mapper;

import kr.co.seoulit.logistics.busisvc.sales.dto.*;
import kr.co.seoulit.logistics.busisvc.sales.to.DeliveryInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface DeliveryMapper {

    public ArrayList<DeliveryInfoResDto> selectDeliveryInfoList();

    public void deliver(HashMap<String, Object> map);

//    public ArrayList<DeliveryInfoResDto> selectDeliveryInfoListByDeliverydate(HashMap<String, String> map);

    public void insertDeliveryResult(DeliveryInfoEntity TO);

    public void updateDeliveryResult(DeliveryInfoEntity TO);

    public void deleteDeliveryResult(DeliveryInfoEntity TO);

    public ArrayList<ReverseResDto> selectReturnAbleList(ReverseReqDto reverseReqDto);

    public void insertReturnList(HashMap<String, String> map);

    public ArrayList<QuarterResDto> selectSalesQuaChart();

}
