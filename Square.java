/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw6;

/**
 *
 * @author csb5h4
 */
public class Square {
    private int x;
    private int y;
    private Boolean isShaded;
    private Integer constraint;
    
    public Square(int x, int y){
        this.x = x;
        this.y = y;
        this.constraint = null;
    }
    
    public Square(int x, int y, Boolean isShaded){
        this(x, y);
        this.isShaded = isShaded;
    }
    
    public Square(int x, int y, int constraint){
        this(x, y);
        this.constraint = constraint;
    }
    
    public Square(int x, int y, int constraint, Boolean isShaded){
        this(x, y, constraint);
        this.isShaded = isShaded;
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    public void setIsShaded(Boolean isShaded){
        this.isShaded = isShaded;
    }
    
    public Boolean getIsShaded(){
        return isShaded;
    }
    
    public void setConstraint(int constraint){
        this.constraint = constraint;
    }
    
    public Integer getConstraint(){
        return constraint;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public String getCoords(){
        return "(" + x + ", " + y + ")";
    }
    
    @Override
    public String toString(){
        return getCoords() + "  shaded:" + isShaded + "   constraint: " + constraint;
    }
}
