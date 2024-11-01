package aldovalzani.prog_sett_be_m2_w2.dto;

import java.time.LocalDateTime;

public record ErrorResponseDTO(String message, LocalDateTime timestamp) {
}
