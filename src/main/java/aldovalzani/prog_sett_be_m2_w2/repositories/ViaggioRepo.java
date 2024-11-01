package aldovalzani.prog_sett_be_m2_w2.repositories;

import aldovalzani.prog_sett_be_m2_w2.entities.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViaggioRepo extends JpaRepository<Viaggio, Long> {
}
