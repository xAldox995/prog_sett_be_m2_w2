package aldovalzani.prog_sett_be_m2_w2.services;

import aldovalzani.prog_sett_be_m2_w2.dto.NewViaggioDTO;
import aldovalzani.prog_sett_be_m2_w2.entities.Viaggio;
import aldovalzani.prog_sett_be_m2_w2.exceptions.BadRequestException;
import aldovalzani.prog_sett_be_m2_w2.exceptions.NotFoundException;
import aldovalzani.prog_sett_be_m2_w2.repositories.ViaggioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<Viaggio> findAllViaggi(int page, int size) {
        if (size > 50) size = 50;
        Pageable pageable = PageRequest.of(page, size);
        return this.viaggioRepo.findAll(pageable);
    }

    public Viaggio findViaggioById(long id_viaggio) {
        return this.viaggioRepo.findById(id_viaggio).
                orElseThrow(() -> new NotFoundException(id_viaggio));
    }

    public Viaggio findViaggioByIdAndUp(long id_viaggio, NewViaggioDTO body) {
        Viaggio viagFound = this.findViaggioById(id_viaggio);
        if (!viagFound.getDestinazione().equals(body.destinazione()) ||
                !viagFound.getDataPartenza().equals(body.data_partenza())) {
            this.viaggioRepo.findByDestinazioneAndDataPartenza(body.destinazione(), body.data_partenza())
                    .ifPresent(viaggio -> {
                        throw new BadRequestException("Viaggio con destinazione: " + body.destinazione() +
                                " e con data: " + body.data_partenza() + " è già esistente");
                    });
        }
        viagFound.setDataPartenza(body.data_partenza());
        viagFound.setDestinazione(body.destinazione());
        return viaggioRepo.save(viagFound);
    }
}
