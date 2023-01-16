import javax.swing.*;

import java.awt.*;


public class Frame extends JFrame {
    public static final Dimension DEFAULT_DIMENSION = new Dimension(700, 700);
    private NoiseGenerator noise;
    private double definitive_offset, scroll = 0.2d;

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

            @Override
            protected void paintComponent(Graphics g){
                double seed_x = definitive_offset, temp_offset = 0.1;
                int x, y, resolution = 700;
                int prev_x = 0;
                int prev_y = (int)(this.getHeight() - (((noise.noise(seed_x) + 1) / 2) * this.getHeight()));

                g.setColor(Color.BLACK);
                g.fillRect(0, 0, this.getWidth(), this.getHeight());
                g.setColor(Color.WHITE);

                for (int i = 0; i < resolution; i++) {
                    x = i * (DEFAULT_DIMENSION.width / resolution);
                    y = (int)(this.getHeight() - (((noise.noise(seed_x) + 1) / 2) * this.getHeight()));
                    g.drawLine(prev_x, prev_y, x, y);
                    seed_x += temp_offset;
                    prev_x = x;
                    prev_y = y;
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
