package bkpaint;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Stroke extends JPanel {
    public JButton stroke1,stroke2,stroke4,stroke8;
    Icon s1=new ImageIcon(getClass().getResource("icon/stroke1.png"));
    Icon s2=new ImageIcon(getClass().getResource("icon/stroke2.png"));
    Icon s4=new ImageIcon(getClass().getResource("icon/stroke4.png"));
    Icon s8=new ImageIcon(getClass().getResource("icon/stroke8.png"));
    public Stroke(){
        StrokeHandler strokehandler=new StrokeHandler();
        setSize(300,100);
        stroke1=new JButton(s1);
        stroke2=new JButton(s2);
        stroke4=new JButton(s4);
        stroke8=new JButton(s8);
        stroke1.addActionListener(strokehandler);
        stroke2.addActionListener(strokehandler);
        stroke4.addActionListener(strokehandler);
        stroke8.addActionListener(strokehandler);
        this.add(stroke1);
        this.add(stroke2);
        this.add(stroke4);
        this.add(stroke8);


    }
    private class StrokeHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==stroke1){
                bkbkpaint.DrawPanel.currentToolAttribute.setStrokeWidth(2);
            }
            if(e.getSource()==stroke2){
                bkbkpaint.DrawPanel.currentToolAttribute.setStrokeWidth(4);
                
            }
            if(e.getSource()==stroke4){
                bkbkpaint.DrawPanel.currentToolAttribute.setStrokeWidth(6);
            }
            if(e.getSource()==stroke8){
                bkbkpaint.DrawPanel.currentToolAttribute.setStrokeWidth(10);
            }

        }
    }
}
