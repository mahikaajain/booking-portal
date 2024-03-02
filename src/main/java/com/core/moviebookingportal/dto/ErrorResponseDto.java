package com.core.moviebookingportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;

@Data
public class ErrorResponseDto {

        public static final String FAILED = "FAILED";
        private String status;
        private String reason;

        public ErrorResponseDto(final String reason) {
            this.status = FAILED;
            this.reason = reason;
        }

}
