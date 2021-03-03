package bkpaint;
import java.awt.*;

public class ToolSerial {
    private static Tools Pencil;
    private static Tools Eraser;
    private static Tools Line;
    private static Tools Oval;
    private static Tools Rect;
    private static Tools currenttool;
    private static Tools Fillcolor;
//    Pencil =1
//    Eraser =2
//    Line=3
//    Oval=4
//    Rect=5
//    Fillcolor=6
    
    public static Tools newTool(int serial){
        switch (serial)
        {
            case 1: if(Pencil ==null) Pencil =new Tools(serial);
            currenttool= Pencil;
            break;
            case 2: if(Eraser==null) Eraser=new Tools(serial);
            currenttool=Eraser;
            break;
            case 3: if(Line==null) Line=new Tools(serial);
            currenttool=Line;
            break;
            case 4: if(Oval==null) Oval=new Tools(serial);
            currenttool=Oval;
            break;
            case 5: if(Rect==null) Rect=new Tools(serial);
            currenttool=Rect;
            break;
            case 6: if(Fillcolor==null) Fillcolor=new Tools(serial);
            currenttool=Fillcolor;
            break;
        }
        return currenttool;
    }
}
