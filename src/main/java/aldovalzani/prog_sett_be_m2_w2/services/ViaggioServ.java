package aldovalzani.prog_sett_be_m2_w2.services;

import aldovalzani.prog_sett_be_m2_w2.dto.NewViaggioDTO;
import aldovalzani.prog_sett_be_m2_w2.entities.Viaggio;
import aldovalzani.prog_sett_be_m2_w2.exceptions.BadRequestException;
import aldovalzani.prog_sett_be_m2_w2.repositories.ViaggioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViaggioServ {
    @Autowired
    ViaggioRepo viaggioRepo;

    public Viaggio saveViaggio(NewViaggioDTO body) {
        viaggioRepo.findByDestinazioneAndDataPartenza(body.destinazione()
                , body.data_partenza()).ifPresent(
                viaggio -> {
                    throw new BadRequestException("Un viaggio per la destinazione: " + body.destinazione() +
                            " con la data: " + body.data_partenza() + " esiste già.");
                }
        );
        Viaggio newViaggio = new Viaggio(body.data_partenza(), body.destinazione());
        return this.viaggioRepo.save(newViaggio);
    }
}
