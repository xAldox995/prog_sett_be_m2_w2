package aldovalzani.prog_sett_be_m2_w2.services;

import aldovalzani.prog_sett_be_m2_w2.dto.NewPrenotazioneDTO;
import aldovalzani.prog_sett_be_m2_w2.entities.Dipendente;
import aldovalzani.prog_sett_be_m2_w2.entities.Prenotazione;
import aldovalzani.prog_sett_be_m2_w2.entities.Viaggio;
import aldovalzani.prog_sett_be_m2_w2.exceptions.BadRequestException;
import aldovalzani.prog_sett_be_m2_w2.exceptions.NotFoundException;
import aldovalzani.prog_sett_be_m2_w2.repositories.PrenotazioneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PrenotazioneServ {
    @Autowired
    private PrenotazioneRepo prenotazioneRepo;
    @Autowired
    private ViaggioServ viaggioServ;
    @Autowired
    private DipendenteServ dipendenteServ;


    public Prenotazione savePrenotazione(NewPrenotazioneDTO body) {
        Dipendente dipFound = dipendenteServ.findDipendenteById(body.id_dipendente());
        Viaggio viaggioFound = viaggioServ.findViaggioById(body.id_viaggio());

        prenotazioneRepo.findByDipendente_EmailAndViaggio_DataPartenza(dipFound.getEmail(), viaggioFound.getDataPartenza())
                .ifPresent(prenotazione -> {
                    throw new BadRequestException("Il dipendente con email: " + dipFound.getEmail()
                            + " ha già una prenotazione in data: " + viaggioFound.getDataPartenza());
                });
        Prenotazione newPrenotazione = new Prenotazione(body.data_richiesta(), dipFound, body.note(), viaggioFound);
        return prenotazioneRepo.save(newPrenotazione);
    }

    public Page<Prenotazione> findAllPrenotazioni(int page, int size) {
        if (size > 10) size = 10;
        Pageable pageable = PageRequest.of(page, size);
        return this.prenotazioneRepo.findAll(pageable);
    }

    public Prenotazione findDPrenotazioneById(long id_prenotazione) {
        return this.prenotazioneRepo.findById(id_prenotazione).
                orElseThrow(() -> new NotFoundException(id_prenotazione));
    }
}
