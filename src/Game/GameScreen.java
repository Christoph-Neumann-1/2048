package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameScreen extends JPanel implements KeyListener {
    @Override
    public void keyTyped(KeyEvent keyEvent) {
        callback.func(keyEvent.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    class Cell extends JLabel {
        Cell() {
            setHorizontalAlignment(CENTER);
            setVerticalAlignment(CENTER);
            setPreferredSize(new Dimension(100, 100));
            setOpaque(true);
        }

        public void setVal(int val) {
            setText(val != 0 ? Integer.toString(val) : "");
            int n = val != 0 ? (int) (Math.log(val) / Math.log(2)) : 0;
            switch (n) {
                case 0 -> setBackground(Color.WHITE);
                case 1 -> setBackground(Color.YELLOW);
                case 2 -> setBackground(Color.RED);
                case 3 -> setBackground(Color.BLUE);
                case 4 -> setBackground(Color.GREEN);
                case 5 -> setBackground(Color.MAGENTA);
                case 6 -> setBackground(Color.ORANGE);
                case 7 -> setBackground(Color.PINK);
                case 8 -> setBackground(Color.CYAN);
                default -> setBackground(Color.LIGHT_GRAY);
            }
        }
    }

    interface Callback {
        void func(char c);
    }

    Callback callback = c -> {
    };

    Cell[] elements;

    GameScreen(int size) {
        assert size > 0;
        setLayout(new GridLayout(size, size));
        elements = new Cell[size * size];
        for (int i = 0; i < size * size; ++i) {
            elements[i] = new Cell();
            add(elements[i]);
        }
    }

    void update(int[] data) {
        assert data.length == elements.length : "Tried calling Update on Game.GameScreen with incorrect number of elements";
        for (int i = 0; i < elements.length; ++i) {
            elements[i].setVal(data[i]);
        }
    }

    void setCallback(Callback callback) {
        this.callback = callback;
    }
}
