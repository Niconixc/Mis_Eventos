package com.example.miseventos;

public class User {
    private int id;
    private String username;
    private String password;
    private String secretQuestion;
    private String secretAnswer;

    public User(int id, String username, String password, String secretQuestion, String secretAnswer) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.secretQuestion = secretQuestion;
        this.secretAnswer = secretAnswer;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getSecretQuestion() { return secretQuestion; }
    public void setSecretQuestion(String secretQuestion) { this.secretQuestion = secretQuestion; }

    public String getSecretAnswer() { return secretAnswer; }
    public void setSecretAnswer(String secretAnswer) { this.secretAnswer = secretAnswer; }
}
