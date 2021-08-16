package mypackage.apprestfullapicodingbat.controller;

import mypackage.apprestfullapicodingbat.entity.Language;
import mypackage.apprestfullapicodingbat.payload.LanguageDto;
import mypackage.apprestfullapicodingbat.payload.template.Result;
import mypackage.apprestfullapicodingbat.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/language")
public class LanguageController {

    @Autowired
    LanguageService languageService;

    /**
     * ADD LANGUAGE
     * @param languageDto
     * @return ResponseEntity<Result>
     */
    @PostMapping
    public ResponseEntity<Result> add(@Valid @RequestBody LanguageDto languageDto){
        Result result = languageService.addLanguage(languageDto);
        return ResponseEntity.status(result.isSuccess()?201:409).body(result);
    }

    /**
     * GET ALL LANGUAGES
     * @return ResponseEntity<List<Language>>
     */
    @GetMapping
    public ResponseEntity<List<Language>> getAll(){
        return ResponseEntity.ok(languageService.getAllLanguages());
    }

    /**
     * GET LANGUAGE BY ID
     * @param id
     * @return ResponseEntity<Language>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Language> getById(@PathVariable Integer id){
        return ResponseEntity.ok(languageService.getLanguageById(id));
    }

    /**
     * EDIT LANGUAGE
     * @param id
     * @param languageDto
     * @return ResponseEntity<Result>
     */
    @PutMapping("/{id}")
    public ResponseEntity<Result> edit(@PathVariable Integer id, @Valid @RequestBody LanguageDto languageDto){
        Result result = languageService.editLanguageById(id, languageDto);
        return ResponseEntity.status(result.isSuccess()?202:409).body(result);
    }

    /**
     * DELETE LANGUAGE
     * @param id
     * @return ResponseEntity<Result>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Result> delete(@PathVariable Integer id){
        Result result = languageService.delete(id);
        return ResponseEntity.status(result.isSuccess()?202:409).body(result);
    }

}
