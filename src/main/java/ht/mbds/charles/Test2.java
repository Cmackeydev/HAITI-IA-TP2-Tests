package ht.mbds.charles;

import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;

public class Test2 {
    public static void main(String[] args) {
        String llmKey = System.getenv("GEMINI_KEY");
    if (llmKey == null) {
      System.out.println("La variable d'environnement GEMINI_KEY n'est pas définie 2.");
      return;
    }

    ChatModel modele = GoogleAiGeminiChatModel.builder()
.apiKey(llmKey)
.modelName("gemini-3-flash-preview")
.build();
// Pose une question au modèle
ChatResponse reponse =modele.chat( UserMessage.from("Quel est ton nom?"));
// Affiche la réponse du modèle (hello)
System.out.println(reponse.aiMessage().text());
}
}
