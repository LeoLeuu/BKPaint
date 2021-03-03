package bkpaint;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class createRepVid {
    int i=0;
    int delay;
    JFrame vdframe;
    ImageIcon img;
    Timer timer;

    public createRepVid(){
        vdframe = new JFrame("Replay video");
        vdframe.setSize(1000,800);
        vdframe.setVisible(true);
        Container container = vdframe.getContentPane();
        JPanel jp2 = new JPanel();
        JLabel jl = new JLabel();
        container.add(jl,BorderLayout.CENTER);
        container.add(jp2,BorderLayout.SOUTH);
        JSlider jsl = new JSlider(0,2000,0);
        JButton jbtn = new JButton("Speed: ");
        jbtn.setBorderPainted(false);
        jp2.add(jbtn);
        jsl.setOrientation(JSlider.HORIZONTAL);
        jsl.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                delay = jsl.getValue();
            }
        });
        jp2.setSize(new Dimension(1000,60));
        jp2.add(jsl);
        JButton playBtn = new JButton("Play");
        playBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer = new Timer(2050- delay, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        img = new ImageIcon(scaleImage.scale(bkbkpaint.DrawPanel.saveframe[i],1000,500));
                        jl.setIcon(img);
                        i++;
                        if (i>DrawPanel.maxFrame) timer.stop();
                    }
                });
                timer.setRepeats(true);
                timer.start();
            }
        });
        jp2.add(playBtn);
    }
}
