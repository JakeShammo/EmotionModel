import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        EmoBot emoBot = new EmoBot();
        System.out.println("Rough emoBot model");
        System.out.println("Each time input one of the following emotions:");
        System.out.println("Amusement, Happiness, Surprise, Inspiration, Sympathy, Sadness, Fear, Anger, Disgust, Neutral");
        while(true){
            Scanner scanner = new Scanner(System. in);
            System.out.print("Input Emotion: ");
            String input = scanner. nextLine();
            if(input.equalsIgnoreCase("Exit")) break;
            emoBot.setReceivedEmoState(input);
            System.out.println("Overall State: "+emoBot.geterateOverallEmotion());
            System.out.println("Response Type: "+emoBot.geterateResponse());
        }
    }

}
