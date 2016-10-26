/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_hw6_csb5h4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author csb5h4
 */
public class Grid {

    private ArrayList<Square> squareCollection;
    private int numRows;
    private int numColumns;
    private int numSquares;
    private int[] numShadedPerRow;
    private int[] numShadedPerColumn;
    private Column[] columnAssignments;

    public Grid(int numRows, int numColumns) {
        this.numRows = numRows;
        this.numColumns = numColumns;
        numShadedPerRow = new int[numRows];
        numShadedPerColumn = new int[numColumns];
        columnAssignments = new Column[numColumns + 1]; //0 is not used
        this.numSquares = numRows * numColumns;
        squareCollection = new ArrayList();
        int[] remainingValues = new int[numRows];
        for (int i = 1; i <= numRows; i++) {
            for (int j = 1; j <= numColumns; j++) {
                Square newSquare = new Square(i, j, false);
                squareCollection.add(newSquare);
            }
            remainingValues[i - 1] = i;
        }
        
        for(int i = 1; i < columnAssignments.length; i++){
            columnAssignments[i] = new Column(i, numRows, remainingValues);
        }
    }

    private Boolean validateIndex(int index) throws Exception {
        Boolean status = index >= 0 && index <= numSquares - 1;
        if (!status) {
            throw new Exception("Invalid index of " + index);
        }
        return status;
    }

    private Boolean validateCoords(int x, int y) throws Exception {
        Boolean status = validateX(x) && validateY(y);
        return status;
    }

    private Boolean validateX(int x) throws Exception {
        Boolean status = x >= 1 && x <= numColumns;
        if (!status) {
            throw new Exception("Invalid X value of " + x + ".  Expected: 1 <= x <= " + numColumns);
        }
        return status;
    }

    private Boolean validateY(int y) throws Exception {
        Boolean status = y >= 1 && y <= numRows;
        if (!status) {
            throw new Exception("Invalid Y value of " + y + ".  Expected: 1 <= y <= " + numRows);
        }
        return status;
    }

    private int convertCoordsToIndex(int x, int y) throws Exception {
        validateCoords(x, y);
        return ((x - 1) * numColumns) + (y - 1);
    }

    private Boolean checkNeighborConstraints(int x, int y) throws Exception{
        int index = convertCoordsToIndex(x, y);
        int tempIndex;
        Square tempSquare;
        tempIndex = -1;
        try{
            tempIndex = convertCoordsToIndex(x-1, y-1);
        }catch(Exception e){
            //do nothing
        }
        if(tempIndex >= 0){
            tempSquare = squareCollection.get(tempIndex);
            if(tempSquare.getConstraint() != null && tempSquare.getConstraint() >= Grid.countShadedNeighbors(this, tempSquare)){
                throw new Exception("Constraint on neighbor square " + squareCollection.get(tempIndex).getCoords() + " forbids shading square " + squareCollection.get(index).getCoords());
            }
        }

        tempIndex = -1;
        try{
            tempIndex = convertCoordsToIndex(x-1, y);
        }catch(Exception e){
            //do nothing
        }
        if(tempIndex >= 0){
            tempSquare = squareCollection.get(tempIndex);
            if(tempSquare.getConstraint() != null && tempSquare.getConstraint() >= Grid.countShadedNeighbors(this, tempSquare)){
                throw new Exception("Constraint on neighbor square " + squareCollection.get(tempIndex).getCoords() + " forbids shading square " + squareCollection.get(index).getCoords());
            }
        }

        tempIndex = -1;
        try{
            tempIndex = convertCoordsToIndex(x-1, y+1);
        }catch(Exception e){
            //do nothing
        }
        if(tempIndex >= 0){
            tempSquare = squareCollection.get(tempIndex);
            if(tempSquare.getConstraint() != null && tempSquare.getConstraint() >= Grid.countShadedNeighbors(this, tempSquare)){
                throw new Exception("Constraint on neighbor square " + squareCollection.get(tempIndex).getCoords() + " forbids shading square " + squareCollection.get(index).getCoords());
            }
        }

        tempIndex = -1;
        try{
            tempIndex = convertCoordsToIndex(x, y+1);
        }catch(Exception e){
            //do nothing
        }
        if(tempIndex >= 0){
            tempSquare = squareCollection.get(tempIndex);
            if(tempSquare.getConstraint() != null && tempSquare.getConstraint() >= Grid.countShadedNeighbors(this, tempSquare)){
                throw new Exception("Constraint on neighbor square " + squareCollection.get(tempIndex).getCoords() + " forbids shading square " + squareCollection.get(index).getCoords());
            }
        }

        tempIndex = -1;
        try{
            tempIndex = convertCoordsToIndex(x+1, y+1);
        }catch(Exception e){
            //do nothing
        }
        if(tempIndex >= 0){
            tempSquare = squareCollection.get(tempIndex);
            if(tempSquare.getConstraint() != null && tempSquare.getConstraint() >= Grid.countShadedNeighbors(this, tempSquare)){
                throw new Exception("Constraint on neighbor square " + squareCollection.get(tempIndex).getCoords() + " forbids shading square " + squareCollection.get(index).getCoords());
            }
        }

        tempIndex = -1;
        try{
            tempIndex = convertCoordsToIndex(x+1, y);
        }catch(Exception e){
            //do nothing
        }
        if(tempIndex >= 0){
            tempSquare = squareCollection.get(tempIndex);
            if(tempSquare.getConstraint() != null && tempSquare.getConstraint() >= Grid.countShadedNeighbors(this, tempSquare)){
                throw new Exception("Constraint on neighbor square " + squareCollection.get(tempIndex).getCoords() + " forbids shading square " + squareCollection.get(index).getCoords());
            }
        }

        tempIndex = -1;
        try{
            tempIndex = convertCoordsToIndex(x+1, y-1);
        }catch(Exception e){
            //do nothing
        }
        if(tempIndex >= 0){
            tempSquare = squareCollection.get(tempIndex);
            if(tempSquare.getConstraint() != null && tempSquare.getConstraint() >= Grid.countShadedNeighbors(this, tempSquare)){
                throw new Exception("Constraint on neighbor square " + squareCollection.get(tempIndex).getCoords() + " forbids shading square " + squareCollection.get(index).getCoords());
            }
        }

        tempIndex = -1;
        try{
            tempIndex = convertCoordsToIndex(x, y-1);
        }catch(Exception e){
            //do nothing
        }
        if(tempIndex >= 0){
            tempSquare = squareCollection.get(tempIndex);
            if(tempSquare.getConstraint() != null && tempSquare.getConstraint() <= Grid.countShadedNeighbors(this, tempSquare)){
                throw new Exception("Constraint on neighbor square " + squareCollection.get(tempIndex).getCoords() + " forbids shading square " + squareCollection.get(index).getCoords());
            }
        }
        return true;
    }
    
    public Boolean shadeSquare(int x, int y) throws Exception {
        int index;
        try {
            index = convertCoordsToIndex(x, y);
            //check neighbors for constraints that would prevent shading this square
            this.checkNeighborConstraints(x, y);
            
            columnAssignments[x].makeAssignment(y);
            squareCollection.get(index).setIsShaded(Boolean.TRUE);
            numShadedPerRow[y - 1]++;
            numShadedPerColumn[x - 1]++;
            if(x > 1){
                columnAssignments[x - 1].removeRemainingValues(new int[]{y - 1, y + 1});
            }
            if(x < numColumns){
                columnAssignments[x + 1].removeRemainingValues(new int[]{y - 1, y + 1});
            }
            for(int i = 1; i <= numColumns; i++){
                columnAssignments[i].removeRemainingValues(new int[]{y});
            }
        } catch (Exception e) {
            throw new Exception("Cannot shade square (" + x + ", " + y + "). " + e.getMessage());
        }
        if (numShadedPerRow[y - 1] > 1) {
            return false;
        }
        if (numShadedPerColumn[x - 1] > 1) {
            return false;
        }
        return true;
    }

    public void unshadeSquare(int x, int y) throws Exception {
        int index;
        try {
            index = convertCoordsToIndex(x, y);
            numShadedPerRow[y - 1]--;
            numShadedPerColumn[x - 1]--;
        } catch (Exception e) {
            throw new Exception("Cannot unshade square. " + e.getMessage());
        }
        squareCollection.get(index).setIsShaded(Boolean.FALSE);
    }

    public String getSquareCoords(int x, int y) throws Exception {
        int index = convertCoordsToIndex(x, y);
        return getSquareCoords(index);
    }

    private String getSquareCoords(int index) throws Exception {
        validateIndex(index);
        return squareCollection.get(index).getCoords();
    }

    public String getSquareInfoString(int x, int y) throws Exception {
        int index = convertCoordsToIndex(x, y);
        return getSquareInfoString(index);
    }

    private String getSquareInfoString(int index) throws Exception {
        validateIndex(index);
        return squareCollection.get(index).toString();
    }

    public void setConstraint(int x, int y, int constraint) throws Exception {
        int index;
        if (constraint < 0 || constraint > 8) {
            throw new Exception("Cannot set constraint. Expected 0 < constraint <= 8.  Actual constraint: " + constraint);
        }
        try {
            index = this.convertCoordsToIndex(x, y);
        } catch (Exception e) {
            throw new Exception("Cannot set constraint. " + e.getMessage());
        }
        if (squareCollection.get(index).getIsShaded()) {
            throw new Exception("Cannot set constraint. Square " + squareCollection.get(index).getCoords() + " is shaded.");
        }
        squareCollection.get(index).setConstraint(constraint);
        this.columnAssignments[x].removeRemainingValues(new int[]{y});
    }

    public static Grid copyGrid(Grid grid) throws Exception {
        Grid newGrid = new Grid(grid.numColumns, grid.numRows);
        for (int i = 0; i < grid.numSquares; i++) {
            if (grid.squareCollection.get(i).getIsShaded()) {
                try {
                    newGrid.squareCollection.get(i).setIsShaded(Boolean.TRUE);
                } catch (Exception e) {
                    throw e;
                }
            }
            newGrid.squareCollection.get(i).setConstraint(grid.squareCollection.get(i).getConstraint());
        }
        System.arraycopy(grid.numShadedPerColumn, 0, newGrid.numShadedPerColumn, 0, grid.numColumns);
        System.arraycopy(grid.numShadedPerRow, 0, newGrid.numShadedPerRow, 0, grid.numRows);
        System.arraycopy(grid.columnAssignments, 0, newGrid.columnAssignments, 0, grid.numColumns + 1);
        return newGrid;
    }

    public static void printGrid(Grid grid) {
        try {
            for (int i = 1; i <= grid.numRows; i++) {
                String out = "";
                if (i == 1) {
                    out = "+";
                    for (int j = 0; j < grid.numColumns; j++) {
                        out += "---+";
                    }
                    System.out.println(out);
                }

                out = "|";
                Integer constraint;
                int index;
                for (int j = 1; j <= grid.numColumns; j++) {
                    index = grid.convertCoordsToIndex(j, i);
                    if ((constraint = grid.squareCollection.get(index).getConstraint()) != null) {
                        out += constraint + " " + Grid.countShadedNeighbors(grid, grid.squareCollection.get(index)) + "|";
                    } else if (grid.squareCollection.get(index).getIsShaded()) {
                        out += "X " + Grid.countShadedNeighbors(grid, grid.squareCollection.get(index)) + "|";
                    } else {
                        out += "   |";
                    }
                }
                System.out.println(out);

                out = "+";
                for (int j = 1; j <= grid.numColumns; j++) {
                    out += "---+";
                }
                System.out.println(out);
            }
            System.out.println("RowTracker: " + Arrays.toString(grid.numShadedPerRow));
            System.out.println("ColumnTracker: " + Arrays.toString(grid.numShadedPerColumn));
        } catch (Exception e) {
            System.out.println("Error printing grid: " + e.getMessage());
        }
    }

    public static Boolean checkComplete(Grid grid) {
        //check each column for 1 entry
        if (!Grid.checkColumns(grid)) {
            return false;
        }
        //check each row for 1 entry
        if (!Grid.checkRows(grid)) {
            return false;
        }

        //check each square for proper number of surrounding shaded regions.
        //a square with no constraint, if shaded, should have no surrounding shaded regions
        //a square with a constraint should have exactly the number of shaded regions as the constraint value
        for (int i = 0; i < grid.numSquares; i++) {
            Square currentSquare = grid.squareCollection.get(i);
            if (!Grid.checkConstraintNeighbors(grid, currentSquare)) {
                return false;
            }
            if (!Grid.checkShadeNeighbors(grid, currentSquare)) {
                return false;
            }
        }
        return true;
    }

    public static Boolean checkColumns(Grid grid) {
        for (int i = 0; i < grid.numColumns; i++) {
            if (grid.numShadedPerColumn[i] != 1) {
                return false;
            }
        }
        return true;
    }

    public static Boolean checkRows(Grid grid) {
        for (int i = 0; i < grid.numRows; i++) {
            if (grid.numShadedPerRow[i] != 1) {
                return false;
            }
        }
        return true;
    }

    public static Boolean checkConstraintNeighbors(Grid grid, Square currentSquare) {
        if (currentSquare.getConstraint() != null) {
            if (Grid.countShadedNeighbors(grid, currentSquare) != currentSquare.getConstraint()) {
                return false;
            }
        }
        return true;
    }

    public static Boolean checkShadeNeighbors(Grid grid, Square currentSquare) {
        if (currentSquare.getIsShaded()) {
            if (Grid.countShadedNeighbors(grid, currentSquare) != 0) {
                return false;
            }
        }
        return true;
    }

    public static int countShadedNeighbors(Grid grid, Square currentSquare) {
        int index;
        int count = 0;
        int currentX = currentSquare.getX();
        int currentY = currentSquare.getY();
        try {
            index = grid.convertCoordsToIndex(currentX - 1, currentY - 1);
            if (grid.squareCollection.get(index).getIsShaded()) {
                count++;
            }
        } catch (Exception e) {
            //just skip it and move on
        }
        try {
            index = grid.convertCoordsToIndex(currentX - 1, currentY);
            if (grid.squareCollection.get(index).getIsShaded()) {
                count++;
            }
        } catch (Exception e) {
            //just skip it and move on
        }
        try {
            index = grid.convertCoordsToIndex(currentX - 1, currentY + 1);
            if (grid.squareCollection.get(index).getIsShaded()) {
                count++;
            }
        } catch (Exception e) {
            //just skip it and move on
        }
        try {
            index = grid.convertCoordsToIndex(currentX, currentY + 1);
            if (grid.squareCollection.get(index).getIsShaded()) {
                count++;
            }
        } catch (Exception e) {
            //just skip it and move on
        }
        try {
            index = grid.convertCoordsToIndex(currentX + 1, currentY + 1);
            if (grid.squareCollection.get(index).getIsShaded()) {
                count++;
            }
        } catch (Exception e) {
            //just skip it and move on
        }
        try {
            index = grid.convertCoordsToIndex(currentX + 1, currentY);
            if (grid.squareCollection.get(index).getIsShaded()) {
                count++;
            }
        } catch (Exception e) {
            //just skip it and move on
        }
        try {
            index = grid.convertCoordsToIndex(currentX + 1, currentY - 1);
            if (grid.squareCollection.get(index).getIsShaded()) {
                count++;
            }
        } catch (Exception e) {
            //just skip it and move on
        }
        try {
            index = grid.convertCoordsToIndex(currentX, currentY - 1);
            if (grid.squareCollection.get(index).getIsShaded()) {
                count++;
            }
        } catch (Exception e) {
            //just skip it and move on
        }
        return count;
    }
    
    public int[] getColumnsMRV(){
        int min = Integer.MAX_VALUE;
        int[] mrvs = new int[numColumns];
        int count = 0;
        for(int i = 1; i <= this.numColumns; i++){
            mrvs[i-1] = this.columnAssignments[i].numRemainingValues;
            if(this.columnAssignments[i].numRemainingValues < min){
                min = this.columnAssignments[i].numRemainingValues;
                count++;
            }
        }
        if(min < 1){
            return new int[]{-1};
        }
        Arrays.sort(mrvs);
        int[] columns = new int[numColumns];
        //int columnIndex = 0;
        for(int i = 0; i < this.numColumns; i++){
            for(int j = 1; j <= this.numColumns; j++){
                if(this.columnAssignments[j].numRemainingValues == mrvs[i]){
                    columns[i] = j;
                }
            }
        }
        return columns;
    }
    
    public int getDegree(int column){
        int degree = 0;
        //if the column behind is not assigned yet, add 1
        if(column > 1 && columnAssignments[column - 1].assigned == false){
            degree++;
        }
        //if the column ahead is not assigned yet, add 1
        if(column < numColumns && columnAssignments[column + 1].assigned == false){
            degree++;
        }
        return degree;
    }
}
