package aldovalzani.prog_sett_be_m2_w2.controllers;

import aldovalzani.prog_sett_be_m2_w2.dto.NewPrenotazioneDTO;
import aldovalzani.prog_sett_be_m2_w2.entities.Prenotazione;
import aldovalzani.prog_sett_be_m2_w2.exceptions.BadRequestException;
import aldovalzani.prog_sett_be_m2_w2.services.PrenotazioneServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

/*
    http://localhost:3001/prenotazioni
*/
@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {
    @Autowired
    private PrenotazioneServ prenotazioneServ;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione savePrenotazione(@RequestBody @Validated NewPrenotazioneDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String msg = validationResult.getAllErrors().stream().
                    map(objectError -> objectError.getDefaultMessage()).collect(Collectors.joining(", "));
            throw new BadRequestException("Ci sono errori nel payload! " + msg);
        }
        return this.prenotazioneServ.savePrenotazione(body);
    }

    @GetMapping
    public Page<Prenotazione> findAllPrenotazioni(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size) {
        return this.prenotazioneServ.findAllPrenotazioni(page, size);
    }

    @GetMapping("/{id_prenotazione}")
    public Prenotazione findDPrenotazioneById(@PathVariable long id_prenotazione) {
        return this.prenotazioneServ.findDPrenotazioneById(id_prenotazione);
    }

    @PutMapping("/{id_prenotazione}")
    public Prenotazione findPrenotazioneAndUp(@PathVariable long id_prenotazione,
                                              @RequestBody @Validated NewPrenotazioneDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }
        return this.prenotazioneServ.findPrenotazioneAndUp(id_prenotazione, body);
    }
}
