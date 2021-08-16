package mypackage.apprestfullapicodingbat.controller;

import mypackage.apprestfullapicodingbat.entity.Department;
import mypackage.apprestfullapicodingbat.payload.DepartmentDto;
import mypackage.apprestfullapicodingbat.payload.template.Result;
import mypackage.apprestfullapicodingbat.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    /**
     * ADD NEW DEPARTMENT
     * @param departmentDto
     * @return ResponseEntity<Result>
     */
    @PostMapping
    public ResponseEntity<Result> add(@Valid @RequestBody DepartmentDto departmentDto){
        return ResponseEntity.status(departmentService.add(departmentDto).isSuccess()?201:409).body(departmentService.add(departmentDto));
    }


    /**
     * GET ALL DEPARTMENTS
     * @return ResponseEntity<List<Department>>
     */
    @GetMapping
    public ResponseEntity<List<Department>> getAll(){
        return ResponseEntity.ok(departmentService.getAll());
    }

    /**
     * GET DEPARTMENT BY ID
     * @param id
     * @return ResponseEntity<Department>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Department> getById(@PathVariable Integer id){
        Department department = departmentService.getById(id);
        return ResponseEntity.ok(department);
    }

    /**
     * EDIT DEPARTMENT BY ID
     * @param id
     * @param departmentDto
     * @return ResponseEntity<Result>
     */
    @PutMapping("/{id}")
    public ResponseEntity<Result> edit(@PathVariable Integer id, @Valid @RequestBody DepartmentDto departmentDto){
        Result result = departmentService.edit(id, departmentDto);
        return ResponseEntity.status(result.isSuccess()?202:409).body(result);
    }

    /**
     * DELETE DEPARTMENT BY ID
     * @param id
     * @return ResponseEntity<Result>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Result> delete(@PathVariable Integer id){
        Result result = departmentService.delete(id);
        return ResponseEntity.status(result.isSuccess()?202:409).body(result);
    }
}
