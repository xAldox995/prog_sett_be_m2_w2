package aldovalzani.prog_sett_be_m2_w2.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record NewViaggioDTO(
        @NotNull(message = "La destinazione è obbligatoria")
        @NotEmpty(message = "Scrivi qualcosa")
        String destinazione,
        @NotNull(message = "La data del viaggio è obbligatoria")
        @Future(message = "La data del viaggio deve essere futura")
        LocalDate data_partenza
) {
}
