import java.util.TimerTask;

public class DrawTask extends TimerTask {
    private Frame frame;

    public DrawTask(Frame frame) {
        this.frame = frame;
    }

    @Override
    public void run() {
        frame.redraw();
    }
}
