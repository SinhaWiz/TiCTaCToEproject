import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
abstract class TicTacToeGame implements ActionListener {
    protected JFrame frame;
    protected JPanel buttonPanel;
    protected JButton[] buttons;
    protected JLabel textfield;
    protected boolean player1Turn;
    public TicTacToeGame(JFrame frame, JLabel textfield) {
        this.frame = frame;
        this.textfield = textfield;
        buttonPanel = new JPanel();
        buttons = new JButton[9];
        setupBoard();
    }
    private void setupBoard() {
        buttonPanel.setLayout(new GridLayout(3, 3));
        buttonPanel.setBackground(new Color(185, 85, 85));
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(new Font("", Font.BOLD, 120));
            buttons[i].setBackground(new Color(211, 225, 211));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }
    }
    public JPanel getButtonPanel() {
        return buttonPanel;
    }
    public void firstTurn() {
        player1Turn = new Random().nextBoolean();
        if (player1Turn) {
            textfield.setText("X turn");
        } else {
            textfield.setText("O turn");
            if (this instanceof PlayerVsComputer) {
                ((PlayerVsComputer) this).computerMove();
            }
        }
    }
    @Override
    public abstract void actionPerformed(ActionEvent e);
    protected boolean checkWin(String player) {
        return (buttons[0].getText().equals(player) && buttons[1].getText().equals(player) && buttons[2].getText().equals(player)) ||
                (buttons[3].getText().equals(player) && buttons[4].getText().equals(player) && buttons[5].getText().equals(player)) ||
                (buttons[6].getText().equals(player) && buttons[7].getText().equals(player) && buttons[8].getText().equals(player)) ||
                (buttons[0].getText().equals(player) && buttons[3].getText().equals(player) && buttons[6].getText().equals(player)) ||
                (buttons[1].getText().equals(player) && buttons[4].getText().equals(player) && buttons[7].getText().equals(player)) ||
                (buttons[2].getText().equals(player) && buttons[5].getText().equals(player) && buttons[8].getText().equals(player)) ||
                (buttons[0].getText().equals(player) && buttons[4].getText().equals(player) && buttons[8].getText().equals(player)) ||
                (buttons[2].getText().equals(player) && buttons[4].getText().equals(player) && buttons[6].getText().equals(player));
    }
    protected void check() {
        if (checkWin("X")) {
            xWins();
        } else if (checkWin("O")) {
            oWins();
        } else if (isBoardFull()) {
            textfield.setText("DRAW -_- ");
        }
    }
    protected boolean isBoardFull() {
        for (JButton button : buttons) {
            if (button.getText().equals("")) {
                return false;
            }
        }
        return true;
    }
    protected void xWins() {
        textfield.setText("X wins");
        disableButtons();
    }
    protected void oWins() {
        textfield.setText("O wins");
        disableButtons();
    }
    protected void disableButtons() {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }
}

