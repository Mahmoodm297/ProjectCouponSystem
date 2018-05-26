package facade;

public class CouponSystemException extends Exception {
	private String msg;
	private Throwable cause;
	
	public CouponSystemException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
		this.msg = message;
		this.cause = cause;
	}

	public CouponSystemException() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CouponSystemException [msg=" + msg + ", cause=" + cause + "]";
	}
	
	

	
}
