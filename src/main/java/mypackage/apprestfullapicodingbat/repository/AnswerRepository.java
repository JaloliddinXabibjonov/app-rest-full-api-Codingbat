package mypackage.apprestfullapicodingbat.repository;

import mypackage.apprestfullapicodingbat.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    boolean existsByTextCheckAnswerAndTask_Id(String textCheckAnswer, Integer task_id);
    boolean existsByTextCheckAnswerAndTask_IdAndIdNot(String textCheckAnswer, Integer task_id, Integer id);
}
