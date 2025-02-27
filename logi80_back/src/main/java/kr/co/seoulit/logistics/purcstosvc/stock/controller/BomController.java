package kr.co.seoulit.logistics.purcstosvc.stock.controller;

import java.util.ArrayList;
import java.util.HashMap;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.nexacro17.xapi.data.PlatformData;
import kr.co.seoulit.logistics.sys.util.DatasetBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import kr.co.seoulit.logistics.purcstosvc.stock.service.StockService;
import kr.co.seoulit.logistics.purcstosvc.stock.to.BomDeployTO;
import kr.co.seoulit.logistics.purcstosvc.stock.to.BomInfoTO;
import kr.co.seoulit.logistics.purcstosvc.stock.to.BomTO;

@RestController
@RequestMapping("/stock/*")
public class BomController {

	@Autowired
	private StockService stockService;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;


	@RequestMapping(value="/bom/deploy")
	public void searchBOM(@RequestAttribute("reqData") PlatformData reqData,
							  @RequestAttribute("resData")PlatformData resData) throws Exception {

		String deployCondition =  reqData.getVariable("deployCondition").getString();
		String itemCode =  reqData.getVariable("itemCode").getString();
		String itemClassificationCondition =  reqData.getVariable("itemClassificationCondition").getString();

		ArrayList<BomDeployTO> bomDeployList = stockService.getBomDeployList(deployCondition, itemCode, itemClassificationCondition);
		datasetBeanMapper.beansToDataset(resData, bomDeployList,BomDeployTO.class);

	}

	@RequestMapping(value="/searchBomInfo")
	public void searchBomInfo(@RequestAttribute("reqData") PlatformData reqData,
								  @RequestAttribute("resData")PlatformData resData) throws Exception {
		String parentItemCode =  reqData.getVariable("parentItemCode").getString();

			ArrayList<BomInfoTO> bomInfoList = stockService.getBomInfoList(parentItemCode);
		datasetBeanMapper.beansToDataset(resData, bomInfoList,BomInfoTO.class);


	}

//	@RequestMapping(value="/bom/available", method=RequestMethod.GET)
//	public ModelMap searchAllItemWithBomRegisterAvailable(HttpServletRequest request, HttpServletResponse response) {
//		map= new ModelMap();
//		try {
//			ArrayList<BomInfoTO> allItemWithBomRegisterAvailable = stockService.getAllItemWithBomRegisterAvailable();
//
//			map.put("gridRowJson", allItemWithBomRegisterAvailable);
//			map.put("errorCode", 1);
//			map.put("errorMsg", "성공");
//		} catch (Exception e1) {
//			e1.printStackTrace();
//			map.put("errorCode", -1);
//			map.put("errorMsg", e1.getMessage());
//		}
//		return map;
//	}
//
//	@RequestMapping(value="/bom/batch", method=RequestMethod.POST)
//	public ModelMap batchBomListProcess(HttpServletRequest request, HttpServletResponse response) {
//		String batchList = request.getParameter("batchList");
//		map= new ModelMap();
//		ArrayList<BomTO> batchBomList = gson.fromJson(batchList, new TypeToken<ArrayList<BomTO>>() {
//		}.getType());
//		try {
//			HashMap<String, Object> resultList = stockService.batchBomListProcess(batchBomList);
//
//			map.put("result", resultList);
//			map.put("errorCode", 1);
//			map.put("errorMsg", "성공");
//		} catch (Exception e1) {
//			e1.printStackTrace();
//			map.put("errorCode", -1);
//			map.put("errorMsg", e1.getMessage());
//		}
//		return map;
//	}

}
