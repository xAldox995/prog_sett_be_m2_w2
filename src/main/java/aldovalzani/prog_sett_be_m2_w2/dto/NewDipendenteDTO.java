package aldovalzani.prog_sett_be_m2_w2.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewDipendenteDTO(
        @NotNull(message = "username obbligatorio")
        @NotEmpty(message = "Scrivi qualcosa")
        String username,
        @NotNull(message = "nome obbligatorio")
        @NotEmpty(message = "Scrivi qualcosa")
        String nome,
        @NotNull(message = "cognome obbligatorio")
        @NotEmpty(message = "Scrivi qualcosa")
        String cognome,
        @NotNull(message = "email obbligatorio")
        @NotEmpty(message = "Scrivi qualcosa")
        @Email(message = "Inserisci un email valida")
        String email
) {
}
