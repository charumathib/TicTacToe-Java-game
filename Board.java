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
        g.drawString("GAME OVER: " + (hasWon(X) ? X : O) + " WINS", 85, 250);
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

    
    private void resetGame() { 
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

    private boolean inputInvalid(int i) { 
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

    private boolean hasWon(String xo) { 
        return rowColumnCheck(xo) || diagonalCheck(xo);
    }

    private boolean rowCheck(String xo) { 
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

    private boolean columnCheck( String xo) { 
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

    private boolean rowColumnCheck(String xo) { 
        boolean result; 
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

