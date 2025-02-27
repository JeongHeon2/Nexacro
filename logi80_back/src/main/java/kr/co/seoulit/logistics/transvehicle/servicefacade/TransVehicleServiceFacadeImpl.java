package kr.co.seoulit.logistics.transvehicle.servicefacade;

import kr.co.seoulit.logistics.transvehicle.dao.TransVehicleDAO;
import kr.co.seoulit.logistics.transvehicle.to.TransVehicleTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class TransVehicleServiceFacadeImpl implements TransVehicleServiceFacade{

    @Autowired
    private TransVehicleDAO transVehicleDAO;

    private ModelMap modelMap = new ModelMap();


    @Override
    public ArrayList<TransVehicleTO> allTransVehicleList() {
        ArrayList<TransVehicleTO> vehicleList= null;

        vehicleList = transVehicleDAO.allTransVehicleList();

        return vehicleList;
    }

    @Override
    public ModelMap getTransVehicleList(String type) {
        ArrayList<TransVehicleTO> transVehicleList=transVehicleDAO.getTransVehicleList(type);
        try{
            modelMap.put("transVehicleList", transVehicleList); //명칭통일필요
            modelMap.put("errorCode", 1);
            modelMap.put("errorMsg", "성공");
        }catch(Exception e){
            e.printStackTrace();
            modelMap.put("errorCode", -2);
            modelMap.put("errorMsg", e.getMessage());
        }
        return modelMap;
    }

    @Override
    public void addNewVehicle(TransVehicleTO transVehicleTO) {
        Map<String, Object> result = new HashMap<>();

        try {
            String getNum = transVehicleDAO.getTransVehicleMaxNo().substring(2);
            int getNum2 = Integer.parseInt(getNum) + 1;

            String lastNo;
            if (getNum2 >= 10) {
                lastNo = Integer.toString(getNum2);
            } else {
                lastNo = '0' + Integer.toString(getNum2);
            }
            String generatedVehicleNumber = "TR" + lastNo;

            transVehicleTO.setVehicleNumber(generatedVehicleNumber);
//            System.out.println(transVehicleTO.getManufacturingCompany());
//
//            Map<String, Object> transVehicleMap = convertTransVehicleToMap(transVehicleTO);

            String plandate1 = transVehicleTO.getYearOfManufacture();
            String plandate2 = transVehicleTO.getMaintenanceDate();
            SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat newDtFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date formatDate1 = dtFormat.parse(plandate1);
            Date formatDate2 = dtFormat.parse(plandate2);
            // Date타입의 변수를 새롭게 지정한 포맷으로 변환
            String strNewDtFormat1 = newDtFormat.format(formatDate1);
            String strNewDtFormat2 = newDtFormat.format(formatDate2);
            transVehicleTO.setYearOfManufacture(strNewDtFormat1);
            transVehicleTO.setMaintenanceDate(strNewDtFormat2);

            // 운송수단 등록
            transVehicleDAO.addNewVehicle(transVehicleTO);

            // 결과에 자동 생성된 차량 번호 추가
            result.put("generatedVehicleNumber", generatedVehicleNumber);
            result.put("errorCode", 1);
            result.put("errorMsg", "성공");
        } catch (Exception e) {
            result.put("errorCode", -2);
            result.put("errorMsg", e.getMessage());
        }
    }


    // Map<String, Object>로 변환
    private Map<String, Object> convertTransVehicleToMap(TransVehicleTO transVehicle) {
        Map<String, Object> transVehicleMap = new HashMap<>();

        transVehicleMap.put("vehicleNumber", transVehicle.getVehicleNumber());
        transVehicleMap.put("manufacturingCompany", transVehicle.getManufacturingCompany());

        return transVehicleMap;
    }

    @Override
    public void updateTransVehicle(TransVehicleTO transList) {
        transVehicleDAO.updateTransVehicle(transList);
    }

    @Override
    public void deleteTransVehicle(String vehicleNumber) {
        transVehicleDAO.deleteTransVehicle(vehicleNumber);
    }
}
