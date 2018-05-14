import java.util.ArrayList;
import java.lang.Math;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.sqrt;

public class EmoBot {
    int goal;
    String receivedEmoState;
    int receivedEmoIndex, currentLevel;
    double friendliness, transition, prevReceivedEmoState, coolingDownEffect = .2;
    double[] levels = {0.0,0.0,0.0};
    double[] levelFactors = {.1,0,.2 };
    double[][] emotionValues = {{5,-5,-5},{3,-3,-3},{2.5,-2.5,-2.5},{2,-3,0},{2,-7,-3},{-2,4,-1},{-2,4,-2},{-5,2,4},{-6,2,5},{0,0,0}};
    List<String> emotions = Arrays.asList("Amusement", "Happiness", "Surprise", "Inspiration", "Sympathy", "Sadness", "Fear", "Anger", "Disgust", "Neutral");
    int[][] responseValue = {{1,1,9},{1,1,9},{1,1,9},{1,1,7},{2,1,9},{4,4,9},{3,3,9},{5,6,7},{5,8,7},{2,9,9}};
    List<String> response = Arrays.asList("Amusement", "Happy", "Surprise", "Inspiring", "Sympathy", "Sad", "Fear", "Anger", "Disgust", "Neutral");
    EmoBot(int goal, float friendliness){
        this.friendliness = friendliness;
        this.goal = goal;
        transition = 0;
    }
    EmoBot(){
        transition = 0;
    }
    String geterateOverallEmotion(){
        if(receivedEmoIndex == -1) {
            receivedEmoIndex = 9;
            System.out.println("Unknown Emotion");
        }
        //System.out.println(emoIndex);
        double maxVal = 0 ;
        int maxLevel = -1;
        String state = "neutral";
        for(int i=0;i<3;i++){
            levels[i] += emotionValues[receivedEmoIndex][i] - levelFactors[i]*levels[i];
            levels[i] = min(levels[i],20);
            if(levels[i] != 0) levels[i] -= coolingDownEffect + i*.5;
            levels[i] = max(0,levels[i]);
            if(levels[i]>maxVal){
                maxVal = levels[i];
                maxLevel = i;
            }
        }
        currentLevel = maxLevel;
        //System.out.println("maxLevel: "+maxLevel);
        //for(int i=0;i<3;i++) System.out.println("level"+i+": " + levels[i]);
        if(maxLevel == -1){
            state = "Neutral";
        }
        else if(maxLevel == 0){
            levels[1] = 0;
            levels[2] = 0;
            if(levels[0]<10) state = "Happy";
            else state = "OverJoyed";
        }
        else if(maxLevel == 1){
            levels[0] = 0;
            levels[2] = 0;
            if(levels[1]<8) state = "Worried";
            else state = "Sad";
        }
        else if(maxLevel == 2){
            levels[0] = 0;
            levels[1] = 0;
            if(levels[1]<10) state = "Irritated";
            else state = "Angry";
        }
        return state;
    }

    public String geterateResponse(){
        if(transition==1){
            transition = 0;
            return "Surprise";
        }
        return response.get(responseValue[receivedEmoIndex][currentLevel]);

    }

    public void setReceivedEmoState(String receivedEmoState) {
        this.receivedEmoState = receivedEmoState;
        receivedEmoIndex = emotions.indexOf(receivedEmoState);
        if(prevReceivedEmoState == 8 || prevReceivedEmoState == 9){
            if(receivedEmoIndex<3) transition = 1;
        }
        prevReceivedEmoState = receivedEmoIndex;
    }
}

