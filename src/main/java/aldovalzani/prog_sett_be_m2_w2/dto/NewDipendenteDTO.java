package aldovalzani.prog_sett_be_m2_w2.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record NewDipendenteDTO(
        @NotEmpty(message = "username obbligatoria")
        String username,
        @NotEmpty(message = "nome obbligatoria")
        String nome,
        @NotEmpty(message = "cognome obbligatoria")
        String cognome,
        @NotEmpty(message = "email obbligatoria")
        @Email(message = "Inserisci un email valida")
        String email
) {
}
