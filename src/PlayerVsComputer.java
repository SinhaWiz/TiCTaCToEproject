
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
class PlayerVsComputer extends TicTacToeGame {
    public PlayerVsComputer(JFrame frame, JLabel textfield) {
        super(frame, textfield);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (player1Turn && buttons[i].getText().equals("")) {
                    buttons[i].setForeground(new Color(164, 222, 108));
                    buttons[i].setText("X");
                    player1Turn = false;
                    textfield.setText("O turn");
                    check();
                    if (!isBoardFull() && !checkWin("X")) {
                        computerMove();
                    }
                }
            }
        }
    }

    public void computerMove() {
        int bestScore = Integer.MIN_VALUE;
        int move = -1;

        for (int i = 0; i < 9; i++) {
            if (buttons[i].getText().equals("")) {
                buttons[i].setText("O");
                int score = minimax(buttons, 0, false);
                buttons[i].setText("");
                if (score > bestScore) {
                    bestScore = score;
                    move = i;
                }
            }
        }
        buttons[move].setForeground(new Color(87, 87, 189));
        buttons[move].setText("O");
        player1Turn = true;
        textfield.setText("X turn");
        check();
    }

    private int minimax(JButton[] board, int depth, boolean isMaximizing) {
        if (checkWin("O")) {
            return 1;
        }
        if (checkWin("X")) {
            return -1;
        }
        if (isBoardFull()) {
            return 0;
        }

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 9; i++) {
                if (board[i].getText().equals("")) {
                    board[i].setText("O");
                    int score = minimax(board, depth + 1, false);
                    board[i].setText("");
                    bestScore = Math.max(score, bestScore);
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 9; i++) {
                if (board[i].getText().equals("")) {
                    board[i].setText("X");
                    int score = minimax(board, depth + 1, true);
                    board[i].setText("");
                    bestScore = Math.min(score, bestScore);
                }
            }
            return bestScore;
        }
    }
}
