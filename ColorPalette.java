package bkpaint;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Color.RGBtoHSB;


public class ColorPalette extends JPanel {
    public ColorButton []colorButtons;
    public static JPanel SelectColorPanel;
    public Color[]colorarray;
    //public static Color SelectColor ;
    public JButton editcolor;
    Icon iconedit= new ImageIcon(getClass().getResource("icon/color-wheel.png"));
    public ColorPalette(){
        //**********
        this.setSize(new Dimension(620,140));
        this.setBackground(Color.lightGray);
        colorarray =new Color[20];
        colorButtons=new ColorButton[20];

for(int i = 0; i < 20; i++)
    //tạo mảng 20 màu
        {                                             
            colorarray[i] = Color.getHSBColor((float) i / (float) 20, 0.85f, 1.0f);
        }
        setLayout(new BorderLayout());
        SelectColorPanel=new JPanel();
        SelectColorPanel.setBackground(Color.black);
        SelectColorPanel.setPreferredSize(new Dimension(50,50));
        editcolor =new JButton("Edit Color",iconedit);
        editcolor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==editcolor){
                    
                    Color c=JColorChooser.showDialog(bkbkpaint.PaintApp,"Edit Color",bkbkpaint.DrawPanel.getCurrentColor());
                    if(c != null){
                    bkbkpaint.DrawPanel.setCurrentColor(c);
                    SelectColorPanel.setBackground(c);
                    }
                }
            }
        });
        JPanel palette =new JPanel();

        float a[]=Color.RGBtoHSB(245,247,246,null);
        palette.setBackground(Color.getHSBColor(a[0],a[1],a[2]));

        palette.setLayout(new GridLayout(2,10));
        for(int i=0;i<20;i++){
            colorButtons[i]=new ColorButton(colorarray[i]);
            palette.add(colorButtons[i]);
        }
        this.add(palette,BorderLayout.CENTER);
        this.add(SelectColorPanel ,BorderLayout.LINE_START);
        this.add(editcolor,BorderLayout.LINE_END);

    }
    public void unselectall(){
        for (ColorButton colorbutton1: colorButtons
             ) {
            colorbutton1.select=false;
        }
    }
    public void paintCompoment(Graphics g){
        super.paintComponent(g);
    }
}
