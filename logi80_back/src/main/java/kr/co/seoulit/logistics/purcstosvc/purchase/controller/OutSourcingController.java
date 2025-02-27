package kr.co.seoulit.logistics.purcstosvc.purchase.controller;

import com.nexacro17.xapi.data.PlatformData;
import kr.co.seoulit.logistics.purcstosvc.purchase.service.PurchaseService;
import kr.co.seoulit.logistics.purcstosvc.purchase.to.OutSourcingTO;
import kr.co.seoulit.logistics.sys.util.DatasetBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/purchase/*")
public class OutSourcingController {

	@Autowired
	private PurchaseService purchaseService;

	@Autowired
	private DatasetBeanMapper datasetBeanMapper;


//	ModelMap map = null;
//
//	@RequestMapping(value="/outsourcing/list" , method=RequestMethod.GET)
//	public ModelMap searchOutSourcingList(HttpServletRequest request, HttpServletResponse response) {
//		String fromDate = request.getParameter("fromDate");
//		String toDate = request.getParameter("toDate");
//		String customerCode = request.getParameter("customerCode");
//		String itemCode = request.getParameter("itemCode");
//		String materialStatus = request.getParameter("materialStatus");
//		map = new ModelMap();
//
//		try {
//			ArrayList<OutSourcingTO> outSourcingList = purchaseService.searchOutSourcingList(fromDate,toDate,customerCode,itemCode,materialStatus);
//			map.put("outSourcingList", outSourcingList);
//			map.put("errorCode", 1);
//			map.put("errorMsg", "성공");
//		} catch (Exception e1) {
//			e1.printStackTrace();
//			map.put("errorCode", -1);
//			map.put("errorMsg", e1.getMessage());
//		}
//		return map;
//	}

	@RequestMapping(value="/outSourcing/List")
	public void searchOutSourcingInfo(@RequestAttribute("reqData") PlatformData reqData,
									  @RequestAttribute("resData") PlatformData resData) throws Exception {

		String dataSearchCondition = reqData.getVariable("dataSearchCondition").getString();
		String startDate = reqData.getVariable("startDate").getString();
		String endDate = reqData.getVariable("endDate").getString();
		System.out.println("넥사에서 왔냐1"+reqData);
		ArrayList<OutSourcingTO> outSourcingTOList = purchaseService.getOutSourcingList(dataSearchCondition, startDate, endDate);
		System.out.println("PurchaseService에서 왔냐"+outSourcingTOList);
		datasetBeanMapper.beansToDataset(resData, outSourcingTOList, OutSourcingTO.class);
		System.out.println("넥사로 던져주냐"+resData);

	}
}
