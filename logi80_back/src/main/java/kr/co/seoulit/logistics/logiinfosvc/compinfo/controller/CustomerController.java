package kr.co.seoulit.logistics.logiinfosvc.compinfo.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.nexacro17.xapi.data.PlatformData;
import kr.co.seoulit.logistics.logiinfosvc.compinfo.service.CompInfoService;
import kr.co.seoulit.logistics.logiinfosvc.compinfo.to.CodeDetailTO;
import kr.co.seoulit.logistics.logiinfosvc.compinfo.to.CustomerTO;
import kr.co.seoulit.logistics.sys.util.DatasetBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/compinfo/*")
public class CustomerController {

    @Autowired
    private CompInfoService compInfoService;
    @Autowired
    private DatasetBeanMapper datasetBeanMapper;

    ModelMap map = null;

    // GSON 라이브러리
    private static Gson gson = new GsonBuilder().serializeNulls().create(); // 속성값이 null 인 속성도 JSON 변환

    //고객사조회
    @RequestMapping(value = "/customer/list")
    public void searchCustomerList(@RequestAttribute("resData") PlatformData resData) throws Exception {

        ArrayList<CustomerTO> customerList = compInfoService.getCustomerList();
        datasetBeanMapper.beansToDataset(resData, customerList, CustomerTO.class);

    }


    @RequestMapping(value = "/customer/batch", method = RequestMethod.POST)
    public ModelMap batchListProcess(HttpServletRequest request, HttpServletResponse response) {
        String batchList = request.getParameter("batchList");
        map = new ModelMap();
        try {
            ArrayList<CustomerTO> customerList = gson.fromJson(batchList, new TypeToken<ArrayList<CustomerTO>>() {
            }.getType());
            HashMap<String, Object> resultMap = compInfoService.batchCustomerListProcess(customerList);

            map.put("result", resultMap);
            map.put("errorCode", 1);
            map.put("errorMsg", "성공!");
        } catch (Exception e1) {
            e1.printStackTrace();
            map.put("errorCode", -1);
            map.put("errorMsg", e1.getMessage());
        }
        return map;
    }

    @RequestMapping(value = "/customer/alllist")
    public void searchCustomerAllList(@RequestAttribute("reqData") PlatformData reqData,
                                      @RequestAttribute("resData") PlatformData resData) throws Exception {
        String divisionCode = reqData.getVariable("divisionCode").getString();
        ArrayList<CustomerTO> customerList = compInfoService.getCustomerList();
        ArrayList<CodeDetailTO> detailCodeList = compInfoService.getCodeDetailList(divisionCode);
        datasetBeanMapper.beansToDataset(resData, customerList, CustomerTO.class);
        datasetBeanMapper.beansToDataset(resData, detailCodeList, CodeDetailTO.class);
    }

    @RequestMapping(value = "/customer/allbatch")
    public void batchCustomer(@RequestAttribute("reqData") PlatformData reqData,
                              @RequestAttribute("resData") PlatformData resData) throws Exception {
        List<CustomerTO> customerList = datasetBeanMapper.datasetToBeans(reqData, CustomerTO.class);
        compInfoService.batchCustomer(customerList);
    }
}
