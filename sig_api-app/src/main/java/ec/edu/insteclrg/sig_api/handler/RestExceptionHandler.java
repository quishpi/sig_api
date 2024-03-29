package ec.edu.insteclrg.sig_api.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ec.edu.insteclrg.sig_api.common.exception.ApiException;
import ec.edu.insteclrg.sig_api.common.exception.ResourceNotFoundException;
import ec.edu.insteclrg.sig_api.dto.ApiResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

//import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.hibernate.exception.ConstraintViolationException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ ResourceNotFoundException.class })
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
		ApiError apiError = new ApiError(NOT_FOUND);
		apiError.setMessage(ex.getMessage());
		return buildResponseEntity(apiError);
	}

	@ExceptionHandler({ ApiException.class })
	public ResponseEntity<?> handleApiException(ApiException ex, HttpServletRequest request) {
		return buildResponseEntity(new ApiError(INTERNAL_SERVER_ERROR, ex.getMessage(), ex));
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		String error = ex.getParameterName() + " parameter is missing";
		return buildResponseEntity(new ApiError(BAD_REQUEST, error, ex));
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		StringBuilder builder = new StringBuilder();
		builder.append(ex.getContentType());
		builder.append(" media type is not supported. Supported media types are ");
		ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
		return buildResponseEntity(
				new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, builder.substring(0, builder.length() - 2), ex));
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		ApiError apiError = new ApiError(BAD_REQUEST);
		apiError.setMessage("Validation error");
		apiError.addValidationErrors(ex.getBindingResult().getFieldErrors());
		apiError.addValidationError(ex.getBindingResult().getGlobalErrors());
		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
	protected ResponseEntity<Object> handleConstraintViolation(jakarta.validation.ConstraintViolationException ex) {
		ApiError apiError = new ApiError(BAD_REQUEST);
		apiError.setMessage("Validation error");
		apiError.addValidationErrors(ex.getConstraintViolations());
		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex,
			WebRequest request) {
		if (ex.getCause() instanceof ConstraintViolationException) {
			return buildResponseEntity(new ApiError(HttpStatus.CONFLICT, "Database error", ex.getCause()));
		}
		return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex));
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		ServletWebRequest servletWebRequest = (ServletWebRequest) request;
		logger.info(String.format("{} to {}", servletWebRequest.getHttpMethod(),
				servletWebRequest.getRequest().getServletPath()));
		String error = "Malformed JSON request";
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		String error = "Error writing JSON output";
		return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, error, ex));
	}

	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
		return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, ex));
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			WebRequest request) {
		ApiError apiError = new ApiError(BAD_REQUEST);
		apiError.setMessage(String.format("The parameter '%s' of value '%s' could not be converted to type '%s'",
				ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName()));
		apiError.setDebugMessage(ex.getMessage());
		return buildResponseEntity(apiError);
	}

	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		ApiResponseDTO<ApiError> error = new ApiResponseDTO<>(false, apiError);
		return new ResponseEntity<>(error, apiError.getStatus());
	}

}