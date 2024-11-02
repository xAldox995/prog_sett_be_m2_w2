package aldovalzani.prog_sett_be_m2_w2.repositories;

import aldovalzani.prog_sett_be_m2_w2.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PrenotazioneRepo extends JpaRepository<Prenotazione, Long> {
    Optional<Prenotazione> findByDipendente_EmailAndViaggio_DataPartenza(String email, LocalDate dataPartenza);
}
