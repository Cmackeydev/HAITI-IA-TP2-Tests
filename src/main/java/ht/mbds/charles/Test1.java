package ht.mbds.charles;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;

public class Test1 {
    public static void main(String[] args) {
        String llmKey = System.getenv("GEMINI_KEY");
    if (llmKey == null) {
      System.out.println("La variable d'environnement GEMINI_KEY n'est pas définie.");
      return;
    }

    ChatModel modele = GoogleAiGeminiChatModel.builder()
.apiKey(llmKey)
.modelName("gemini-3-flash-preview")
.build();
// Pose une question au modèle
String reponse =modele.chat("TQuel est mon nom");
// Affiche la réponse du modèle (hello)
System.out.println(reponse);
}
}
