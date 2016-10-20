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
public class HW6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Grid grid = new Grid(5, 5);
        try{
            grid.shadeSquare(3, 4);
            grid.setConstraint(1, 2, 3);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        Grid.printGrid(grid);
    }
    
}