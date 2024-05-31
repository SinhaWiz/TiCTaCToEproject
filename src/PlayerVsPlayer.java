import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
class PlayerVsPlayer extends TicTacToeGame {
    public PlayerVsPlayer(JFrame frame, JLabel textfield) {
        super(frame, textfield);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (buttons[i].getText().equals("")) {
                    if (player1Turn) {
                        buttons[i].setForeground(new Color(164, 222, 108));
                        buttons[i].setText("X");
                        player1Turn = false;
                        textfield.setText("O turn");
                    } else {
                        buttons[i].setForeground(new Color(87, 87, 189));
                        buttons[i].setText("O");
                        player1Turn = true;
                        textfield.setText("X turn");
                    }
                    check();
                }
            }
        }
    }
}
