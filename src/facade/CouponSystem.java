package facade;

//import org.apache.tomcat.websocket.CaseInsensitiveKeyMap;

import dao.CouponDaoImpl;
import ifcs.CouponClientFacade;

public class CouponSystem {
	private static CouponSystem instance= null ;
	private CouponDaoImpl couponDao;
	private DailyCouponExpirationTask dailyTask;
	private CouponClientFacade couponClient = null;
	
			// ;
	public static CouponSystem getInstance(){
		if ( instance == null)
			instance = new CouponSystem();
		return instance;
		
	}
	private  CouponSystem() {
		couponDao = new CouponDaoImpl();
		dailyTask = new DailyCouponExpirationTask();
		Thread thread = new Thread(dailyTask);
		thread.start();
		//dailyTask.start();
		// TODO Auto-generated constructor stub
	}
	
	
	public void shutdown () {
		this.dailyTask.setQuit(true);
		
	}
	
	public CouponClientFacade login ( String name, String password, ClientType type) {
		//CouponClientFacade client = null;
		
		
		switch (type) {
		case ADMIN :
			couponClient = AdminFacade.getInstance().login(name, password,type);
			//client = client.login(name, password, type);
			//client = new Admin
			break;
		case COMPANY:
			couponClient =CompanyFacade.getInstance().login(name, password,type);
			//client.login(name, password, type);
			break;
		case CUSTOMER:
			couponClient = CustomerFacade.getInstance().login(name, password,type);
			//client.login(name, password, type);
			break ;
			default :
				System.out.println("Wrong Client Type");
				couponClient =  null;
		}
		
		//couponClient.
		return couponClient;		
		
	}

	
	
	
	

}
