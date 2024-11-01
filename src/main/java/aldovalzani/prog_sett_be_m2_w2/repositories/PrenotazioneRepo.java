package aldovalzani.prog_sett_be_m2_w2.repositories;

import aldovalzani.prog_sett_be_m2_w2.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrenotazioneRepo extends JpaRepository<Prenotazione, Long> {
}
