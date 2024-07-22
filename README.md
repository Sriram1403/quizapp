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
  - Example:
    ```json
    {
      "qusetionTitle": "string",
      "option1": "String",
      "option2": "String",
      "option3": "String",
      "option4": "String",
      "rightAnswer": "String",
      "difficultyLevel": "String",
      "category": "String"
    }
    ```
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
  - Request Parameters:
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
  - Example:
    ```json
    {
      "id": Integer,
      "response": "String"
    }
    ```
  - Response: `ResponseEntity<Integer>`

## Technologies Used

- Spring Boot: Framework for building the application.
- Java: Programming language used for development.
- MySQL: Database used for storing and retrieving questions and quizzes.

## Docker Setup

### Prerequisites

Ensure you have Docker installed on your machine. You can download and install Docker from [Docker's official website](https://www.docker.com/products/docker-desktop).

### Setup with Docker Compose

   
**Build and run the containers using Docker Compose:**

  ```sh
    docker-compose up --build
  ```
    
**Verify that the containers are running and connected to the `quizapp-network`:**

  ```sh
    docker ps
    docker network inspect quizapp-network
   ```
    
**To stop and restart:**

  ```sh
    docker-compose down
    docker-compose up -d
  ```

###Setup in cmd for testing purposes

 **Create a Docker Network:**
   ```sh
     docker network create quizapp-network
   ```
 **Run MySQL Container:**
   ```sh
     docker run --name mysql-container --network quizapp-network -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=quizapp -p 3307:3306 -d mysql:latest
   ```
 **Build and Run Quiz App Container:**

  - Navigate to the project directory where your Dockerfile is located.
  - Build the Docker image:
       ```sh
         docker build -t quizapp .
       ```
  - Run the Docker containe:
      ```sh
        docker run --name quiz-app --network quizapp-network -p 8080:8080 -d quizapp
       ```
      
  **Verify Containers:**

  - Check if the containers are running and connected
      ```sh
         docker ps
       ```
      ```sh
         docker network inspect quizapp-network
      ```
You should see both quiz-app and mysql-container connected to the quizapp-network.

 ### Access the Application

Once the containers are up and running, you can access the application at [http://localhost:8080](http://localhost:8080).

## How to Run without Docker

1. Clone the repository.
2. Navigate to the project directory.
3. Run the application using your preferred IDE or command line with `mvn spring-boot:run`.

---

Feel free to reach out for any queries or contributions. Enjoy using the Quiz App!
