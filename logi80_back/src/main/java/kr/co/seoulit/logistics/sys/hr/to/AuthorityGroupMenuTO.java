package kr.co.seoulit.logistics.sys.hr.to;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AuthorityGroupMenuTO {
	private String authorityGroupCode;
	private String menuCode;
	private String menuName;
}
