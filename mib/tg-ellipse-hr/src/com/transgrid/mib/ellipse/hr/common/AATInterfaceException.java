package com.transgrid.mib.ellipse.hr.common;

/**
 * Common Exception for all AAT interfaces
 * 
 * @author Anil Kanike
 * 
 */
public class AATInterfaceException extends Exception {
	/**
     * 
     */
    private static final long serialVersionUID = 1L;

	public AATInterfaceException(Exception e) {
		super(e);
	}

	public AATInterfaceException(String s) {
		super(s);
	}

	public AATInterfaceException(String s, Exception e) {
		super(s, e);
	}

}
