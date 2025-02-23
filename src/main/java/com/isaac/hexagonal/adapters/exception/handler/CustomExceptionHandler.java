package com.isaac.hexagonal.adapters.exception.handler;

import com.isaac.hexagonal.adapters.exception.handler.response.ErroResponse;
import com.isaac.hexagonal.application.exception.BusinessException;
import com.isaac.hexagonal.application.exception.IntegrationException;
import com.isaac.hexagonal.application.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

/**
 * Classe responsável por tratar exceções em toda a aplicação e retornar respostas apropriadas.
 */
@Order(Ordered.LOWEST_PRECEDENCE)
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler  {

    private static final Logger LOGGER_TECNICO = LoggerFactory.getLogger(CustomExceptionHandler.class);

    /**
     * Trata exceções de validação de argumentos de método.
     *
     * @param exception      Exceção capturada.
     * @param httpHeaders    Cabeçalhos HTTP.
     * @param httpStatusCode Status HTTP.
     * @param webRequest     Requisição web.
     * @return ResponseEntity com detalhes do erro.
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders httpHeaders,
            HttpStatus httpStatusCode,
            WebRequest webRequest) {
        LOGGER_TECNICO.warn("Tratando exceção de validação de argumentos de método.");
        ErroResponse erroResponse = buildValidationErrorResponse(exception);
        LOGGER_TECNICO.debug("Erro de validação: {}", erroResponse);
        return buildResponseEntity(erroResponse);
    }

    /**
     * Trata exceções de negócio.
     *
     * @param businessException Exceção capturada.
     * @return ResponseEntity com detalhes do erro.
     */
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<Object> handleBusinessException(BusinessException businessException) {
        LOGGER_TECNICO.warn("Erro de negócio capturado: {}", businessException.getMessage(), businessException);
        ErroResponse erroResponse = buildApiErroResponse(UNPROCESSABLE_ENTITY, "Ocorreu um erro de negócio: " + businessException.getMessage());
        LOGGER_TECNICO.debug("Erro de negócio: {}", erroResponse);
        return buildResponseEntity(erroResponse);
    }

    /**
     * Trata exceções de validação.
     *
     * @param validationException Exceção capturada.
     * @return ResponseEntity com detalhes do erro.
     */
    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<Object> handleValidationException(ValidationException validationException) {
        LOGGER_TECNICO.warn("Tratando exceção de validação.");
        return handleValidationException(validationException, "Ocorreu um erro de validação: " + validationException.getMessage());
    }

    /**
     * Método genérico para tratar exceções de validação.
     *
     * @param exception Exceção capturada.
     * @param message   Mensagem de erro.
     * @return ResponseEntity com detalhes do erro.
     */
    private ResponseEntity<Object> handleValidationException(Exception exception, String message) {
        LOGGER_TECNICO.warn("Erro de validação capturado: {}", exception.getMessage(), exception);
        ErroResponse erroResponse = buildApiErroResponse(BAD_REQUEST, message);
        LOGGER_TECNICO.debug("Erro de validação: {}", erroResponse);
        return buildResponseEntity(erroResponse);
    }

    /**
     * Trata exceções de integração.
     *
     * @param integrationException Exceção capturada.
     * @return ResponseEntity com detalhes do erro.
     */
    @ExceptionHandler(IntegrationException.class)
    protected ResponseEntity<Object> handleIntegrationException(IntegrationException integrationException) {
        LOGGER_TECNICO.error("Erro de integração capturado: {}", integrationException.getErrorMsg(), integrationException);
        ErroResponse erroResponse = buildApiErroResponse(INTERNAL_SERVER_ERROR, "Ocorreu um erro de integração: " + integrationException.getErrorMsg());
        LOGGER_TECNICO.debug("Erro de integração: {}", erroResponse);
        return buildResponseEntity(erroResponse);
    }

    /**
     * Trata todas as outras exceções.
     *
     * @param genericException Exceção capturada.
     * @return ResponseEntity com detalhes do erro.
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleAllExceptions(Exception genericException) {
        LOGGER_TECNICO.error("Erro interno do servidor capturado: {}", genericException.getMessage(), genericException);
        ErroResponse erroResponse = buildApiErroResponse(INTERNAL_SERVER_ERROR, "Ocorreu um erro interno no servidor: " + genericException.getMessage());
        LOGGER_TECNICO.debug("Erro interno do servidor: {}", erroResponse);
        return buildResponseEntity(erroResponse);
    }

    /**
     * Constrói uma ResponseEntity com a resposta de erro API.
     *
     * @param erroResponse Resposta de erro API.
     * @return ResponseEntity com detalhes do erro.
     */
    private ResponseEntity<Object> buildResponseEntity(ErroResponse erroResponse) {
        LOGGER_TECNICO.info("Construindo ResponseEntity com status: {}", erroResponse.status());
        return new ResponseEntity<>(erroResponse, erroResponse.status());
    }

    /**
     * Constrói uma resposta de erro de validação.
     *
     * @param methodArgumentNotValidException Exceção de validação de argumento de método.
     * @return ApiErroResponse com detalhes do erro.
     */
    private ErroResponse buildValidationErrorResponse(MethodArgumentNotValidException methodArgumentNotValidException) {
        LOGGER_TECNICO.info("Construindo resposta de erro de validação.");
        ErroResponse erroResponse = buildApiErroResponse(BAD_REQUEST, "Ocorreu um erro de validação nos dados fornecidos.");
        erroResponse.addValidationErrors(methodArgumentNotValidException.getBindingResult().getFieldErrors());
        erroResponse.addValidationError(methodArgumentNotValidException.getBindingResult().getGlobalErrors());
        LOGGER_TECNICO.debug("Resposta de erro de validação: {}", erroResponse);
        return erroResponse;
    }

    /**
     * Constrói uma resposta de erro API.
     *
     * @param httpStatus  Status HTTP.
     * @param errorMessage Mensagem de erro.
     * @return ApiErroResponse com detalhes do erro.
     */
    private ErroResponse buildApiErroResponse(HttpStatus httpStatus, String errorMessage) {
        LOGGER_TECNICO.info("Construindo resposta de erro API com status: {} e mensagem: {}", httpStatus, errorMessage);
        return new ErroResponse(LocalDateTime.now(), httpStatus, httpStatus.value(), errorMessage, null, new ArrayList<>());
    }
}