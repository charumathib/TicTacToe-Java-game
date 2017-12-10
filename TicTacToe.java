import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
public class TicTacToe
{
    public static void main (String[] args){
        JFrame theGUI = new JFrame();
        theGUI.setSize(500, 500);
        theGUI.setResizable(false);
        theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = theGUI.getContentPane();
        Board b = new Board();
        pane.add(b);
        theGUI.setVisible(true);
        b.instantiatePlayers();
        while(true){
            b.input();
            b.repaint();
        }
    }

}
