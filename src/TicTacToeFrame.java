import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class TicTacToeFrame extends JFrame {
    JPanel mainPnl, statsPnl, gamePanel, boardPnl, bottomPnl;
    JButton quitBtn;
    TicTacToeTile[][] board = new TicTacToeTile[3][3];

    JOptionPane pane;

    boolean playerMove = true;
    int numMoves = 0;


    public TicTacToeFrame() {

        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        add(mainPnl);

        boardPnl = new JPanel();
        boardPnl.setLayout(new GridLayout(3, 3));


        mainPnl.add(boardPnl);


        bottomPnl = new JPanel();
        mainPnl.add(bottomPnl, BorderLayout.SOUTH);
        createPanel();
        createButton();

        setTitle("Tic Tac Toe");
        setSize(500, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void createPanel() {
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 3; col++) {
                board[row][col] = new TicTacToeTile(row, col);
                board[row][col].setText(" ");
                board[row][col].setFont(new Font(Font.SERIF, Font.ITALIC, 15));
                int finalRow = row;
                int finalCol = col;
                board[row][col].addActionListener((ActionEvent ae) ->
                {
                    TicTacToeTile tile = (TicTacToeTile) ae.getSource(); //figures out which button was clicked on the tiles
                    if (!tile.getText().equals(" ")) // if tile isn't empty
                    {
                        JOptionPane.showMessageDialog(null, "Illegal move. Please choose an empty square.");
                        return;
                    }
                    // starts with x and switches players
                    if (playerMove) {
                        tile.setText("X");
                    } else {
                        tile.setText("O");
                    }
                    //switch from x to o
                    playerMove = !playerMove;
                    numMoves++;

                    // on move 5, check for win
                    if (numMoves >= 5) {
                        checkWIN();
                    }
                    if (numMoves >= 7) {
                        checkTie();
                    }
                    // if no win has been made by 9, then it's a tie
                    if (numMoves == 9) {
                        JOptionPane.showMessageDialog(null, "Full Board Tie!");
                        restartGame();
                    }


                });

                boardPnl.add(board[row][col]);
            }
    }

    public void createButton() {
        quitBtn = new JButton("Quit");
        quitBtn.setFont(new Font("Serif", Font.BOLD, 14));
        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));
        bottomPnl.add(quitBtn);
    }

    public void checkWIN() {

        // checking row win
        for (int row = 0; row < 3; row++) {
            if (board[row][0].getText().equals(board[row][1].getText())
                    && board[row][1].getText().equals(board[row][2].getText())
                    && !board[row][0].getText().equals(" ")) {
                JOptionPane.showMessageDialog(null, board[row][0].getText() + " row wins!");
                int again = JOptionPane.showConfirmDialog(null, "Would you like to play again?");
                if (again == JOptionPane.YES_OPTION) {
                    resetGame();
                } else {
                    System.exit(0);
                }
                return;
            }
        }
        // checking col win
        for (int col = 0; col < 3; col++) {
            if (board[0][col].getText().equals(board[1][col].getText())
                    && board[1][col].getText().equals(board[2][col].getText())
                    && !board[0][col].getText().equals(" ")) {
                JOptionPane.showMessageDialog(null, board[0][col].getText() + " column wins!");
                int again = JOptionPane.showConfirmDialog(null, "Would you like to play again?");
                if (again == JOptionPane.YES_OPTION) {
                    resetGame();
                } else {
                    System.exit(0);
                }
                return;
            }

        }
        // checking diagonol win
        if (board[0][0].getText().equals(board[1][1].getText())
                && board[1][1].getText().equals(board[2][2].getText())
                && !board[0][0].getText().equals(" ")) {
            JOptionPane.showMessageDialog(null, board[0][0].getText() + " diagonal wins!");
            int again = JOptionPane.showConfirmDialog(null, "Would you like to play again?");
            if (again == JOptionPane.YES_OPTION) {
                resetGame();
            } else {
                System.exit(0);
            }
            return;
        }
        // checking diagonal win
        if (board[0][2].getText().equals(board[1][1].getText())
                && board[1][1].getText().equals(board[2][0].getText())
                && !board[0][2].getText().equals(" ")) {
            JOptionPane.showMessageDialog(null, board[0][2].getText() + " diagonal wins!");
            int again = JOptionPane.showConfirmDialog(null, "Would you like to play again?");
            if (again == JOptionPane.YES_OPTION) {
                resetGame();
            } else {
                System.exit(0);
            }
            return;
        }
    }

    private void checkTie()
    {
        // Check for row ties
        for (int row = 0; row < 3; row++) {
            if (board[row][0].getText().equals("X") ||
                    board[row][1].getText().equals("X") ||
                    board[row][2].getText().equals("X"))
            {

                JOptionPane.showMessageDialog(null, "Game Over Short Tie Game");
                restartGame();
                return;
            }
            if (board[row][0].getText().equals("O") ||
                    board[row][1].getText().equals("O") ||
                    board[row][2].getText().equals("O"))
            {

                JOptionPane.showMessageDialog(null, "Game Over Short Tie Game");
                restartGame();
                return;
            }

        }
        // Now  the columns
        for (int col = 0; col < 3; col++) {
            if (board[0][col].getText().equals("X") ||
                    board[1][col].getText().equals("X") ||
                    board[2][col].getText().equals("X")) {

                JOptionPane.showMessageDialog(null, "Game Over Short Tie Game");
                restartGame();
                return;
            }
            if (board[0][col].getText().equals("O") ||
                    board[1][col].getText().equals("O") ||
                    board[2][col].getText().equals("O")) {

                JOptionPane.showMessageDialog(null, "Game Over Short Tie Game");
                restartGame();
                return;
            }

        }
        // Now check for the diagonals

        if (board[0][0].getText().equals("X") ||
                board[1][1].getText().equals("X") ||
                board[2][2].getText().equals("X")) {
            JOptionPane.showMessageDialog(null, "Game Over Short Tie Game");
            restartGame();
            return;
        }
        if (board[0][0].getText().equals("O") ||
                board[1][1].getText().equals("O") ||
                board[2][2].getText().equals("O")) {
            JOptionPane.showMessageDialog(null, "Game Over Short Tie Game");
            restartGame();
            return;
        }
        if (board[0][2].getText().equals("X") ||
                board[1][1].getText().equals("X") ||
                board[2][0].getText().equals("X")) {
            JOptionPane.showMessageDialog(null, "Game Over Short Tie Game");
            restartGame();
            return;
        }
        if (board[0][2].getText().equals("O") ||
                board[1][1].getText().equals("O") ||
                board[2][0].getText().equals("O"))
        {
            JOptionPane.showMessageDialog(null, "Game Over Short Tie Game");
            restartGame();
            return;
        }
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col].getText().equals("")) {
                    return;
                }
            }

        }

        JOptionPane.showConfirmDialog(null, "Game Tied");
        restartGame();
    }

    private boolean restartGame()
    {
        int again = JOptionPane.showConfirmDialog(null, "Would you like to play again?");
        if(again == JOptionPane.YES_OPTION)
        {
            resetGame();
        }
        else {
           System.exit(0);
        }
        return false;
    }
    private void resetGame()
    {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col].setText(" ");
            }
        }
        numMoves = 0;
        playerMove = true;
    }

}








