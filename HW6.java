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
//        // TODO code application logic here
        long startTime = 0, endTime = 0;

//instance 1 of hw
        Grid grid1 = new Grid(5, 5);
        Grid solvedGrid1 = null;
        PuzzleSolver solver = null;
        try{
            grid1.setConstraint(1, 5, 0);
            grid1.setConstraint(3, 4, 1);
            solver = new PuzzleSolver(Grid.copyGrid(grid1));
            int[] assignments = new int[grid1.numColumns];
            startTime = System.currentTimeMillis();
            assignments = solver.solve();
            endTime = System.currentTimeMillis();
            if(!solver.failure){
                solvedGrid1 = solver.solvedGrid;
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        //Grid.printGrid(grid1);
        Grid.printGrid(solvedGrid1);
        if(solver != null){
            System.out.println("Time taken: " + (endTime - startTime) + " ms");
            System.out.println("Nodes expanded: " + solver.numNodesExpanded + "\n\n");
        }

//instance 2 of hw
        Grid grid2 = new Grid(6, 6);
        Grid solvedGrid2 = null;
        try{
            grid2.setConstraint(2, 6, 0);
            grid2.setConstraint(4, 5, 2);
            solver = new PuzzleSolver(Grid.copyGrid(grid2));
            int[] assignments = new int[grid2.numColumns];
            startTime = System.currentTimeMillis();
            assignments = solver.solve();
            endTime = System.currentTimeMillis();
            if(!solver.failure){
                solvedGrid2 = solver.solvedGrid;
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        //Grid.printGrid(grid2);
        Grid.printGrid(solvedGrid2);
        if(solver != null){
            System.out.println("Time taken: " + (endTime - startTime) + " ms");
            System.out.println("Nodes expanded: " + solver.numNodesExpanded + "\n\n");
        }
        
//instance 3 of hw
        Grid grid3 = new Grid(6, 6);
        Grid solvedGrid3 = null;
        try{
            grid3.setConstraint(1, 6, 1);
            grid3.setConstraint(4, 3, 1);
            grid3.setConstraint(6, 4, 2);
            solver = new PuzzleSolver(Grid.copyGrid(grid3));
            int[] assignments = new int[grid3.numColumns];
            startTime = System.currentTimeMillis();
            assignments = solver.solve();
            endTime = System.currentTimeMillis();
            if(!solver.failure){
                solvedGrid3 = solver.solvedGrid;
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        //Grid.printGrid(grid3);
        Grid.printGrid(solvedGrid3);
        if(solver != null){
            System.out.println("Time taken: " + (endTime - startTime) + " ms");
            System.out.println("Nodes expanded: " + solver.numNodesExpanded + "\n\n");
        }
        
//instance 4 of hw
        Grid grid4 = new Grid(7, 7);
        Grid solvedGrid4 = null;
        try{
            grid4.setConstraint(1, 7, 1);
            grid4.setConstraint(4, 6, 0);
            grid4.setConstraint(5, 1, 2);
            solver = new PuzzleSolver(Grid.copyGrid(grid4));
            int[] assignments = new int[grid4.numColumns];
            startTime = System.currentTimeMillis();
            assignments = solver.solve();
            endTime = System.currentTimeMillis();
            if(!solver.failure){
                solvedGrid4 = solver.solvedGrid;
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        //Grid.printGrid(grid4);
        Grid.printGrid(solvedGrid4);
        if(solver != null){
            System.out.println("Time taken: " + (endTime - startTime) + " ms");
            System.out.println("Nodes expanded: " + solver.numNodesExpanded + "\n\n");
        }
    }
}
