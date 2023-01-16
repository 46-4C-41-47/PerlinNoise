import java.util.Timer;

public class Main {
    public static void main(String[] args) {
        float seed = 0f;
        int frameRate = 60;

        NoiseGenerator noise = new NoiseGenerator(seed);
        Frame frame = new Frame(noise);

        Timer timer = new Timer(true);
        DrawTask task = new DrawTask(frame);
        timer.scheduleAtFixedRate(task, 0, 1000/frameRate);
    }
}