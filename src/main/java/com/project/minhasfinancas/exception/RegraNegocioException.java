package com.project.minhasfinancas.exception;

public class RegraNegocioException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    String msg;

    public RegraNegocioException(String msg) {
        super(msg);
    }
}