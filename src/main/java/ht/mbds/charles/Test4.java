package ht.mbds.charles;

import java.time.Duration;

import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.googleai.GoogleAiEmbeddingModel;
import dev.langchain4j.model.googleai.GoogleAiEmbeddingModel.TaskType;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.CosineSimilarity;


public class Test4 {
    public static void main(String[] args) {
        String llmKey = System.getenv("GEMINI_KEY");
    if (llmKey == null) {
      System.out.println("La variable d'environnement GEMINI_KEY n'est pas définie.");
      return;
    }

EmbeddingModel embeddingModel = GoogleAiEmbeddingModel.builder().apiKey(llmKey).modelName("gemini-embedding-2").taskType(TaskType.SEMANTIC_SIMILARITY).outputDimensionality(300).timeout(Duration.ofMillis(5000)).build();

Response<Embedding> embedding1 = embeddingModel.embed("Je suis la première phrase");
Response<Embedding> embedding2 = embeddingModel.embed("Je suis une phrase similaire à la première");

double similarite = CosineSimilarity.between(embedding2.content(),embedding1.content());
System.out.println("Similarité entre les deux phrases : " + similarite);

}
}
