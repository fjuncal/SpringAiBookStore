package com.springai.bookstore.ai.rest.controllers;

import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/bookstore")
public class BookstoreAssistantController {
    private final OpenAiChatClient ChatClient;

    public BookstoreAssistantController(OpenAiChatClient ChatClient) {
        this.ChatClient = ChatClient;
    }

    @GetMapping("/informations")
    public String bookStoreChat(@RequestParam(value = "message", defaultValue = "Quais são os livros best sellers dos ultimos anos?") String message){
        return ChatClient.call(message);
    }

    @GetMapping("/informationsChatResponse")
    public ChatResponse bookStoreChatParameter(@RequestParam(value = "message", defaultValue = "Quais são os livros best sellers dos ultimos anos?") String message){
        return ChatClient.call(new Prompt(message));
    }
    @GetMapping("/reviews")
    public String bookStoreReview(@RequestParam(value = "book", defaultValue = "Dom Quixote") String book){
        PromptTemplate promptTemplate = new PromptTemplate("""
                Por favor, me forneça um breve resumo do livro {book}
                e também a biografia de seu autor
                """);
        promptTemplate.add("book", book);
        return ChatClient.call(promptTemplate.create()).getResult().getOutput().getContent();
    }

    //
    @GetMapping("/stream/informations")
    public Flux<String> bookStoreChatStream(@RequestParam(value = "message", defaultValue = "Quais são os livros best sellers dos ultimos anos?") String message){
        return ChatClient.stream(message);
    }
}
