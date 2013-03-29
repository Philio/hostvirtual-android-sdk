package org.vr.cloud;

public class ApiException extends Exception {

	private static final long serialVersionUID = -9134387964139932153L;

	public ApiException(String detailMessage) {
		super(detailMessage);
	}
	
}