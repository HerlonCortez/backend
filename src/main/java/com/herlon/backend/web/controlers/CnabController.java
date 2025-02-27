package com.herlon.backend.web.controlers;

import com.herlon.backend.domain.sevices.CnabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("cnab")
@CrossOrigin(origins = {"https://frontend-ycvu.onrender.com","http://localhost:9090"})
public class CnabController {

    @Autowired
    private CnabService cnabService;

    @PostMapping("upload")
    public String upload(@RequestParam("file")MultipartFile file) throws  Exception{
        cnabService.uploadCnabFile(file);
        return "Processamento iniciado";
    }
}
