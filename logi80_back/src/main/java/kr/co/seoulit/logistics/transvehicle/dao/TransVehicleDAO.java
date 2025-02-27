package kr.co.seoulit.logistics.transvehicle.dao;

import kr.co.seoulit.logistics.transvehicle.to.TransVehicleTO;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.Map;

@Mapper
public interface TransVehicleDAO {
    public ArrayList<TransVehicleTO> allTransVehicleList();
    public ArrayList<TransVehicleTO> getTransVehicleList(String type);

    // 최신 차량범호 조회
    public String getTransVehicleMaxNo();

    // 운송수단 등록
    void addNewVehicle(TransVehicleTO transVehicleTO);

    public void updateTransVehicle(TransVehicleTO transList);
    public void deleteTransVehicle(String vehicleNumber);

//    public ItemTO searchItem(String itemCode);
//
//    public ArrayList<TransVehicleTO> searchItemGroupList();
//
//    public void insertItem(ItemTO TO);
//
//    public void updateItem(ItemTO TO);
//
//    public void deleteItem(ItemTO TO);
}
