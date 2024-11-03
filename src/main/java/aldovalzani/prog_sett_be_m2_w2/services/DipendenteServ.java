package aldovalzani.prog_sett_be_m2_w2.services;

import aldovalzani.prog_sett_be_m2_w2.dto.NewDipendenteDTO;
import aldovalzani.prog_sett_be_m2_w2.entities.Dipendente;
import aldovalzani.prog_sett_be_m2_w2.exceptions.BadRequestException;
import aldovalzani.prog_sett_be_m2_w2.exceptions.NotFoundException;
import aldovalzani.prog_sett_be_m2_w2.repositories.DipendenteRepo;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class DipendenteServ {
    @Autowired
    private DipendenteRepo dipendenteRepo;

    @Autowired
    private Cloudinary cloudinaryUp;

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

    public Dipendente findDipendenteById(long id_dipendente) {
        return this.dipendenteRepo.findById(id_dipendente).
                orElseThrow(() -> new NotFoundException(id_dipendente));
    }

    public Dipendente findDipendenteByIdAndUp(long id_dipendente, NewDipendenteDTO body) {
        Dipendente dipFound = this.findDipendenteById(id_dipendente);
        if (!dipFound.getEmail().equals(body.email())) {
            this.dipendenteRepo.findByEmail(body.email()).ifPresent(
                    dipendente -> {
                        throw new BadRequestException("Email " + body.email() + " già in uso");
                    }
            );
        }
        dipFound.setCognome(body.cognome());
        dipFound.setEmail(body.email());
        dipFound.setNome(body.nome());
        dipFound.setUsername(body.username());
        System.out.println("Aggiornamento dati completato per dipendente con ID: " + id_dipendente);
        return this.dipendenteRepo.save(dipFound);
    }

    public void findDipendenteByIdAndDel(long id_dipendente) {
        Dipendente dipenteToDel = this.findDipendenteById(id_dipendente);
        this.dipendenteRepo.delete(dipenteToDel);
    }

    public String patchDipendenteAvatar(long id_dipendente, MultipartFile file) {
        Dipendente dipendenteToPatchImg = findDipendenteById(id_dipendente);
        String url = null;
        try {
            Map<String, Object> risutatoUp = cloudinaryUp.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            url = (String) risutatoUp.get("url");
            dipendenteToPatchImg.setAvatar_url(url);
            dipendenteRepo.save(dipendenteToPatchImg);
        } catch (IOException e) {
            throw new BadRequestException("Ci sono stati problemi con l'upload del file!");
        }
        return url;
    }
}
