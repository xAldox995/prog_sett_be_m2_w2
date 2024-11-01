package aldovalzani.prog_sett_be_m2_w2.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "viaggi")
@Getter
@Setter
@NoArgsConstructor
public class Viaggio {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private long id;
    private String destinazione;
    private LocalDate data_partenza;
    @Setter(AccessLevel.NONE)
    private String stato;

    public Viaggio(LocalDate data_partenza, String destinazione) {
        this.data_partenza = data_partenza;
        this.destinazione = destinazione;
        this.stato = "In programma";
    }

    public void setStato() {
        this.stato = "Completato";
    }
}
