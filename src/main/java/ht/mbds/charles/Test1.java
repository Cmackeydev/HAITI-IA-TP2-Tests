package ht.mbds.charles;

public class Test1 {
    public static void main(String[] args) {
        String llmKey = System.getenv("GEMINI_KEY");
    if (llmKey == null) {
      System.out.println("La variable d'environnement GEMINI_KEY n'est pas définie.");
      return;
    }
    
}
}
