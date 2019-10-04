
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author andre
 */
public class SnowFlakeFrame extends JFrame implements MouseListener,MouseMotionListener {
    
    private Triangolo a;
    private List<Polygon> polys;
    private List<CropPoint> cropPoints;
    private int pCounter = 0;
    private boolean definePoly = true; 
    
    public SnowFlakeFrame(){
        super("SnowFlake Generator");
        this.setSize(300,400);
        this.setBackground(Color.BLUE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.cropPoints = new ArrayList<CropPoint>();
        this.polys = new ArrayList<Polygon>();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        if(e.getButton() == MouseEvent.BUTTON1){
            
            if(this.definePoly){
                
                CropPoint point = new CropPoint(e.getX(),e.getY());
                
                if(this.pCounter >= 3){
                    if(cropPoints.get(0).contains(e.getX(),e.getY())){
                        this.definePoly = false;
                        point = new CropPoint(cropPoints.get(0).getX(),cropPoints.get(0).getY());
                        
                    }
                }
                cropPoints.add(point);
                cropPoints.get(cropPoints.size()-1).setLastPoint(true);
                for(int i = 0; i<cropPoints.size()-1; i++) {
                        cropPoints.get(i).setLastPoint(false);
                }
                if(this.definePoly == false){
                    for(int i = 0; i<cropPoints.size(); i++) {
                        cropPoints.get(i).poligonDefined(true);
                    }
                }
                this.pCounter++;
                repaint();
            }
        }        
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
    }
    
    @Override
    public void mouseDragged(MouseEvent arg0) {
    }

    @Override
    public void mouseMoved(MouseEvent arg0) {
    }
    
    public void paint(Graphics g){
        
        super.paint(g);
        int coordX = this.getWidth()/4;
        int coordY = this.getHeight()/4;
        g.setColor(Color.gray);
        this.a = new Triangolo(coordX,coordY,this.getWidth()/2,this.getHeight()/2);
        this.a.paint(g);
        int i = 0;
        for(CropPoint p : cropPoints){
            
            p.paint(g);
            if(i >= 1){
                g.setColor(Color.black);
                g.drawLine(cropPoints.get(i).getX(),cropPoints.get(i).getY(),cropPoints.get(i-1).getX(),cropPoints.get(i-1).getY());
            }
            i++;
        }
        
        if(this.definePoly == false){
            int[] pointsX = new int[cropPoints.size()];
            int[] pointsY = new int[cropPoints.size()];
            
            for(int j = 0;j<cropPoints.size();j++) {
                  pointsX[j] = cropPoints.get(j).getX();
                  pointsY[j] = cropPoints.get(j).getY();
            }
            
            Polygon p = new Polygon(pointsX,pointsY,this.cropPoints.size());
            this.polys.add(p);
            this.definePoly = true;
            this.cropPoints.clear();
            this.pCounter = 0;
           
        }
        if(this.polys.size() > 0 ){
            for(int j = 0;j<this.polys.size();j++) {
                this.polys.get(j).paint(g);
            }
        }
    }
    
    public static void main(String[] args){
        
        SnowFlakeFrame b = new SnowFlakeFrame();
        b.setVisible(true);
      
    }
}
