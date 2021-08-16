package mypackage.apprestfullapicodingbat.service;

import mypackage.apprestfullapicodingbat.entity.Department;
import mypackage.apprestfullapicodingbat.entity.Task;
import mypackage.apprestfullapicodingbat.payload.TaskDto;
import mypackage.apprestfullapicodingbat.payload.template.Result;
import mypackage.apprestfullapicodingbat.repository.DepartmentRepository;
import mypackage.apprestfullapicodingbat.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    /**
     * ADD NEW TASK
     * @param taskDto
     * @return Result
     */
    public Result addTask(TaskDto taskDto){
        boolean exists = taskRepository.existsByQuestionAndDepartment_Id(taskDto.getQuestion(), taskDto.getDepartmentId());
        if (exists)
            return new Result("Bunday savol bu bo`limda mavjud", false);
        Task task=new Task();
        task.setQuestion(taskDto.getQuestion());
        task.setHint(taskDto.getHint());
        task.setSolution(taskDto.getSolution());
        Optional<Department> optionalDepartment = departmentRepository.findById(taskDto.getDepartmentId());
        if (!optionalDepartment.isPresent())
            return new Result("Bunday bo`lim mavjud emas",false);
        task.setDepartment(optionalDepartment.get());
        taskRepository.save(task);
        return new Result("Muvaffaqiyatli saqlandi", true);
    }

    /**
     * GET ALL TASKS
     * @return List<Task>
     */
    public List<Task> getAllTask(){
        return taskRepository.findAll();
    }

    /**
     * GET TASK BY ID
     * @param id
     * @return Task
     */
    public Task getTaskById(Integer id){
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent())
            return null;
        return optionalTask.get();
    }

    /**
     * EDIT TASK BY ID
     * @param id
     * @param taskDto
     * @return Result
     */
    public Result editTask(Integer id, TaskDto taskDto){
        boolean exists = taskRepository.existsByQuestionAndDepartment_IdAndIdNot(taskDto.getQuestion(), taskDto.getDepartmentId(), id);
        if (exists)
            return new Result("Bunday savol ushbu bo`limda mavjud", false);
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent())
            return new Result("Bunday savol mavjud emas", false);
        Task task = optionalTask.get();
        task.setQuestion(taskDto.getQuestion());
        task.setHint(taskDto.getHint());
        task.setSolution(taskDto.getSolution());
        Optional<Department> optionalDepartment = departmentRepository.findById(taskDto.getDepartmentId());
        if (!optionalDepartment.isPresent())
            return new Result("Bunday bo`lim mavjud emas",false);
        task.setDepartment(optionalDepartment.get());
        taskRepository.save(task);
        return new Result("Muvaffaqiyatli tahrirlandi", true);
    }

    /**
     * DELETE TASK BY ID
     * @param id
     * @return Result
     */
    public Result deleteTask(Integer id){
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent())
            return new Result("Bunday savol mavjud emas", false);
        taskRepository.deleteById(id);
        return new Result("Muvaffaqiyatli o`chirildi", true);
    }
 }
