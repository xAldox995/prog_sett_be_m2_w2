package aldovalzani.prog_sett_be_m2_w2.controllers;

import aldovalzani.prog_sett_be_m2_w2.dto.NewDipendenteDTO;
import aldovalzani.prog_sett_be_m2_w2.entities.Dipendente;
import aldovalzani.prog_sett_be_m2_w2.exceptions.BadRequestException;
import aldovalzani.prog_sett_be_m2_w2.exceptions.NotFoundException;
import aldovalzani.prog_sett_be_m2_w2.services.DipendenteServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

/*
    http://localhost:3001/dipendenti
 */
@RestController
@RequestMapping("/dipendenti")

public class DipendenteController {
    @Autowired
    private DipendenteServ dipendenteServ;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente saveDipendente(@RequestBody @Validated NewDipendenteDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(" ,"));
            throw new BadRequestException("Ci sono errori nel payload! " + message);
        }
        return this.dipendenteServ.saveDipendente(body);
    }

    @GetMapping
    public Page<Dipendente> findAllDipendti(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "30") int size) {
        return this.dipendenteServ.findAllDipendenti(page, size);
    }

    @GetMapping("/{id_dipendente}")
    public Dipendente findDipendenteById(@PathVariable long id_dipendente) {
        return this.dipendenteServ.findDipendenteById(id_dipendente);
    }

    @PutMapping("/{id_dipendente}")
    public Dipendente findDipendenteByIdAndUp(@PathVariable long id_dipendente,
                                              @RequestBody @Validated NewDipendenteDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String msg = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(" ,"));
            throw new BadRequestException("Ci sono degli errori nel payload! " + msg);
        }
        Dipendente dipendeteUp = this.dipendenteServ.findDipendenteByIdAndUp(id_dipendente, body);
        if (dipendeteUp == null) throw new NotFoundException(id_dipendente);
        return dipendeteUp;
    }

}
