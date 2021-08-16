package mypackage.apprestfullapicodingbat.repository;

import mypackage.apprestfullapicodingbat.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskRepository extends JpaRepository<Task, Integer> {
        boolean existsByQuestionAndDepartment_Id(String question, Integer department_id);
        boolean existsByQuestionAndDepartment_IdAndIdNot(String question, Integer department_id, Integer id);
}
