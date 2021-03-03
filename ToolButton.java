package bkpaint;
import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolButton extends JButton implements ActionListener {
    public Icon icon;
    public Tools tool;
    public ToolButton(Icon icon1,Tools tool1){
        super(icon1);
        //this.icon=icon1;
        this.tool=tool1;
        addActionListener(this::actionPerformed);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        bkbkpaint.DrawPanel.currentTool=tool;
        //Main.paint.repaint();
        bkbkpaint.PaintApp.repaint();
        //debug iiiiiiiiiiiiiiiiiiiiiiii
        //System.out.println("dang...");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
