package bkpaint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class DrawPanel extends JPanel implements MouseListener, MouseMotionListener {
    public Image image;
    private int preX, preY;
    private int startX, startY;
    private int mouseX, mouseY;
    private boolean isDrawing;
    private Graphics2D g2d;
    protected boolean IsPressed;
    public Tools currentTool;
    public ToolAttribute currentToolAttribute;
    public ArrayList<Point> listpoint;
    public BufferedImage[] saveframe = new BufferedImage[100];
    public BufferedImage[] savetempimage = new BufferedImage[50];
    public static int numFrame = 0;
    public static int maxFrame = 0;
    public static int stt = 0;
    public static int max = 0;

    public DrawPanel() {

        //this.setPreferredSize(new Dimension(1900, 800));
        addMouseListener(this);
        addMouseMotionListener(this);
        IsPressed = false;
        currentTool = ToolSerial.newTool(1);
        currentToolAttribute = new ToolAttribute(Color.black, 2);
        listpoint = new ArrayList<Point>();
    }

    public void drawShapes(Graphics2D graphics2D, Tools currentTool, int pointX1, int pointY1, int pointX2, int pointY2) {
        if (currentTool.option == 3) {
            graphics2D.setStroke(new BasicStroke(currentToolAttribute.StrokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            graphics2D.drawLine(pointX1, pointY1, pointX2, pointY2);

            repaintRect(pointX1, pointY1, pointX2, pointY2);
            //return;
        }
        if (currentTool.option == 2) {
            graphics2D.setColor(Color.white);
            graphics2D.setStroke(new BasicStroke(currentToolAttribute.StrokeWidth * 5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            graphics2D.drawLine(pointX1, pointY1, pointX2, pointY2);
            repaint();
            //return;
        }
        if (currentTool.option == 1) {
            graphics2D.setStroke(new BasicStroke(currentToolAttribute.StrokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            graphics2D.drawLine(pointX1, pointY1, pointX2, pointY2);
            repaint();
            return;

        }
        int tempX, tempY;
        int weight, height;
        if (pointX1 >= pointX2) {  // pointX2 is left edge
            tempX = pointX2;
            weight = pointX1 - pointX2;
        } else {   // pointX1 is left edge
            tempX = pointX1;
            weight = pointX2 - pointX1;
        }
        if (pointY1 >= pointY2) {  // pointY2 is top edge
            tempY = pointY2;
            height = pointY1 - pointY2;
        } else {   // pointY1 is top edge
            tempY = pointY1;
            height = pointY2 - pointY1;
        }

        if (currentTool.option == 4) {
            graphics2D.setStroke(new BasicStroke(currentToolAttribute.StrokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            graphics2D.drawOval(tempX, tempY, weight, height);
            repaint();
            repaintRect(pointX1, pointY1, pointX2, pointY2);
            return;
        }
        if (currentTool.option == 5) {//


            graphics2D.setStroke(new BasicStroke(currentToolAttribute.StrokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            graphics2D.drawRect(tempX, tempY, weight, height);              //draw rectangle
            repaintRect(pointX1, pointY1, pointX2, pointY2);
            repaint();                                                      //repaint to properly display stroke
            return;
        }
    }

    // dùng để vẽ hình khi kéo thả
    public void repaintRect(int pointX1, int pointY1, int pointX2, int pointY2) {
        int tempX, tempY;
        int weight, height;
        if (pointX1 >= pointX2) {
            tempX = pointX2;
            weight = pointX1 - pointX2;
        } else {
            tempX = pointX1;
            weight = pointX2 - pointX1;
        }
        if (pointY1 >= pointY2) {
            tempY = pointY2;
            height = pointY1 - pointY2;
        } else {
            tempY = pointY1;
            height = pointY2 - pointY1;
        }
        repaint(tempX, tempY, weight + currentToolAttribute.getStrokeWidth(), height + currentToolAttribute.getStrokeWidth());
    }

    public void setimage(BufferedImage image1) {
        image = image1;
        repaint();
    }

    public void setImage(BufferedImage image1) {
        int width = image1.getWidth();
        int height = image1.getHeight();
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        image = createImage(width, height);
        repaint();
        Graphics graphics = image.getGraphics();  
        graphics.setColor(getBackground());
        graphics.fillRect(0, 0, getSize().width, getSize().height);
        graphics.dispose();
    }

    public void clearImage(BufferedImage image) {
        Graphics2D g2 = image.createGraphics();
        g2.setColor(Color.white);
        g2.fillRect(0, 0, image.getWidth(), image.getHeight());
        g2.dispose();
        repaint();
    }

    public Color getCurrentColor() {
        if (currentTool.option != 2) {
            return currentToolAttribute.getColor();
        } else {
            return getBackground();
        }
    }

    public void setCurrentColor(Color clr) {
        currentToolAttribute.setColor(clr);
    }


    //    int i=0;
    public void createblankpanel() {
        if (image == null) {
            image = createImage(getSize().width, getSize().height);
            Graphics g = image.getGraphics();
            g.setColor(Color.white);
            g.fillRect(0, 0, getSize().width, getSize().height);
            g.dispose();
        }
    }
    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        createblankpanel();
        Graphics2D graphics2D = (Graphics2D) grphcs;
        graphics2D.setColor(getCurrentColor());
        graphics2D.drawImage(image, 0, 0, this);
        if (isDrawing && currentTool.option != 1 && currentTool.option != 2) {
            drawShapes(graphics2D, currentTool, startX, startY, mouseX, mouseY);
        }

    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (isDrawing) return;
        preX = startX = e.getX();
        preY = startY = e.getY();
        if (stt == 0 ) {
            saveframe[0] = MenuControl.getCurrentImage(bkbkpaint.DrawPanel);
            savetempimage[0] = MenuControl.getCurrentImage(bkbkpaint.DrawPanel);
        }
        g2d = (Graphics2D) image.getGraphics();
        g2d.setColor(getCurrentColor());
        isDrawing = true;
    }


    @Override
    public void mouseReleased(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        if (currentTool.option != 1 && currentTool.option != 2) {
            drawShapes(g2d, currentTool, startX, startY, mouseX, mouseY);
        }
        isDrawing = false;
        if (currentTool.option == 6) {
            BufferedImage i = (BufferedImage) image;
            Color initialColor = new Color(i.getRGB(e.getX(), e.getY()));
            listpoint.add(new Point(e.getX(), e.getY()));
            if (!initialColor.equals(g2d.getColor())) {
                while (listpoint.size() > 0) {
                    Point p = listpoint.remove(0);
                    Color c = new Color(i.getRGB(p.x, p.y));
                    if ((c.getRed() <= initialColor.getRed() + 40) && (c.getRed() >= initialColor.getRed() - 40) && (c.getBlue() <= initialColor.getBlue() + 40) && (c.getBlue() >= initialColor.getBlue() - 40) && (c.getGreen() <= initialColor.getGreen() + 40) && (c.getGreen() >= initialColor.getGreen() - 40)) {
                        i.setRGB(p.x, p.y, g2d.getColor().getRGB());
                        if (p.x > 0) listpoint.add(new Point(p.x - 1, p.y));       // check điểm ảnh hướng tây 
                        if (p.x < i.getWidth() - 1)
                            listpoint.add(new Point(p.x + 1, p.y));                  // check điểm ảnh hướng đông
                        if (p.y > 0) listpoint.add(new Point(p.x, p.y - 1));        // check điểm ảnh hướng nam
                        if (p.y < i.getHeight() - 1)
                            listpoint.add(new Point(p.x, p.y + 1));                 // check điểm ảnh hướng bắc
                        repaint();
                    }
                }
            }
        }
        numFrame ++;
        saveframe[numFrame] = MenuControl.getCurrentImage(bkbkpaint.DrawPanel);
        maxFrame = numFrame;
        stt++;
        savetempimage[stt] = MenuControl.getCurrentImage(bkbkpaint.DrawPanel);
        max = stt;
        System.out.println(max);
        repaint();
    }


    public void Undo() {
        if (bkbkpaint.isUndo) {
            stt--;
            bkbkpaint.DrawPanel.setimage(savetempimage[stt]);
            System.out.println(stt);
            numFrame ++;
            saveframe[numFrame] = MenuControl.getCurrentImage(bkbkpaint.DrawPanel);
            maxFrame = numFrame;
            repaint();
        }
    else {
            if (stt < max) {
                stt++;
                bkbkpaint.DrawPanel.setimage(savetempimage[stt]);
                numFrame ++;
                saveframe[numFrame] = MenuControl.getCurrentImage(bkbkpaint.DrawPanel);
                maxFrame = numFrame;

            }
            repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (!isDrawing)
            return;

        mouseX = e.getX();
        mouseY = e.getY();

        if (currentTool.option == 1) {
            drawShapes(g2d, ToolSerial.newTool(1), preX, preY, mouseX, mouseY); 

        } else if (currentTool.option == 2) {
            drawShapes(g2d, ToolSerial.newTool(2), preX, preY, mouseX, mouseY);

        } else if (currentTool.option == 3) {

            repaint();
        } else if (currentTool.option == 4) {

            repaint();
        } else if (currentTool.option == 5) {

            repaint();
        }

        preX = mouseX;
        preY = mouseY;

        System.out.println(maxFrame);

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
