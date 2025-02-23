package com.isaac.hexagonal.application.exception;

/**
 * Exceção lançada quando ocorre um erro no servidor.
 */
public class ServerException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final String status;
    private final String errorMsg;

    /**
     * Constrói uma nova ServerException com o status e a mensagem especificados.
     *
     * @param status o código de status
     * @param errorMsg a mensagem de erro
     */
    public ServerException(String status, String errorMsg) {
        super(errorMsg);
        this.status = status;
        this.errorMsg = errorMsg;
    }

    /**
     * Constrói uma nova ServerException com o status, a mensagem e a causa especificados.
     *
     * @param status o código de status
     * @param errorMsg a mensagem de erro
     * @param cause a causa da exceção
     */
    public ServerException(String status, String errorMsg, Throwable cause) {
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