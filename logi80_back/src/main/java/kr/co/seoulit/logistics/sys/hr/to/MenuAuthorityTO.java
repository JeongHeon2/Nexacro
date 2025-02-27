package kr.co.seoulit.logistics.sys.hr.to;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MenuAuthorityTO {
	private String menuCode;
	private String menuName;
	private String authorityGroupCode;
	private String menuLevel;
	private String authority;

}
