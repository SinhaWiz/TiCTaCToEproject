import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class MenuPanel  implements MouseListener {
    private JFrame frame;
    private JPanel menuPanel;
    private JLabel textfield;
    private JButton PvC, PvP, QUIT;
    public JFrame getFrame() {
        return frame;
    }
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
    public MenuPanel() {
        frame = new JFrame();
        menuPanel = new JPanel();
        textfield = new JLabel();
        setupFrame();
        setupMenu();
    }
    private void setupFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(73, 73, 73));
        frame.setVisible(true);
        frame.setResizable(false);
            }
    private void setupMenu() {
        menuPanel.setBackground(new Color(0, 0, 0));
        menuPanel.setForeground(new Color(45, 227, 211));
        menuPanel.setLayout(new GridLayout(4, 1));
        textfield.setBackground(new Color(40, 38, 38));
        textfield.setForeground(new Color(23, 217, 224));
        textfield.setFont(new Font("Ink Free", Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);
        PvC = createMenuButton(" Player  Vs  Computer ");
        PvP = createMenuButton(" Player  Vs  Player ");
        QUIT = createMenuButton("  QUIT  ");
        PvC.addMouseListener(this);
        PvP.addMouseListener(this);
        QUIT.addMouseListener(this);
        menuPanel.add(textfield);
        menuPanel.add(PvC);
        menuPanel.add(PvP);
        menuPanel.add(QUIT);
        frame.add(menuPanel);
        PvP.addActionListener(e -> startGame(new PlayerVsPlayer(frame, textfield)));
        PvC.addActionListener(e -> startGame(new PlayerVsComputer(frame, textfield)));
        QUIT.addActionListener(e -> System.exit(0));
    }
    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("MV Bola", Font.BOLD, 40));
        button.setBackground(new Color(215, 232, 208));
        button.setForeground(new Color(196, 16, 109));
        button.setPreferredSize(new Dimension(500, 200));
        return button;
    }
    private void startGame(TicTacToeGame game) {
        frame.remove(menuPanel);
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(textfield, BorderLayout.NORTH);
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(game.getButtonPanel());
        frame.revalidate();
        frame.repaint();
        game.firstTurn();
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    @Override
    public void mouseExited(MouseEvent e) {
        frame.setCursor(Cursor.getDefaultCursor());
    }
}
