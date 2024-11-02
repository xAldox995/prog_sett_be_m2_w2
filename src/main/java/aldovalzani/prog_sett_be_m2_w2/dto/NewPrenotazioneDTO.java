package aldovalzani.prog_sett_be_m2_w2.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record NewPrenotazioneDTO(
        @NotNull(message = "Id del viaggio è obbligatorio")
        long id_viaggio,
        @NotNull(message = "Id del dipendente è obbligatorio")
        long id_dipendente,
        @NotNull(message = "Inserire una data per la rechiesta")
        @Future(message = "La data della richiesta deve essere futura")
        LocalDate data_richiesta,
        @Size(max = 200, message = "Le note possono avere fino a 200 carattere")
        String note) {
}
