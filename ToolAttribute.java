package bkpaint;
import java.awt.*;

public class ToolAttribute  {
    public int StrokeWidth;
    public Color color;

    public int getStrokeWidth() {
        return StrokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        StrokeWidth = strokeWidth;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ToolAttribute(Color color1, int strokedim){
//        super(option);
        this.color = color1;
        this.StrokeWidth=strokedim;


    }
}
