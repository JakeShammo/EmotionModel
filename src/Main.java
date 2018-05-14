import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        EmoBot emoBot = new EmoBot();
        while(true){
            Scanner scanner = new Scanner(System. in);
            String input = scanner. nextLine();
            if(input.equalsIgnoreCase("Exit")) break;
            emoBot.receivedEmoState = input;
            System.out.println(emoBot.geterateOverallEmotion());
        }
    }
}
