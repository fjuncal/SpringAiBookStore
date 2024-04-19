package com.springai.bookstore.ai.rest.controllers;

import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookstore")
public class BookstoreAssistantController {
    private final OpenAiChatClient openAiChatClient;

    public BookstoreAssistantController(OpenAiChatClient openAiChatClient) {
        this.openAiChatClient = openAiChatClient;
    }

    @GetMapping("/informations")
    public String bookStoreChat(@RequestParam(value = "message", defaultValue = "Quais são os livros best sellers dos ultimos anos?") String message){
        return openAiChatClient.call(message);
    }
}
