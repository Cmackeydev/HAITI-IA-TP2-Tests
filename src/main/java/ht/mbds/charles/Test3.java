package ht.mbds.charles;

import java.util.Map;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;

public class Test3 {
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

PromptTemplate traducteur = PromptTemplate.from("Traduire le texte suivant en anglais : {texte}");
Prompt promptTraduction = traducteur.apply(Map.of("texte","Bonjour"));
String reponse =modele.chat(promptTraduction.toUserMessage()).toString();
// Affiche la réponse du modèle (hello)
System.out.println(reponse);
}
}
