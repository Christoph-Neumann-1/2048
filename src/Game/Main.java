package Game;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        final Controller controller = new Controller(5);
        final GameScreen game = new GameScreen(5);
        game.setCallback(c -> {
            switch (c) {
                case 'w' -> game.update(controller.update(Controller.Direction.Up));
                case 's' -> game.update(controller.update(Controller.Direction.Down));
                case 'a' -> game.update(controller.update(Controller.Direction.Left));
                case 'd' -> game.update(controller.update(Controller.Direction.Right));
            }
            window.setTitle("Score: "+controller.getScore());
            if(controller.GameOver)
            {
                if(JOptionPane.showOptionDialog(window,"Score: "+controller.getScore()+"\nPlay again?","Game over",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,new Object[]{"Yes","No"},"Yes")==0)
                {
                    controller.reset();
                    game.update(controller.getElements());
                }
                else
                    window.dispose();
            }
        });
        game.update(controller.getElements());
        window.setTitle("Score: "+controller.getScore());
        window.addKeyListener(game);//Adding the key listener to the GameScreen didn't work, probably because I can't focus the JPanel
        window.add(game);
        window.setVisible(true);
        window.pack();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
