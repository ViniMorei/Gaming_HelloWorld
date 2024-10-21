package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Hello, Gaming!");

        GamePanel game_panel = new GamePanel();
        window.add(game_panel);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }
}
