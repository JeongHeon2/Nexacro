package kr.co.seoulit.logistics.busisvc.sales.repository;
import kr.co.seoulit.logistics.busisvc.sales.to.SalesPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

@Repository
public interface SalesPlanRepository extends JpaRepository<SalesPlanEntity, String>{
    @Override
    <S extends SalesPlanEntity> S save(S entity);

    @Override
    ArrayList<SalesPlanEntity> findAll();

    // @Query("SELECT s.salesPlanNo FROM SalesPlanEntity s ORDER BY s.salesPlanNo DESC")
    // @Query("SELECT s.salesPlanNo FROM SalesPlanEntity s")
    @Query(value = "SELECT sales_plan_no  FROM sales_plan WHERE ROWNUM = 1 ORDER BY sales_plan_no  DESC", nativeQuery = true)
    String findTopSalesPlanNo();

    SalesPlanEntity findTopByOrderBySalesPlanNoDesc();
}
