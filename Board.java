import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
public class Board extends JPanel
{
    TicTacToe t = new TicTacToe();
    int row;
    int column;
    Player[][] players = new Player[3][3];  
    String letter = "";
    int counter = 0;
    Scanner in = new Scanner(System.in);
    //create a 3X3 grid
    public void paint(Graphics g){
        super.paintComponent(g);

        createGrid(g);

        drawPlayersInGrid(g);

        if(checkForWin()){
            showEndScreen(g);
        }
    }

    private void showEndScreen(Graphics g) { 
        g.setColor(Color.black);
        g.fillRect(0, 0, 500, 500);
        g.setColor(Color.cyan);
        Font font = new Font("Apple Chancery", Font.BOLD, 30);
        g.setFont(font);
        if(hasWon("O")){
            g.drawString("GAME OVER: O WINS", 85, 250);
        } 
        if(hasWon("X")){
            g.drawString("GAME OVER: X WINS", 85, 250);
        }
    }

    private void drawPlayersInGrid(Graphics g) { 
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                players[i][j].draw(g);
            }
        }

    }

    private void createGrid(Graphics g) { 
        setBackground(Color.black);
        g.setColor(Color.magenta);
        g.fillRect(500/3 - 5, 0, 10, 500);
        g.fillRect((500/3)*2 - 5, 0, 10, 500);
        g.fillRect(0, 500/3 -5, 500, 10);
        g.fillRect(0, (500/3)*2, 500, 10);  
    }

    public void input(){
        if (checkForWin()) { 
            in.close();
        }else { 
            counter++;
            System.out.println("\u000c");
            System.out.println("ENTER A ROW NUMBER BETWEEN 1 AND 3");
            row = in.nextInt();
            System.out.println("ENTER A COLUMN NUMBER BETWEEN 1 AND 3");
            column = in.nextInt();
            if(row < 1 || column < 1 || row > 3 || column > 3){
                counter --;
            }

            if(players[row-1][column-1].row == -100){
                if(counter%2 == 0){
                    players[row-1][column-1] = new Player("X", row, column, Color.red);
                } else if(counter%2 == 1){
                    players[row-1][column-1] = new Player("O", row, column, Color.green);
                }
            }else{
                counter --;
            }
        }

    }

    public void instantiatePlayers(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                players[i][j] = new Player("", -100, -100, Color.white);
            }
        }
    }

    public boolean checkForWin() { 
        return hasWon("X") || hasWon("O");
    }

    private boolean hasWon(String xo) { 
        return rowColumnCheck(xo) || diagonalCheck(xo);
    }

    private boolean rowCheck( String xo) { 

        boolean result = true ; 
        for ( int row = 0 ; row < 3 ; row ++ ) {
            for ( int col = 0; col < 3 ; col ++ ) { 
                result = result && players[row][col].letter.equals(xo) ;
            }
            if (result) 
                break;
        }
        return result ; 
    }

    private boolean columnCheck( String xo) { 
        boolean result = true ; 
        for ( int col = 0 ; col < 3 ; col ++ ) {
            for ( int row = 0; row < 3 ; row ++ ) { 
                result = result && players[row][col].letter.equals(xo) ;
            }
            if (result) 	
                break;
        }
        return result ; 
    }

    private boolean rowColumnCheck(String xo) { 
        boolean result ; 
        result = rowCheck(xo) || columnCheck(xo);
        return result ; 
    }

    private boolean diagonalCheck(String xo) { 
        boolean result = true ; 
        for ( int i = 0; i < 3 ; i ++ ) { 
            result = result && players[i][i].letter.equals(xo);
        }
        if(result){//left diagonal check
            return result;   
        }
        result = true;
        int counter = 2;
        for (int i = 0; i < 3; i++){
            result = result && players[i][counter].letter.equals(xo);
            counter--;
        }
        return result ; 
    }
}

