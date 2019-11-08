
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author andre
 */
public class CropPolygon {
    
    private int[] pointsX;
    private int[] pointsY;
    private int nPoints;
    
    public CropPolygon(int[] pointsX, int[] pointsY,int nPoints){
        
        this.pointsX = pointsX;
        this.pointsY = pointsY;
        this.nPoints = nPoints;
    }
        
    public void paint(Graphics g){
    
          g.setColor(Color.BLUE);
          g.fillPolygon(pointsX, pointsY,this.nPoints);
    }
    
    public Polygon toPolygon(){
    
        return new Polygon(this.pointsX,this.pointsY,3);
    
    }
    
    
    

}
