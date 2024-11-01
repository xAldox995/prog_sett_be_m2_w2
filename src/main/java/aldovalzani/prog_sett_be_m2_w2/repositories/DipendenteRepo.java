package aldovalzani.prog_sett_be_m2_w2.repositories;

import aldovalzani.prog_sett_be_m2_w2.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DipendenteRepo extends JpaRepository<Dipendente, Long> {
    Optional<Dipendente> findByEmail(String email);
}
