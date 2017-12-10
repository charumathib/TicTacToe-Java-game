import javax.swing.*;
import java.awt.*;
public class Player extends JPanel
{
    String letter = "";
    Font font = new Font("Monospaced", Font.BOLD, 70);
    int counter = 0;
    int row = 0;
    int column = 0;
    Color color;
    public Player(String l, int r, int c, Color col){
        row = r;
        column = c;
        letter = l;
        color = col;
    }
    public void draw(Graphics g){
        g.setColor(color);
        g.setFont(font);
        g.drawString(letter, 500/3 * column - 100, 500/3 * row - 50);
    }
    public void setColor(Color c){
        this.color = c;
    }
}
