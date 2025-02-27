package kr.co.seoulit.logistics.busisvc.sales.mapper;

import kr.co.seoulit.logistics.busisvc.sales.dto.ReleaseInfoReqDto;
import kr.co.seoulit.logistics.busisvc.sales.dto.ReleaseInfoResDto;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.seoulit.logistics.busisvc.sales.to.DeliveryInfoEntity;
import kr.co.seoulit.logistics.busisvc.sales.to.ReleaseInfoEntity;
import kr.co.seoulit.logistics.busisvc.sales.to.ReleaseInfoTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReleaseMapper {

    //출고 등록
    public void releaseRegist(HashMap<String, Object> map);

    public void insertReleaseResult(ReleaseInfoEntity TO);

    //출고현황조회
    public ArrayList<ReleaseInfoResDto> selectReleaseInfoList();

    //출고현황 수정
    public void updateReleaseInfo(ReleaseInfoTO releaseInfoTO);

    //출고현황 삭제
    public void deleteReleaseInfo(String releaseNo);

}
