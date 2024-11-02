package aldovalzani.prog_sett_be_m2_w2.repositories;

import aldovalzani.prog_sett_be_m2_w2.entities.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ViaggioRepo extends JpaRepository<Viaggio, Long> {
    Optional<Viaggio> findByDestinazioneAndDataPartenza(String destinazione, LocalDate dataPartenza);
}
