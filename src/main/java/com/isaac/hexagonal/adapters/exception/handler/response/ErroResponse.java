package com.isaac.hexagonal.adapters.exception.handler.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Resposta de erro da API.
 */
@JsonTypeName("apierro")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public record ErroResponse(
        @JsonProperty("timestamp")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
        LocalDateTime timestamp,
        @JsonProperty("status")
        HttpStatus status,
        @JsonProperty("codigoErro")
        Integer codigoErro,
        @JsonProperty("mensagem")
        String mensagem,
        @JsonProperty("mensagemDetalhada")
        String mensagemDetalhada,
        @JsonProperty("subErros")
        List<SubErroResponse> subErros
) {
    public ErroResponse {
        subErros = subErros != null ? new ArrayList<>(subErros) : new ArrayList<>();
    }

    /**
     * Adiciona um suberro à lista de suberros.
     *
     * @param subError o suberro a ser adicionado
     */
    public void addSubErro(SubErroResponse subError) {
        subErros.add(subError);
    }

    /**
     * Adiciona um erro de validação à lista de suberros.
     *
     * @param object o objeto que causou o erro
     * @param field o campo que causou o erro
     * @param rejectedValue o valor rejeitado
     * @param message a mensagem de erro
     */
    public void addValidationError(String object, String field, Object rejectedValue, String message) {
        addSubErro(new SubErroResponse(object, field, message, rejectedValue));
    }

    /**
     * Adiciona um erro de validação à lista de suberros.
     *
     * @param object o objeto que causou o erro
     * @param message a mensagem de erro
     */
    public void addValidationError(String object, String message) {
        addSubErro(new SubErroResponse(object, message));
    }

    /**
     * Adiciona um erro de validação à lista de suberros.
     *
     * @param fieldError o erro de campo
     */
    public void addValidationError(FieldError fieldError) {
        this.addValidationError(
                fieldError.getObjectName(),
                fieldError.getField(),
                fieldError.getRejectedValue(),
                fieldError.getDefaultMessage());
    }

    /**
     * Adiciona uma lista de erros de validação à lista de suberros.
     *
     * @param fieldErrors a lista de erros de campo
     */
    public void addValidationErrors(List<FieldError> fieldErrors) {
        fieldErrors.forEach(this::addValidationError);
    }

    /**
     * Adiciona um erro de validação à lista de suberros.
     *
     * @param objectError o erro de objeto
     */
    public void addValidationError(ObjectError objectError) {
        this.addValidationError(
                objectError.getObjectName(),
                objectError.getDefaultMessage());
    }

    /**
     * Adiciona uma lista de erros de validação à lista de suberros.
     *
     * @param globalErrors a lista de erros globais
     */
    public void addValidationError(List<ObjectError> globalErrors) {
        globalErrors.forEach(this::addValidationError);
    }
}