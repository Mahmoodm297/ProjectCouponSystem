package ifcs;

import facade.ClientType;

public interface CouponClientFacade {
	
	/**
	 * Login interface
	 */
	CouponClientFacade login (String name, String password, ClientType clientType);
}
