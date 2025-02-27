package kr.co.seoulit.logistics.purcstosvc.stock.controller;

import com.nexacro17.xapi.data.PlatformData;
import kr.co.seoulit.logistics.purcstosvc.stock.dto.StockResDto;
import kr.co.seoulit.logistics.purcstosvc.stock.service.StockService;
import kr.co.seoulit.logistics.purcstosvc.stock.to.StockChartTO;
import kr.co.seoulit.logistics.purcstosvc.stock.to.StockLogTO;
import kr.co.seoulit.logistics.purcstosvc.stock.entity.StockEntity;
import kr.co.seoulit.logistics.sys.util.DatasetBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/stock/*")
public class StockController {

    @Autowired
    private StockService stockService;
    @Autowired
    private DatasetBeanMapper datasetBeanMapper;

    @RequestMapping(value = "/sto/list")
    public void searchStockList(@RequestAttribute("resData") PlatformData resData) throws Exception {

        ArrayList<StockResDto> stockList = stockService.getStockList();
        datasetBeanMapper.beansToDataset(resData, stockList, StockResDto.class);

    }


    //재고로그조회
    @RequestMapping(value = "/sto/log-list")
    public void searchStockLogList(@RequestAttribute("resData") PlatformData resData,
                                   @RequestAttribute("reqData") PlatformData reqData) throws Exception {

        String startDate = reqData.getVariable("startDate").getString();
        String endDate = reqData.getVariable("endDate").getString();
        ArrayList<StockLogTO> stockLogList = stockService.getStockLogList(startDate, endDate);
        datasetBeanMapper.beansToDataset(resData, stockLogList, StockLogTO.class);

    }


    //입고
    @RequestMapping(value = "/sto/warehousing")
    public void warehousing(@RequestAttribute("reqData") PlatformData reqData,
                            @RequestAttribute("resData") PlatformData resData) throws Exception {

        String orderNoList = reqData.getVariableList().getString("orderNoList");
        ArrayList<String> orderNoArr = new ArrayList<>();
        orderNoArr.add(orderNoList);

        HashMap<String, Object> resultMap = stockService.warehousing(orderNoArr);
        resData.getVariableList().add("g_procedureMsg", resultMap.get("errorMsg"));
        resData.getVariableList().add("g_procedureCode", resultMap.get("errorCode"));

    }


    //창고 재고조회
    @RequestMapping(value = "/sto/warehousestocklist")
    public void searchWarehouseStockList(@RequestAttribute("reqData") PlatformData reqData,
                                         @RequestAttribute("resData") PlatformData resData) throws Exception {

        String houseCode = reqData.getVariable("houseCode").getString();
        ArrayList<StockEntity> stockList = stockService.getWarehouseStockList(houseCode);
        datasetBeanMapper.beansToDataset(resData, stockList, StockEntity.class);

    }

    //창고자재일괄처리
    @RequestMapping(value = "/sto/batch")
    public void modifyStockInfo(@RequestAttribute("reqData") PlatformData reqData,
                                @RequestAttribute("resData") PlatformData resData) throws Exception {

        ArrayList<StockEntity> stockEntityList = (ArrayList<StockEntity>) datasetBeanMapper.datasetToBeans(reqData, StockEntity.class);
        stockService.batchStockProcess(stockEntityList);

    }

    @RequestMapping(value = "/sto/chart")
    public void getStockChart(@RequestAttribute("reqData") PlatformData reqData,
                              @RequestAttribute("resData") PlatformData resData) throws Exception {
        ArrayList<StockChartTO> stockList = stockService.getStockChart();
        datasetBeanMapper.beansToDataset(resData, stockList, StockChartTO.class);
    }

}
