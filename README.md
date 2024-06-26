# Quiz App Project

## Overview

This project is a Quiz Application with functionalities to manage quiz questions and quizzes. It provides RESTful endpoints to handle various operations related to questions and quizzes.

## Endpoints

### Question Controller

The `QuestionController` manages operations related to quiz questions. It provides the following endpoints:

- **Get All Questions**

  - Endpoint: `GET /question/allQuestions`
  - Description: Fetches all questions.
  - Response: `ResponseEntity<List<Questions>>`

- **Get Questions by Category**

  - Endpoint: `GET /question/category/{category}`
  - Description: Fetches all questions by the specified category.
  - Path Variable: 
    - `category` (String): The category of questions to fetch.
  - Response: `ResponseEntity<List<Questions>>`

- **Add a Question**

  - Endpoint: `POST /question/add`
  - Description: Adds a new question.
  - Request Body: `Questions` object containing the question details.
  - Response: `ResponseEntity<String>`

- **Delete a Question**

  - Endpoint: `DELETE /question/delete/{id}`
  - Description: Deletes a question by its ID.
  - Path Variable: 
    - `id` (Integer): The ID of the question to delete.
  - Response: `ResponseEntity<String>`

### Quiz Controller

The `QuizController` manages operations related to quizzes. It provides the following endpoints:

- **Create a Quiz**

  - Endpoint: `POST /quiz/create?category={category}&numOfQuestions={numOfQuestions}&difficultyLevel={difficultyLevel}&title={your title}`
  - Description: Creates a new quiz.
  - Request Parameters*:
    - `category` (String): The category of the quiz.
    - `numOfQuestions` (int): Number of questions in the quiz.
    - `difficultyLevel` (String): Difficulty level of the quiz.
    - `title` (String): Title of the quiz.
  - Response: `ResponseEntity<String>`

- **Get Quiz Questions**

  - Endpoint: `GET /quiz/get/{id}`
  - Description: Fetches questions for the specified quiz.
  - Path Variable: 
    - `id` (Integer): The ID of the quiz.
  - Response: `ResponseEntity<List<QuestionWrapper>>`

- **Submit a Quiz**

  - Endpoint: `POST /quiz/submit/{id}`
  - Description: Submits responses for the specified quiz and calculates the result.
  - Path Variable: 
    - `id` (Integer): The ID of the quiz.
  - Request Body: List of `ResponseWrapper` objects containing the responses.
  - {
        "id": Integer,
        "response":"String"
    }
  - Response: `ResponseEntity<Integer>`

## Technologies Used

- Spring Boot: Framework for building the application.
- Java: Programming language used for development.
- MySql: DB used for storing and retrieving qustions and quizes.

## How to Run

1. Clone the repository.
2. Navigate to the project directory.
3. Run the application using your preferred IDE or command line with `mvn spring-boot:run`.

---

Feel free to reach out for any queries or contributions. Enjoy using the Quiz App!
