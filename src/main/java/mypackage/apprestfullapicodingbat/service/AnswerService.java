package mypackage.apprestfullapicodingbat.service;

import mypackage.apprestfullapicodingbat.entity.Answer;
import mypackage.apprestfullapicodingbat.entity.Task;
import mypackage.apprestfullapicodingbat.entity.User;
import mypackage.apprestfullapicodingbat.payload.AnswerDto;
import mypackage.apprestfullapicodingbat.payload.template.Result;
import mypackage.apprestfullapicodingbat.repository.AnswerRepository;
import mypackage.apprestfullapicodingbat.repository.TaskRepository;
import mypackage.apprestfullapicodingbat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TaskRepository taskRepository;

    /**
     * ADD ANSWER TO TASK
     * @param answerDto
     * @return Result
     */
    public Result addAnswer(AnswerDto answerDto){
        boolean exists = answerRepository.existsByTextCheckAnswerAndTask_Id(answerDto.getTextCheckAnswer(), answerDto.getTaskId());
        if (exists)
            return new Result("Bunday javob va savol mavjud",false);
        Answer answer=new Answer();
        answer.setDescription(answerDto.getDescription());
        answer.setTextCheckAnswer(answerDto.getTextCheckAnswer());
        answer.setSuccess(false);
        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        if (optionalUser.isPresent()){
            answer.setUser(optionalUser.get());
        }
        Optional<Task> optionalTask = taskRepository.findById(answerDto.getTaskId());
        if (!optionalTask.isPresent())
            return new Result("Bunday savol mavjud emas", false);
        answer.setTask(optionalTask.get());
        answerRepository.save(answer);
        return new Result("Muvaffaqiyatli saqlandi", true);
    }

    /**
     * GET ALL ANSWERS
     * @return List<Answer>
     */
    public List<Answer> getAllAnswers(){
        return answerRepository.findAll();
    }

    /**
     * GET ANSWER BY ID
     * @param id
     * @return
     */
    public Answer getAnswerById(Integer id){
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (!optionalAnswer.isPresent()) {
            return null;
        }
        return optionalAnswer.get();
    }

    /**
     * EDIT ANSWER BY ID
     * @param id
     * @param answerDto
     * @return Result
     */
    public Result editAnswer(Integer id, AnswerDto answerDto){
        boolean exists = answerRepository.existsByTextCheckAnswerAndTask_IdAndIdNot(answerDto.getTextCheckAnswer(), answerDto.getTaskId(), id);
        if (exists)
            return new Result("Bunday javob va savol mavjud", false);
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (!optionalAnswer.isPresent())
            return new Result("Bunday javob mavjud emas", false);
        Answer answer = optionalAnswer.get();
        answer.setDescription(answerDto.getDescription());
        answer.setTextCheckAnswer(answerDto.getTextCheckAnswer());
        answer.setSuccess(false);
        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        if (optionalUser.isPresent()){
            answer.setUser(optionalUser.get());
        }
        Optional<Task> optionalTask = taskRepository.findById(answerDto.getTaskId());
        if (!optionalTask.isPresent())
            return new Result("Bunday savol mavjud emas", false);
        answer.setTask(optionalTask.get());
        answerRepository.save(answer);
        return new Result("Muvaffaqiyatli tahrirlandi", true);
    }

    /**
     * DELETE ANSWER BY ID
     * @param id
     * @return Result
     */
    public Result deleteAnswer(Integer id){
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (!optionalAnswer.isPresent())
            return new Result("Bunday javob mavjud emas", false);
        answerRepository.deleteById(id);
        return new Result("Muvaffaqiyatli o`chirildi", true);
    }
}
