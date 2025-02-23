package com.isaac.hexagonal.adapters.exception.handler.response;

/**
 * Especifico para validacao de API
 */
public record SubErroResponse(
        String objeto,
        String mensagem,
        String campo,
        Object valorRejeitado
) {
    public SubErroResponse(String objeto, String mensagem) {
        this(objeto, mensagem, null, null);
    }
}