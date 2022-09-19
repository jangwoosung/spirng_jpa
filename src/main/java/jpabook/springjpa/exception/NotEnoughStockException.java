/*******************************************************************************
 * created by        : jws
 * creation date     : 2022-09-19
 *
 * Copyright (c) 2021 Samsung SDS.
 * All rights reserved.
 *******************************************************************************/

package jpabook.springjpa.exception;

/**
 * Class description
 *
 * @author jws
 * @since 2022. 9. 19
 * @version 1.0
*/

public class NotEnoughStockException extends RuntimeException {

	/**
	 *
	 */
	public NotEnoughStockException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public NotEnoughStockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public NotEnoughStockException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public NotEnoughStockException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public NotEnoughStockException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
