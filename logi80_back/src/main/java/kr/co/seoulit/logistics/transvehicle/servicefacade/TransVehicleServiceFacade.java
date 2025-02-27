package kr.co.seoulit.logistics.transvehicle.servicefacade;

import kr.co.seoulit.logistics.logiinfosvc.compinfo.to.CodeTO;
import kr.co.seoulit.logistics.logiinfosvc.compinfo.to.CustomerTO;
import kr.co.seoulit.logistics.transvehicle.to.TransVehicleTO;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface TransVehicleServiceFacade {
    public ArrayList<TransVehicleTO> allTransVehicleList();

    public ModelMap getTransVehicleList(String type);

    public void addNewVehicle(TransVehicleTO transVehicleTO);
    public void updateTransVehicle(TransVehicleTO transList);

   public void deleteTransVehicle(String vehicleNumber);


//    public void deleteSelectedItem(String itemCode);
//    public void deleteSelectedItemGroup(String itemGroupCode);
//    public void updateItemGroup(ItemGroupTO batchList);
//    public ModelMap searchItemGroupList();
//    public void batchListProcess(ItemTO batchList);
//    public void itemGroupBatchListProcess(ItemGroupTO batchList);
}
