package aldovalzani.prog_sett_be_m2_w2.controllers;

import aldovalzani.prog_sett_be_m2_w2.dto.NewDipendenteDTO;
import aldovalzani.prog_sett_be_m2_w2.entities.Dipendente;
import aldovalzani.prog_sett_be_m2_w2.services.DipendenteServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DipendenteController {
    @Autowired
    private DipendenteServ dipendenteServ;
    /*
        http://localhost:3001/dipendenti
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente saveDipendente (@RequestBody@Validated NewDipendenteDTO body, BindingResult validationResult)
}
