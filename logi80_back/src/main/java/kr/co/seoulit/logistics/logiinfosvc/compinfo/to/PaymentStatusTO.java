package kr.co.seoulit.logistics.logiinfosvc.compinfo.to;

import kr.co.seoulit.logistics.sys.annotation.Dataset;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Dataset(name="paymentStatus")
public class PaymentStatusTO  extends BaseTO {
    String purchase_sum, sale_sum;
}
