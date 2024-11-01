package aldovalzani.prog_sett_be_m2_w2.services;

import aldovalzani.prog_sett_be_m2_w2.dto.NewDipendenteDTO;
import aldovalzani.prog_sett_be_m2_w2.entities.Dipendente;
import aldovalzani.prog_sett_be_m2_w2.exceptions.BadRequestException;
import aldovalzani.prog_sett_be_m2_w2.repositories.DipendenteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DipendenteServ {
    @Autowired
    private DipendenteRepo dipendenteRepo;

    public Dipendente saveDipendente(NewDipendenteDTO body) {
        this.dipendenteRepo.findByEmail(body.email()).ifPresent(
                dipendente -> {
                    throw new BadRequestException("Email" + body.email() + " già in uso");
                }
        );
        Dipendente newDipendente = new Dipendente(body.cognome(), body.email(), body.nome(), body.username());
        return this.dipendenteRepo.save(newDipendente);
    }

    public Page<Dipendente> findAllDipendenti(int page, int size) {
        if (size > 30) size = 30;
        Pageable pageable = PageRequest.of(page, size);
        return this.dipendenteRepo.findAll(pageable);
    }

}
