import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
public class Board extends JPanel
{
    int row;
    int column;
    Player[][] players = new Player[3][3];  
    Scanner in = new Scanner(System.in);
    boolean isX = false ; 
    int EMPTY = -100 ;
    String X = "X";
    String O = "O" ;
    boolean alreadyPrinted = false;
    long endtime = 0;
    long startTime = System.nanoTime();
    boolean changeColor = true;
    //create a 3X3 grid
    public void paint(Graphics g){
        super.paintComponent(g);
        createGrid(g);
        drawPlayersInGrid(g);
        if(checkForWin()){
            showEndScreen(g);
        }else if(checkForDraw()){
            showDrawScreen(g);
        }
    }

    public void showEndScreen(Graphics g) { 
        g.setColor(Color.cyan);
        Font font = new Font("Apple Chancery", Font.BOLD, 30);
        g.setFont(font);
        g.drawString("GAME OVER: " + (hasWon(X) ? X : O) + " WINS", 85, 250);

    }

    public void blink(String xo, Color c){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(players[i][j].letter.equals(xo)){
                    players[i][j].setColor(c);
                }
            }
        }

    }

    public void drawPlayersInGrid(Graphics g) { 
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                players[i][j].draw(g);
            }
        }

    }

    public void createGrid(Graphics g) { 
        setBackground(Color.black);
        g.setColor(Color.magenta);
        g.fillRect(500/3 - 5, 0, 10, 500);
        g.fillRect((500/3)*2 - 5, 0, 10, 500);
        g.fillRect(0, 500/3 -5, 500, 10);
        g.fillRect(0, (500/3)*2, 500, 10);  
    }

    public void resetGame() { 
        instantiatePlayers();
        in.reset();
        isX = false ; 
    }

    public void input(){
        if (checkForWin()) { 
            System.out.println("\u000c");
            System.out.println("THANKS FOR PLAYING. HOPE YOU ENJOYED IT ....\n\n");
            System.out.print("DO YOU WANT TO PLAY AGAIN (Y/N)?   ");
            String option = in.next();
            if (option.startsWith("Y") || option.startsWith("y")) { 
                resetGame();
            }else { 
                in.close();
                System.exit(0);
            } 

        }else if(checkForDraw()){
            System.out.println("\u000c");
            System.out.println("THAT WAS A DRAW...\n\n");
            System.out.print("DO YOU WANT TO PLAY AGAIN (Y/N)?   ");
            String option = in.next();
            if (option.startsWith("Y") || option.startsWith("y")) { 
                resetGame();
            }else { 
                in.close();
                System.exit(0);
            } 
        }else { 
            System.out.println("\u000c");
            String xo = isX ? X : O ; 
            System.out.println("Player " + xo + " please choose options below : " +"\n" + "\n");
            System.out.println("ENTER A ROW NUMBER BETWEEN 1 AND 3");
            row = in.nextInt();
            System.out.println("ENTER A COLUMN NUMBER BETWEEN 1 AND 3");
            column = in.nextInt();

            if ( inputInvalid(row) || inputInvalid(column)) { 
                System.out.println("PLEASE CHOOSE VALID VALUES ... ");
                String enter = in.next();
            } else {

                if(players[row-1][column-1].row == EMPTY){
                    players[row-1][column-1] = new Player(xo, row, column, isX ? Color.red:Color.green);
                    isX = !isX ;
                }else{
                    System.out.println("SPOT ALREADY TAKEN... PLEASE TRY AGAIN");
                    String enter = in.next();
                }
            }
        }

    }

    public boolean inputInvalid(int i) { 
        return ( i < 1 || i > 3)  ;
    }

    public void instantiatePlayers(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                players[i][j] = new Player("", EMPTY, EMPTY, Color.white);
            }
        }
    }

    public boolean checkForWin() { 
        return hasWon(X) || hasWon(O);
    }

    public boolean hasWon(String xo) { 
        return rowColumnCheck(xo) || diagonalCheck(xo);
    }

    public boolean rowCheck(String xo) { 
        boolean result = false ; 
        for (int row = 0 ; row < 3 ; row ++ ) {
            result = true ; 
            for (int col = 0; col < 3 ; col ++ ) { 
                result = result && players[row][col].letter.equals(xo) ;
            }
            if (result) 
                break;
        }
        return result ; 
    }

    public boolean columnCheck( String xo) { 
        boolean result = false; 
        for ( int col = 0 ; col < 3 ; col ++ ) {
            result = true ; 
            for ( int row = 0; row < 3 ; row ++ ) { 
                result = result && players[row][col].letter.equals(xo) ;
            }
            if (result) 	
                break;
        }
        return result ; 
    }

    public boolean rowColumnCheck(String xo) { 
        boolean result; 
        result = rowCheck(xo) || columnCheck(xo);
        return result ; 
    }

    public boolean diagonalCheck(String xo) { 
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

    public boolean checkForDraw(){
        int counter = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(players[i][j].row != -100){
                    counter++;
                }
            }
        }
        return counter == 9;
    }
    
    public void showDrawScreen(Graphics g){
        g.setColor(Color.white);
        Font font = new Font("Apple Chancery", Font.BOLD, 30);
        g.setFont(font);
        g.drawString("GAME OVER : DRAW", 85, 250);
    }
}
