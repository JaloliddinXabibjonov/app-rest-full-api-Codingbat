package mypackage.apprestfullapicodingbat.controller;

import mypackage.apprestfullapicodingbat.entity.Answer;
import mypackage.apprestfullapicodingbat.payload.AnswerDto;
import mypackage.apprestfullapicodingbat.payload.template.Result;
import mypackage.apprestfullapicodingbat.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @PostMapping
    public ResponseEntity<Result> add(@Valid @RequestBody AnswerDto answerDto){
        Result result = answerService.addAnswer(answerDto);
        return ResponseEntity.status(result.isSuccess()?201:409).body(result);
    }

    @GetMapping
    public ResponseEntity<List<Answer>> getAll(){
        return ResponseEntity.ok(answerService.getAllAnswers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Answer> getById(@PathVariable Integer id){
        return ResponseEntity.ok(answerService.getAnswerById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> edit(@PathVariable Integer id, @Valid @RequestBody AnswerDto answerDto){
        Result result = answerService.editAnswer(id, answerDto);
        return ResponseEntity.status(result.isSuccess()?202:409).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> delete(@PathVariable Integer id){
        Result result = answerService.deleteAnswer(id);
        return ResponseEntity.status(result.isSuccess()?202:409).body(result);
    }
}
