import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;


public class Frame extends JFrame {
    public static final Dimension DEFAULT_DIMENSION = new Dimension(700, 700);
    private double definitive_offset, scroll = 0.2d;
    private NoiseGenerator noise;

    public Frame(NoiseGenerator noise) {
        super();
        this.noise = noise;
        this.definitive_offset = noise.getSeed();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(DEFAULT_DIMENSION);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        this.add(draw(), BorderLayout.CENTER);
        this.setVisible(true);
    }


    private JPanel draw() {
        JPanel contentPane = new JPanel() {
            double seed_x  = definitive_offset, temp_offset = 0.1;
            int x1, y1, x2, y2, resolution = DEFAULT_DIMENSION.width;
            ArrayList<Integer> y_history = new ArrayList<>();

            private void init() {
                for (int i = 0; i < resolution; i++) {
                    y1 = (int)(this.getHeight() - (((noise.noise(seed_x) + 1) / 2) * this.getHeight()));
                    y_history.add(y1);
                    seed_x += temp_offset;
                }
            }

            @Override
            protected void paintComponent(Graphics g){
                seed_x = definitive_offset;

                g.setColor(Color.BLACK);
                g.fillRect(0, 0, this.getWidth(), this.getHeight());
                g.setColor(Color.WHITE);

                if (y_history.size() < resolution) {
                    this.init();
                }

                seed_x += temp_offset;
                y_history.add((int)(this.getHeight() - (((noise.noise(seed_x) + 1) / 2) * this.getHeight())));
                y_history.remove(0);

                for (int i = 0; i < resolution - 1; i++) {
                    x1 = i * (DEFAULT_DIMENSION.width / resolution);
                    y1 = y_history.get(i);
                    x2 = (i + 1) * (DEFAULT_DIMENSION.width / resolution);
                    y2 = y_history.get(i + 1);
                    g.drawLine(x1, y1, x2, y2);
                }
            }
        };

        return contentPane;
    }


    public void redraw() {
        definitive_offset += scroll;
        this.repaint();
    }
}
