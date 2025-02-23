package com.isaac.hexagonal.application.exception;

/**
 * Exceção lançada quando uma regra de negócio é violada.
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final String errorCode;
    private final String errorMsg;

    /**
     * Constrói uma nova BusinessException com o código de erro e a mensagem especificados.
     *
     * @param errorCode o código de erro
     * @param errorMsg a mensagem de erro
     */
    public BusinessException(String errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    /**
     * Constrói uma nova BusinessException com o código de erro, a mensagem e a causa especificados.
     *
     * @param errorCode o código de erro
     * @param errorMsg a mensagem de erro
     * @param cause a causa da exceção
     */
    public BusinessException(String errorCode, String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    /**
     * Retorna o código de erro.
     *
     * @return o código de erro
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Retorna a mensagem de erro.
     *
     * @return a mensagem de erro
     */
    public String getErrorMsg() {
        return errorMsg;
    }
}