package com.sriram.quizapp.Service;

import com.sriram.quizapp.Dao.QuestionDao;
import com.sriram.quizapp.Dao.QuizDao;
import com.sriram.quizapp.Model.Questions;
import com.sriram.quizapp.Model.Quiz;
import com.sriram.quizapp.Wrapper.QuestionWrapper;
import com.sriram.quizapp.Wrapper.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizDao quizDao;

    @Autowired
    private QuestionDao questionDao;


    public ResponseEntity<String> createQuiz(String category, int numOfQuestions, String difficultyLevel, String title) {

        try {
            Pageable pageable = PageRequest.of(0, numOfQuestions);
            List<Questions> questions = questionDao.findRandomQuestionsByCategoryAndDiffcultyLevel(category, pageable, difficultyLevel);

            if (questions.isEmpty()) {
                return new ResponseEntity<>("No questions found for the specified criteria", HttpStatus.NOT_FOUND);
            }

            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestions(questions);
            quizDao.save(quiz);

            return new ResponseEntity<>("Quiz Created Successfully", HttpStatus.CREATED);
        } catch (Exception e) {

            e.printStackTrace();
            return new ResponseEntity<>("Failed to create quiz", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {

        try {

            Optional<Quiz> quiz = quizDao.findById(id);

            if(quiz.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            List<Questions> questionsFromDB = quiz.get().getQuestions();
            List<QuestionWrapper> questionsForUser = new ArrayList<>();

            for(Questions q : questionsFromDB){
                QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
                questionsForUser.add(qw);
            }

            return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
        }catch (Exception e) {

            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<ResponseWrapper> responses) {
        Quiz quiz = quizDao.findById(id).get();
        List<Questions> questions = quiz.getQuestions();
        int right = 0;
        int i = 0;
        for(ResponseWrapper responseWrapper : responses){
            if(i < questions.size() && responseWrapper.getResponse().equals(questions.get(i).getRightAnswer())){
                right++;
            }
            i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
