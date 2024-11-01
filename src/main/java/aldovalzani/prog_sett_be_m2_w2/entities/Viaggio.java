package aldovalzani.prog_sett_be_m2_w2.entities;

import jakarta.persistence.*;
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
    private String stato;
    @ManyToOne
    @JoinColumn(name = "id_dipendente")
    private Dipendente dipendente;

    public Viaggio(LocalDate data_partenza, String destinazione, Dipendente dipendente, String stato) {
        this.data_partenza = data_partenza;
        this.destinazione = destinazione;
        this.dipendente = dipendente;
        this.stato = stato;
    }
}
