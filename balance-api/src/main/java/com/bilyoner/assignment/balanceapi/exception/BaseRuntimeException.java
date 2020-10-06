package com.bilyoner.assignment.balanceapi.exception;

public abstract class BaseRuntimeException extends RuntimeException {

	private final transient HttpAwareErrorCode errorCode;
    private String message;
    private transient Object[] params;

    public BaseRuntimeException(HttpAwareErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public BaseRuntimeException(HttpAwareErrorCode errorCode, Throwable t) {
        super(t);
        this.errorCode = errorCode;
    }

    public BaseRuntimeException(HttpAwareErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public BaseRuntimeException(HttpAwareErrorCode errorCode, Object... params) {
        this.errorCode = errorCode;
        this.params = params;
    }

    public BaseRuntimeException(HttpAwareErrorCode errorCode, Throwable t, Object... params) {
        super(t);
        this.errorCode = errorCode;
        this.params = params;
    }

    public HttpAwareErrorCode getErrorCode() {
        return errorCode;
    }

    public void setMessage(String message) {
		this.message = message;
	}

	@Override
    public String getMessage() {
        return message;
    }

    public Object[] getParams() {
        return params;
    }
}
