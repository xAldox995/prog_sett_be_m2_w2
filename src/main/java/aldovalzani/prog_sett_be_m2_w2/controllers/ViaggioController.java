package aldovalzani.prog_sett_be_m2_w2.controllers;

import aldovalzani.prog_sett_be_m2_w2.dto.NewViaggioDTO;
import aldovalzani.prog_sett_be_m2_w2.entities.Viaggio;
import aldovalzani.prog_sett_be_m2_w2.exceptions.BadRequestException;
import aldovalzani.prog_sett_be_m2_w2.services.ViaggioServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

/*
    http://localhost:3001/viaggi
 */

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {
    @Autowired
    private ViaggioServ viaggioServ;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggio saveViaggio(@RequestBody @Validated NewViaggioDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream().
                    map(objectError -> objectError.getDefaultMessage()).collect(Collectors.joining(" ,"));
            throw new BadRequestException("Ci sono errori nel payload! " + message);
        }
        return this.viaggioServ.saveViaggio(body);
    }

    @GetMapping
    public Page<Viaggio> findAllViaggi(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "50") int size) {
        return this.viaggioServ.findAllViaggi(page, size);
    }

    @GetMapping("/{id_viaggio}")
    public Viaggio findViaggioById(@PathVariable long id_viaggio) {
        return this.viaggioServ.findViaggioById(id_viaggio);
    }

    @PutMapping("/{id_viaggio}")
    public Viaggio findViaggioNyIdAndUp(@PathVariable long id_viaggio,
                                        @RequestBody @Validated NewViaggioDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }
        return this.viaggioServ.findViaggioByIdAndUp(id_viaggio, body);
    }

    @DeleteMapping("/{id_viaggio}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findViaggioByIdAndDel(@PathVariable long id_viaggio) {
        this.viaggioServ.findViaggioByIdAndDel(id_viaggio);
    }

}
