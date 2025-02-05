package org.artie4.chinooksrv.controller.advice

import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.NoHandlerFoundException
import java.time.LocalDateTime

@RestControllerAdvice
class GlobalExceptionHandler {
    // Handle validation errors (e.g., @Valid fails)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationExceptions(
        ex: MethodArgumentNotValidException,
        request: WebRequest,
    ): ResponseEntity<ErrorResponse> {
        val errors = ex.bindingResult.fieldErrors.joinToString(", ") { it.defaultMessage ?: "Invalid field" }
        val errorResponse =
            ErrorResponse(
                status = HttpStatus.BAD_REQUEST.value(),
                error = "Validation Failed",
                message = errors,
                path = request.getDescription(false).replace("uri=", ""),
            )
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    // Handle constraint violations (e.g., @Validated fails)
    @ExceptionHandler(ConstraintViolationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleConstraintViolationExceptions(
        ex: ConstraintViolationException,
        request: WebRequest,
    ): ResponseEntity<ErrorResponse> {
        val errors = ex.constraintViolations.joinToString(", ") { it.message }
        val errorResponse =
            ErrorResponse(
                status = HttpStatus.BAD_REQUEST.value(),
                error = "Constraint Violation",
                message = errors,
                path = request.getDescription(false).replace("uri=", ""),
            )
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    // Handle 404 - Resource Not Found
    @ExceptionHandler(NoHandlerFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNoHandlerFoundException(
        ex: NoHandlerFoundException,
        request: WebRequest,
    ): ResponseEntity<ErrorResponse> {
        val errorResponse =
            ErrorResponse(
                status = HttpStatus.NOT_FOUND.value(),
                error = "Resource Not Found",
                message = "The requested resource was not found.",
                path = request.getDescription(false).replace("uri=", ""),
            )
        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }

    // Handle generic exceptions (500 - Internal Server Error)
    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleGenericExceptions(
        ex: Exception,
        request: WebRequest,
    ): ResponseEntity<ErrorResponse> {
        val errorResponse =
            ErrorResponse(
                status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                error = "Internal Server Error",
                message = "An unexpected error occurred.",
                path = request.getDescription(false).replace("uri=", ""),
            )
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}

// Data class for custom error response
data class ErrorResponse(
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val status: Int,
    val error: String,
    val message: String,
    val path: String,
)
