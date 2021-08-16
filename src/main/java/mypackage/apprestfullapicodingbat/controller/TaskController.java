package mypackage.apprestfullapicodingbat.controller;

import mypackage.apprestfullapicodingbat.entity.Task;
import mypackage.apprestfullapicodingbat.payload.TaskDto;
import mypackage.apprestfullapicodingbat.payload.template.Result;
import mypackage.apprestfullapicodingbat.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    /**
     * ADD NEW TASK
     * @param taskDto
     * @return ResponseEntity<Result>
     */
    @PostMapping
    public ResponseEntity<Result> add(@Valid @RequestBody TaskDto taskDto){
        Result result = taskService.addTask(taskDto);
        return ResponseEntity.status(result.isSuccess()?201:409).body(result);
    }

    /**
     * GET ALL TASKS
     * @return ResponseEntity<List<Task>>
     */
    @GetMapping
    public ResponseEntity<List<Task>> getAll(){
        return ResponseEntity.ok(taskService.getAllTask());
    }

    /**
     * GET TASK BY ID
     * @param id
     * @return ResponseEntity<Task>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(@PathVariable Integer id){
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    /**
     * EDIT TASK
     * @param id
     * @param taskDto
     * @return ResponseEntity<Result>
     */
    @PutMapping("/{id}")
    public ResponseEntity<Result> edit(@PathVariable Integer id, @Valid @RequestBody TaskDto taskDto){
        Result result = taskService.editTask(id, taskDto);
        return ResponseEntity.status(result.isSuccess()?202:409).body(result);
    }

    /**
     * DELETE TASK
     * @param id
     * @return ResponseEntity<Result
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Result> delete(@PathVariable Integer id){
        Result result = taskService.deleteTask(id);
        return ResponseEntity.status(result.isSuccess()?202:409).body(result);
    }

}
