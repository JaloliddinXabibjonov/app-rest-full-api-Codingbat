package mypackage.apprestfullapicodingbat.service;

import mypackage.apprestfullapicodingbat.entity.Language;
import mypackage.apprestfullapicodingbat.payload.LanguageDto;
import mypackage.apprestfullapicodingbat.payload.template.Result;
import mypackage.apprestfullapicodingbat.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {

    @Autowired
    LanguageRepository languageRepository;

    /**
     * ADD NEW LANGUAGE
     * @param languageDto
     * @return Result
     */
    public Result addLanguage(LanguageDto languageDto){
        boolean existsByName = languageRepository.existsByName(languageDto.getName());
        if (existsByName)
            return new Result("Ushbu nomdagi dasturlash tili mavjud", false);
        Language language=new Language();
        language.setName(languageDto.getName());
        languageRepository.save(language);
        return new Result("Muvaffaqiyatli saqlandi", true);
    }

    /**
     * GET ALL LANGUAGES
     * @return List<Language>
     */
    public List<Language> getAllLanguages(){
        return languageRepository.findAll();
    }

    /**
     * GET LANGUAGE BY ID
     * @param id
     * return LANGUAGE
     */
    public Language getLanguageById(Integer id){
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (optionalLanguage.isPresent())
            return optionalLanguage.get();
        return new Language();
    }

    /**
     * EDIT LANGUAGE BY ID
     * @param id
     * @param languageDto
     * @return Result
     */
    public Result editLanguageById(Integer id, LanguageDto languageDto){
        boolean existsByName = languageRepository.existsByNameAndIdNot(languageDto.getName(),id);
        if (existsByName)
            return new Result("Ushbu nomli dasturlash tili avval kiritilgan", false);
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (!optionalLanguage.isPresent())
            return new Result("Bunday dasturlash tili mavjud emas", false);
        Language language = optionalLanguage.get();
        language.setName(languageDto.getName());
        languageRepository.save(language);
        return new Result("Muvaffaqiyatli tahrirlandi", true);
    }

    /**
     * DELETE LANGUAGE BY ID
     * @param id
     * @return Result
     */
    public Result delete(Integer id){
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (!optionalLanguage.isPresent())
            return new Result("Bunday dasturlash tili mavjud emas", false);
        languageRepository.deleteById(id);
        return new Result("Muvaffaqiyatli o`chirildi", true);
    }
}
