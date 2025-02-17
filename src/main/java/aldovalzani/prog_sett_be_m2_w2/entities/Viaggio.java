package aldovalzani.prog_sett_be_m2_w2.entities;

import aldovalzani.prog_sett_be_m2_w2.exceptions.BadRequestException;
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
    @Column(name = "data_partenza")
    private LocalDate dataPartenza;
    @Setter(AccessLevel.NONE)
    private String stato;

    public Viaggio(LocalDate dataPartenza, String destinazione) {
        this.dataPartenza = dataPartenza;
        this.destinazione = destinazione;
        this.stato = "In programma";
    }

    public void setStato(String stato) {
        if (stato.equals("Completato") || stato.equals("In programma")) {
            this.stato = stato;
        } else {
            throw new BadRequestException("Stato non valido. Deve essere 'Completato' o 'In Programma'");
        }
    }
}
