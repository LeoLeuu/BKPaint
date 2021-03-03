package bkpaint;
import javax.swing.*;
import java.awt.*;

public class PaintApp extends JPanel{
        //public MenuControl menuControl;
        public ColorPalette ColorPalette;
        public PaintTool PaintTool;
        public Stroke Stroke;
        public PaintApp(){
            ColorPalette=new ColorPalette();
            PaintTool=new PaintTool();
            Stroke=new Stroke();
            this.setLayout(new FlowLayout(10,20,10));
            this.add(PaintTool);
            this.add(ColorPalette);
            this.add(Stroke);  
            
    }
}
