/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw6;

import java.util.ArrayList;

/**
 *
 * @author csb5h4
 */
public class Grid{
    private ArrayList<Square> squareCollection;
    private int numRows;
    private int numColumns;
    private int numSquares;
    
    public Grid(int numRows, int numColumns){
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.numSquares = numRows * numColumns;
        squareCollection = new ArrayList();
        for(int i = 1; i <= numRows; i++){
            for(int j = 1; j <= numColumns; j++){
                Square newSquare = new Square(i, j, false);
                squareCollection.add(newSquare);
            }
        }
    }
    
    private Boolean validateIndex(int index) throws Exception{
        Boolean status = index >= 0 && index <= numSquares - 1;
        if(!status){
            throw new Exception("Invalid index of " + index);
        }
        return status;
    }
    
    private Boolean validateCoords(int x, int y) throws Exception{
        Boolean status = false;
        try{
            status = validateX(x) && validateY(y);
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
        return status;
    }
    
    private Boolean validateX(int x) throws Exception{
        Boolean status = x >= 1 && x <= numColumns;
        if(!status){
            throw new Exception("Invalid X value of " + x + ".  Expected: 1 <= x <= " + numColumns);
        }
        return status;
    }
    
    private Boolean validateY(int y) throws Exception{
        Boolean status = y>= 1 && y <= numRows;
        if(!status){
            throw new Exception("Invalid Y value of " + y + ".  Expected: 1 <= y <= " + numRows);
        }
        return status;
    }
    
    private int convertCoordsToIndex(int x, int y) throws Exception{
        try{
            validateCoords(x, y);
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
        return ((y - 1) * numRows) + (x - 1);
    }
    
    public void shadeSquare(int x, int y) throws Exception{
        int index;
        try{
            index = convertCoordsToIndex(x, y);
        }catch(Exception e){
            throw new Exception("Cannot shade square. " + e.getMessage());
        }
        squareCollection.get(index).setIsShaded(Boolean.TRUE);
    }
    
    public void unshadeSquare(int x, int y) throws Exception{
        int index;
        try{
            index = convertCoordsToIndex(x, y);
        }catch(Exception e){
            throw new Exception("Cannot unshade square. " + e.getMessage());
        }
        squareCollection.get(index).setIsShaded(Boolean.FALSE);
    }
    
    public String getSquareCoords(int x, int y) throws Exception{
        int index;
        try{
            index = convertCoordsToIndex(x, y);
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
        return getSquareCoords(index);
    }
    
    private String getSquareCoords(int index) throws Exception{
        try{
            validateIndex(index);
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
        return squareCollection.get(index).getCoords();
    }
    
    public String getSquareInfoString(int x, int y) throws Exception{
        int index;
        try{
            index = convertCoordsToIndex(x, y);
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
        return getSquareInfoString(index);
    }
    
    private String getSquareInfoString(int index) throws Exception{
        try{
            validateIndex(index);
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
        return squareCollection.get(index).toString();
    }
    
    public void setConstraint(int x, int y, int constraint) throws Exception{
        int index;
        if(constraint < 0 || constraint > 8){
            throw new Exception("Cannot set constraint. Expected 0 < constraint <= 8.  Actual constraint: " + constraint);
        }
        try{
            index = this.convertCoordsToIndex(x, y);
        }catch(Exception e){
            throw new Exception("Cannot set constraint. " + e.getMessage());
        }
        if(squareCollection.get(index).getIsShaded()){
            throw new Exception("Cannot set constraint. Square " + squareCollection.get(index).getCoords()+ " is shaded.");
        }
        squareCollection.get(index).setConstraint(constraint);
    }
    
    public static void printGrid(Grid grid){
        try{
        for(int i = 1; i <= grid.numRows; i++){
            String out = "";
            if(i == 1){
                out = "+";
                for(int j = 0; j < grid.numColumns; j++){
                    out += "---+";
                }
                System.out.println(out);   
            }
            
            out = "|";
            String inner;
            Integer constraint;
            int index;
            for(int j = 1; j <= grid.numColumns; j++){
                index = grid.convertCoordsToIndex(j, i);
                if((constraint = grid.squareCollection.get(index).getConstraint()) != null){
                    out += " " + constraint + " |";
                } else if(grid.squareCollection.get(index).getIsShaded()){
                    out += " X |";
                } else {
                    out += "   |";
                }
            }
            System.out.println(out);
            
            out = "+";
            for(int j = 1; j <= grid.numColumns; j++){
                out += "---+";
            }
            System.out.println(out);
        }
        }catch(Exception e){
            System.out.println("Error printing grid: " + e.getMessage());
        }
    }
}