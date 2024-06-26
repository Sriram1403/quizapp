package com.sriram.quizapp.Dao;

import com.sriram.quizapp.Model.Questions;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Questions,Integer> {

    List<Questions> findByCategory(String category);

    @Query(value = "SELECT * FROM questions q Where q.category=:category && q.difficulty_level=:difficultyLevel ORDER BY RAND()", nativeQuery = true)
    List<Questions> findRandomQuestionsByCategoryAndDiffcultyLevel(String category, Pageable pageable, String difficultyLevel);
}
