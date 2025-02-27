package kr.co.seoulit.logistics.transvehicle.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.nexacro17.xapi.data.PlatformData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.co.seoulit.logistics.logiinfosvc.compinfo.to.CustomerTO;
import kr.co.seoulit.logistics.sys.util.DatasetBeanMapper;
import kr.co.seoulit.logistics.transvehicle.servicefacade.TransVehicleServiceFacade;
import kr.co.seoulit.logistics.transvehicle.to.TransVehicleTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Api(description = "운송수단관리")
@CrossOrigin("*")
@RestController
@RequestMapping("/logi/trans/*")
public class TransVehicleController {
    @Autowired
    private TransVehicleServiceFacade TransVehicleSF;

    @Autowired
    private DatasetBeanMapper datasetBeanMapper;

    private ModelMap modelMap = new ModelMap();

    private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 JSON 변환

    @ApiOperation(value = "운송수단 전체 조회")
    @RequestMapping(value = "/allTransVehicleList", method = RequestMethod.GET)
    public void allTransVehicleList(@RequestAttribute("reqData") PlatformData reqData,
                                        @RequestAttribute("resData") PlatformData resData) throws Exception {
        ArrayList<TransVehicleTO> transVehiscleList = TransVehicleSF.allTransVehicleList();

        datasetBeanMapper.beansToDataset(resData, transVehiscleList, TransVehicleTO.class);
    }

//    @ApiOperation(value = "운송수단 type별 조회")
//    @RequestMapping (value = "/getTransVehicleList/{type}", method = RequestMethod.GET)
//    public void getTransVehicleList(@RequestAttribute("reqData") PlatformData reqData,
//                                    @RequestAttribute("resData") PlatformData resData) throws Exception {
//
//    }

    @ApiOperation(value = "운송수단 등록")
    @RequestMapping(value ="/addTransportation")
    public void addNewVehicle(@RequestAttribute("reqData") PlatformData reqData,
                              @RequestAttribute("resData") PlatformData resData) throws Exception{
        System.out.println("제발 와줘바바");
        TransVehicleTO transVhc = datasetBeanMapper.datasetToBean(reqData, TransVehicleTO.class);
        TransVehicleSF.addNewVehicle(transVhc);
    }

    @ApiOperation(value = "운송수단 수정")
    @RequestMapping("/updateTransportation")
    public void updateTransVehicle(@RequestAttribute("reqData") PlatformData reqData,
                                   @RequestAttribute("resData") PlatformData resData) throws Exception{
        TransVehicleTO transList = datasetBeanMapper.datasetToBean(reqData, TransVehicleTO.class);
        TransVehicleSF.updateTransVehicle(transList);
    }

    @ApiOperation(value = "운송수단 삭제")
    @RequestMapping(value = "/deleteTransportation")
    public void deleteTransVehicle(@RequestAttribute("reqData") PlatformData reqData,
                                   @RequestAttribute("resData") PlatformData resData) throws Exception {
        String vehicleNumber = reqData.getVariable("vehicleNumber").getString();
        System.out.println("운송수단삭제"+ vehicleNumber);
        TransVehicleSF.deleteTransVehicle(vehicleNumber);
    }
}