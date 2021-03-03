package bkpaint;
import javax.swing.*;   
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ColorButton extends JButton {
    public Color color;
    public boolean select;
    public ColorButton(Color color1){
        select=false;
        this.color=color1;
        setBackground(color);
        addMouseListener(new colorbuttonhandler());

    }
    private class colorbuttonhandler extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            bkbkpaint.PaintApp.ColorPalette.unselectall();
            select=true;
            bkbkpaint.DrawPanel.setCurrentColor(color);
            ColorPalette.SelectColorPanel.setBackground(color);
            bkbkpaint.PaintApp.repaint();
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
}


