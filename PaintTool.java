package bkpaint;
import javax.swing.*;
import java.awt.*;

public class PaintTool extends JPanel {
    public ToolButton[] toolbuttonarray;
    private Icon Pen= new ImageIcon(getClass().getResource("icon/pencil.png"));
    private Icon Eraser= new ImageIcon(getClass().getResource("icon/eraser.png"));
    private Icon Line= new ImageIcon(getClass().getResource("icon/line.png"));
    private Icon Oval= new ImageIcon(getClass().getResource("icon/oval.png"));
    private Icon Rect= new ImageIcon(getClass().getResource("icon/rectangle.png"));
    private Icon FillColor= new ImageIcon(getClass().getResource("icon/fillcolor.png"));
    
    public PaintTool(){
        this.setBackground(Color.lightGray);
        this.setPreferredSize(new Dimension(300,120));
        this.setLayout(new GridLayout(2,3));
        toolbuttonarray=new ToolButton[6];
        toolbuttonarray[0]= new ToolButton(Pen,ToolSerial.newTool(1));
        toolbuttonarray[1]= new ToolButton(Eraser,ToolSerial.newTool(2));
        toolbuttonarray[2]= new ToolButton(Line,ToolSerial.newTool(3));
        toolbuttonarray[3]= new ToolButton(Oval,ToolSerial.newTool(4));
        toolbuttonarray[4]= new ToolButton(Rect,ToolSerial.newTool(5));
        toolbuttonarray[5]=new ToolButton(FillColor, ToolSerial.newTool(6));
        for (ToolButton tb1:toolbuttonarray) {
            this.add(tb1);

        }
    }
}
