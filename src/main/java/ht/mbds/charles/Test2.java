package ht.mbds.charles;

import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
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
ChatResponse reponse =modele.chat( UserMessage.from("Quel est mon nom?"));
// Affiche la réponse du modèle (hello)
System.out.println(reponse.aiMessage().text());
double cout_entrant = reponse.tokenUsage().inputTokenCount()*0.5/1000000;
double cout_sortant = reponse.tokenUsage().outputTokenCount()*3.0/1000000;
System.out.println("Coût de tokens en entrée  : " + cout_entrant + " $");
System.out.println("Coût de tokens à la sortie : " + cout_sortant + " $" );
System.out.println("Une requête environ " + Math.ceil(1/(cout_entrant + cout_sortant)) + " fois plus grande couterait 1 $");
}

}
