package com.sriram.quizapp.Controller;

import com.sriram.quizapp.Model.Questions;
import com.sriram.quizapp.Service.QuizService;
import com.sriram.quizapp.Wrapper.QuestionWrapper;
import com.sriram.quizapp.Wrapper.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numOfQuestions, @RequestParam String difficultyLevel, @RequestParam String title){
        return quizService.createQuiz(category,numOfQuestions,difficultyLevel,title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
       return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submit(@PathVariable Integer id, @RequestBody List<ResponseWrapper> responses){
        return quizService.calculateResult(id, responses);
    }
}
