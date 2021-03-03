/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkpaint;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author quochung17
 */
public class Library {
    public  JFileChooser jFile;
    public Library(){
        String dir = System.getProperty("user.dir");
        jFile=new JFileChooser(dir+"/Library");
        jFile.setFileFilter(new FileNameExtensionFilter("All","png","jpeg","jpg"));
    }
    public  void openimage(){
        int a=jFile.showOpenDialog(bkbkpaint.DrawPanel);
        if(a==JFileChooser.APPROVE_OPTION){
            try {
                BufferedImage OpImg = ImageIO.read(jFile.getSelectedFile());
                bkbkpaint.DrawPanel.setimage(scaleImage.scale(OpImg,1900,800));
            } catch (IOException ex) {
                Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
