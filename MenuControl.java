package bkpaint;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
//import java.util.logging.Level;
//import java.util.logging.Logger;

public class MenuControl extends JMenuBar {
    //các thành phần của menu
    public JMenu File, Home, Help;
    public JMenuItem New, Open, Save, Exit,About;
    public static JFileChooser SelectFile=null;
    public static BufferedImage OpenImage;
    public MenuControl() {
        File = new JMenu("File");
        Home = new JMenu("Home");
        Help = new JMenu("Help");
        New = new JMenuItem("New");
        Open = new JMenuItem("Open");
        Save = new JMenuItem("Save");
        Exit = new JMenuItem("Exit");
        About=new JMenuItem("About");
        KeyStroke keynew=KeyStroke.getKeyStroke(KeyEvent.VK_N,KeyEvent.CTRL_DOWN_MASK);
        KeyStroke keysave=KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.CTRL_DOWN_MASK);
        KeyStroke keyopen=KeyStroke.getKeyStroke(KeyEvent.VK_O,KeyEvent.CTRL_DOWN_MASK);
        KeyStroke keyexit=KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,KeyEvent.CTRL_DOWN_MASK);
        File.add(New);
        File.add(Open);
        File.add(Save);
        File.add(Exit);
        Help.add(About);
        Menuhandler menuhandler = new Menuhandler();
        New.addActionListener(menuhandler);
        Save.addActionListener(menuhandler);
        Exit.addActionListener(menuhandler);
        About.addActionListener(menuhandler);
        Open.addActionListener(menuhandler);
        New.setAccelerator(keynew);
        Save.setAccelerator(keysave);
        Open.setAccelerator(keyopen);
        Exit.setAccelerator(keyexit);
        this.add(File);
        this.add(Home);
        this.add(Help);

    }
    public JFileChooser getFileChooser(){
        if(SelectFile==null){
            SelectFile = new JFileChooser();
            FileNameExtensionFilter filefilter=new FileNameExtensionFilter("All","png","jpeg","jpg");
            SelectFile.setFileFilter(filefilter);
        }
        return SelectFile;
    }

    public static BufferedImage getCurrentImage(Component cpn){
        BufferedImage bimage=new BufferedImage(cpn.getWidth(),cpn.getHeight(),BufferedImage.TYPE_INT_BGR);
        cpn.paint(bimage.getGraphics());
        return bimage;
    }

    class Menuhandler implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == New) {
                BufferedImage bimage = new BufferedImage(bkbkpaint.DrawPanel.getWidth(), bkbkpaint.DrawPanel.getHeight(), BufferedImage.TYPE_INT_ARGB);
                bkbkpaint.DrawPanel.clearImage(bimage);
                bkbkpaint.DrawPanel.setImage(bimage);
            }
            if (e.getSource() == Open) {
                getFileChooser();
                int a= SelectFile.showOpenDialog(bkbkpaint.DrawPanel);
                if(a==JFileChooser.APPROVE_OPTION){
                    try {
                        File i=SelectFile.getSelectedFile();    
                        BufferedImage OpImg = ImageIO.read(SelectFile.getSelectedFile());
                        bkbkpaint.DrawPanel.setimage(OpImg);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null,"open error");
                    }
                }
            }
            if (e.getSource() == Save) {
                getFileChooser();
                int b= SelectFile.showSaveDialog(bkbkpaint.DrawPanel);
                if(b==JFileChooser.APPROVE_OPTION){
                    File f=SelectFile.getSelectedFile();
                    f=new File(f.getAbsolutePath());
                    BufferedImage bimage=getCurrentImage(bkbkpaint.DrawPanel);
                    try {
                        ImageIO.write(bimage,"png",f);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null,"save error");
                    }
                }
           }
            if (e.getSource() == Exit) {
                System.exit(0);
            }
            if (e.getSource() == About) {
                JOptionPane.showMessageDialog(null, "Code by TEAM 10 ");
            }


        }
    }
}
