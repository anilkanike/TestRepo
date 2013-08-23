package com.transgrid.mib.ellipse.screenservice;

/**
 * 
 * @author Anil Kanike
 *
 */
public class Error {

	private String err;
	private String errMsg;
	private String errType;
	private String errCode;
	private String errSev;

	public Error(String err) throws Exception {
		this.err = err;
		
		if(err.equals("")) return;
		
		String[] tmpArr = this.err.split("-");
		this.errMsg = tmpArr[1].trim();

		tmpArr = tmpArr[0].split(":");
		this.errCode = tmpArr[1].trim();

		this.errType = tmpArr[0].trim().substring(0, 1);
		this.errSev = tmpArr[0].trim().substring(1, tmpArr[0].trim().length());
	}

	public boolean isError() {
		if (err != null && !err.trim().equals("")) {
			if (getErrType().equals("X")) {
				// setIdle();
				return true;
			}
		}
		return false;
	}
	
	public boolean isWarning() {
		if (err != null && !err.trim().equals("")) {
			if (getErrType().equals("W")) {
				// setIdle();
				return true;
			}
		}
		return false;
	}

	public String getErr() {
		return err;
	}

	public void setErr(String err) {
		this.err = err;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getErrType() {
		return errType;
	}

	public void setErrType(String errType) {
		this.errType = errType;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrSev() {
		return errSev;
	}

	public void setErrSev(String errSev) {
		this.errSev = errSev;
	}

}
