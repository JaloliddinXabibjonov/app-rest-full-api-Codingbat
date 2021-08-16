package mypackage.apprestfullapicodingbat.service;

import mypackage.apprestfullapicodingbat.entity.Department;
import mypackage.apprestfullapicodingbat.entity.Language;
import mypackage.apprestfullapicodingbat.payload.DepartmentDto;
import mypackage.apprestfullapicodingbat.payload.template.Result;
import mypackage.apprestfullapicodingbat.repository.DepartmentRepository;
import mypackage.apprestfullapicodingbat.repository.LanguageRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    LanguageRepository languageRepository;

    /**
     * ADD NEW DEPARTMENT
     * @param departmentDto
     * @return Result
     */
    public Result add(DepartmentDto departmentDto){
        //CHECK DEPARTMENT NAME AND LANGUAGE ID
        boolean exists = departmentRepository.existsByNameAndLanguage_Id(departmentDto.getName(), departmentDto.getLanguageId());
        if (exists)
            return new Result("Bunday bo`lim bu dasturlash tilida mavjud", false);

        Department department=new Department();
        department.setName(departmentDto.getName());
        department.setDescription(departmentDto.getDescription());
        Optional<Language> optionalLanguage = languageRepository.findById(departmentDto.getLanguageId());
        if (!optionalLanguage.isPresent())
            return new Result("Bunday dasturlash tili mavjud emas", false);
        department.setLanguage(optionalLanguage.get());
        departmentRepository.save(department);
        return new Result("Muvaffaqiyatli saqlandi", true);
    }

    /**
     * GET ALL DEPARTMENTS
     * @return List<Department>
     */
    public List<Department> getAll(){
        return departmentRepository.findAll();
    }

    /**
     * GET DEPARTMENT BY ID
     * @param id
     * @return DEPARTMENT
     */
    public Department getById(Integer id){
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent())
            return null;
        return optionalDepartment.get();
    }

    /**
     * EDIT DEPARTMENT BY ID
     * @param id
     * @param departmentDto
     * @return Result
     */
    public Result edit(Integer id, DepartmentDto departmentDto){
        boolean exists = departmentRepository.existsByNameAndLanguage_IdAndIdNot(departmentDto.getName(), departmentDto.getLanguageId(), id);
        if (exists)
            return new Result("Bu nomli bo`lim ushbu dasturlash tilida mavjud",false);
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent())
            return new Result("Bunday bo`lim mavjud emas", false);
        Department department = optionalDepartment.get();
        department.setName(departmentDto.getName());
        department.setDescription(departmentDto.getDescription());
        Optional<Language> optionalLanguage = languageRepository.findById(departmentDto.getLanguageId());
        if (!optionalLanguage.isPresent())
            return new Result("Bunday dasturlash tili mavjud emas", false);
        department.setLanguage(optionalLanguage.get());
        departmentRepository.save(department);
        return new Result("Muvaffaqiyatli tahrirlandi", true);
    }

    /**
     * DELETE DEPARTMENT BY ID
     * @param id
     * @return Result
     */
    public Result delete(Integer id){
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent())
            return new Result("Bunday bo`lim mavjud emas", false);
        departmentRepository.deleteById(id);
        return new Result("Muvaffaqiyatli o`chirildi", true);
    }
}
