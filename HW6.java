/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_hw6_csb5h4;

/**
 *
 * @author csb5h4
 */
public class AI_HW6_csb5h4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Grid grid1 = new Grid(5, 5);
        Grid solvedGrid1 = null;
        try{
            //grid.shadeSquare(3, 4);
            grid1.setConstraint(1, 5, 0);
            grid1.setConstraint(3, 4, 1);
//            grid1.shadeSquare(3, 5);
//            grid1.shadeSquare(4, 2);
//            grid1.shadeSquare(2, 1);
//            grid1.shadeSquare(5, 4);
//            grid1.shadeSquare(1, 3);
            
            PuzzleSolver solver = new PuzzleSolver(Grid.copyGrid(grid1));
            if(solver.solve()){
                solvedGrid1 = solver.solvedGrid;
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        Grid.printGrid(grid1);
        Grid.printGrid(solvedGrid1);

        
//        Grid grid2 = new Grid(6, 6);
//        try{
//            grid2.setConstraint(2, 6, 0);
//            grid2.setConstraint(4, 5, 2);
//            Grid.printGrid(grid2);
//            solver.setGrid(grid2);
//            Grid solvedGrid2 = solver.solve();
//            Grid.printGrid(solvedGrid2);
//        }catch(Exception e){
//            System.out.println(e.getMessage());
//        }
//        
//        Grid grid3 = new Grid(6, 6);
//        try{
//            grid3.setConstraint(1, 6, 1);
//            grid3.setConstraint(4, 3, 1);
//            grid3.setConstraint(6, 4, 2);
//            Grid.printGrid(grid3);
//            solver.setGrid(grid3);
//            Grid solvedGrid3 = solver.solve();
//            Grid.printGrid(solvedGrid3);
//        }catch(Exception e){
//            System.out.println(e.getMessage());
//        }
//        
//        Grid grid4 = new Grid(7, 7);
//        try{
//            grid4.setConstraint(1, 7, 1);
//            grid4.setConstraint(4, 6, 0);
//            grid4.setConstraint(5, 1, 2);
//            Grid.printGrid(grid4);
//            solver.setGrid(grid4);
//            Grid solvedGrid4 = solver.solve();
//            Grid.printGrid(solvedGrid4);
//        }catch(Exception e){
//            System.out.println(e.getMessage());
//        }
    }
}
