package kim.chunghyeon.webapp.common.api.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@Builder
public class ApiErrorDto {
    private final ZonedDateTime timestamp;
    private final int status;
    private final String error;
    private final String message;
}
