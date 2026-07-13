package com.application.user_service.dto;

import java.net.URI;
import java.time.LocalDateTime;

public record ErrorResponse(String statusCode, LocalDateTime timestamp, String uri,String message) {

}
