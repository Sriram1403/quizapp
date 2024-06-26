package com.sriram.quizapp.Service;

import com.sriram.quizapp.Dao.QuestionDao;
import com.sriram.quizapp.Model.Questions;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;

    public ResponseEntity<List<Questions>> allQuestions() {

        try{
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Questions>> getAllQuestionsByCategory(String category) {

        try{
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity<String> addQuestion(Questions question) {

        try{
            if (question.getDifficultyLevel() == null ||
                    question.getOption1() == null ||
                    question.getOption2() == null ||
                    question.getOption3() == null ||
                    question.getOption4() == null ||
                    question.getQuestionTitle() == null ||
                    question.getRightAnswer() == null ||
                    question.getCategory() == null) {
                return new ResponseEntity<>("Missing required fields", HttpStatus.BAD_REQUEST);
            }
            questionDao.save(question);
            return new ResponseEntity<>("Question Added Successfully", HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to add question",HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> deleteQuestionById(Integer id) {
        try {
            Optional<Questions> optionalQuestion = questionDao.findById(id);
            if (optionalQuestion.isPresent()) {
                questionDao.deleteById(id);
                return new ResponseEntity<>("Question Deleted Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Question not found with id: " + id, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to delete question", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
