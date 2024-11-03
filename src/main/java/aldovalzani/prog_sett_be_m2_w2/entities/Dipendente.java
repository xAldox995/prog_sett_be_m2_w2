package aldovalzani.prog_sett_be_m2_w2.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dipendenti")
@Getter
@Setter
@NoArgsConstructor
public class Dipendente {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private long id;
    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String avatar_url;

    public Dipendente(String cognome, String email, String nome, String username) {
        this.cognome = cognome;
        this.email = email;
        this.nome = nome;
        this.username = username;
        this.avatar_url = "https://ui-avatars.com/api/?name=" + getNome() + "+" + getCognome();
    }
}
