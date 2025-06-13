package com.hazavao.demo.service;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class WordService {

  private final OpenAiService openAiService;

  public WordService() {
    String apiKey = System.getenv("API_KEY");
    if (apiKey == null || apiKey.isBlank()) {
      apiKey = "api_key";
    }
    this.openAiService = new OpenAiService(apiKey);
  }

  public String getDefinition(String teny) {
    ChatMessage systemMessage = new ChatMessage("system", "Vous êtes un dictionnaire malgache.");
    ChatMessage userMessage =
        new ChatMessage("user", "Donne-moi la définition en malgache du mot : " + teny);

    ChatCompletionRequest completionRequest =
        ChatCompletionRequest.builder()
            .model("gpt-3.5-turbo")
            .messages(List.of(systemMessage, userMessage))
            .build();

    ChatCompletionResult result = openAiService.createChatCompletion(completionRequest);

    if (result.getChoices() != null && !result.getChoices().isEmpty()) {
      return result.getChoices().get(0).getMessage().getContent();
    } else {
      return "Tsy afaka mahita fanazavana amin'izao fotoana izao.";
    }
  }
}
