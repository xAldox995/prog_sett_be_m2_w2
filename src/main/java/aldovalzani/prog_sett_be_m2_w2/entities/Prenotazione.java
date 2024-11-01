package aldovalzani.prog_sett_be_m2_w2.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "prenotazioni")
@Getter
@Setter
@NoArgsConstructor
public class Prenotazione {
    @ManyToOne
    @JoinColumn(name = "id_viaggio")
    Viaggio viaggio;
    @ManyToOne
    @JoinColumn(name = "id_dipendente")
    Dipendente dipendente;
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private long id;
    private LocalDate data_richiesta;
    private String note;

    public Prenotazione(LocalDate data_richiesta, Dipendente dipendente, String note, Viaggio viaggio) {
        this.data_richiesta = data_richiesta;
        this.dipendente = dipendente;
        this.note = note;
        this.viaggio = viaggio;
    }
}
