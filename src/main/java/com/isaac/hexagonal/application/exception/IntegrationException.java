package com.isaac.hexagonal.application.exception;

/**
 * Exceção lançada quando ocorre um erro de integração.
 */
public class IntegrationException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final String status;
    private final String errorMsg;

    /**
     * Constrói uma nova IntegrationException com o status e a mensagem especificados.
     *
     * @param status o código de status
     * @param errorMsg a mensagem de erro
     */
    public IntegrationException(String status, String errorMsg) {
        super(errorMsg);
        this.status = status;
        this.errorMsg = errorMsg;
    }

    /**
     * Constrói uma nova IntegrationException com o status, a mensagem e a causa especificados.
     *
     * @param status o código de status
     * @param errorMsg a mensagem de erro
     * @param cause a causa da exceção
     */
    public IntegrationException(String status, String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.status = status;
        this.errorMsg = errorMsg;
    }

    /**
     * Retorna o código de status.
     *
     * @return o código de status
     */
    public String getStatus() {
        return status;
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