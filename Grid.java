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
    public int numRows;
    public int numColumns;
    private int numSquares;
    private int[] numShadedPerRow;
    private int[] numShadedPerColumn;
    public Column[] columnAssignments;
    public int numLeftToAssign;
    
    public Grid(int numRows, int numColumns) {
        this.numRows = numRows;
        this.numColumns = numColumns;
        numShadedPerRow = new int[numRows];
        numShadedPerColumn = new int[numColumns];
        columnAssignments = new Column[numColumns + 1]; //0 is not used
        this.numSquares = numRows * numColumns;
        this.numLeftToAssign = numColumns;
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

    //check the index and ensure it is within the correct bounds
    private Boolean validateIndex(int index) throws Exception {
        Boolean status = index >= 0 && index <= numSquares - 1;
        if (!status) {
            throw new Exception("Invalid index of " + index);
        }
        return status;
    }

    //check coordinates and ensure they are within the correct bounds
    private Boolean validateCoords(int x, int y) throws Exception {
        Boolean status = validateX(x) && validateY(y);
        return status;
    }

    //check X coordinate to ensure it is within the correct bounds
    private Boolean validateX(int x) throws Exception {
        Boolean status = x >= 1 && x <= numColumns;
        if (!status) {
            throw new Exception("Invalid X value of " + x + ".  Expected: 1 <= x <= " + numColumns);
        }
        return status;
    }

    //check Y coordinate to ensure it is within the correct bounds
    private Boolean validateY(int y) throws Exception {
        Boolean status = y >= 1 && y <= numRows;
        if (!status) {
            throw new Exception("Invalid Y value of " + y + ".  Expected: 1 <= y <= " + numRows);
        }
        return status;
    }

    //take coordinates and convert them into the index used to access a square
    private int convertCoordsToIndex(int x, int y) throws Exception {
        validateCoords(x, y);
        return ((x - 1) * numColumns) + (y - 1);
    }

    //used when shading squares.  Before shading is done, the surrounding squares are checked to see if they have a constraint.
    //if they do, then the number of squares that are shaded around that constraint square are checked to make sure the 
    //constraint won't be violated by shading this square 
    private Boolean checkNeighborConstraints(int x, int y) throws Exception{
        int index = convertCoordsToIndex(x, y);
        int tempIndex;
        Square tempSquare;
        tempIndex = -1;
        try{
            tempIndex = convertCoordsToIndex(x-1, y-1);
        }catch(Exception fallingStar){
            //put it in your pocket
        }
        if(tempIndex >= 0){
            tempSquare = squareCollection.get(tempIndex);
            if(tempSquare.getConstraint() != null && tempSquare.getConstraint() <= Grid.countShadedNeighbors(this, tempSquare)){
                throw new Exception("Constraint on neighbor square " + squareCollection.get(tempIndex).getCoords() + " forbids shading square " + squareCollection.get(index).getCoords());
            }
        }

        tempIndex = -1;
        try{
            tempIndex = convertCoordsToIndex(x-1, y);
        }catch(Exception fallingStar){
            //put it in your pocket
        }
        if(tempIndex >= 0){
            tempSquare = squareCollection.get(tempIndex);
            if(tempSquare.getConstraint() != null && tempSquare.getConstraint() <= Grid.countShadedNeighbors(this, tempSquare)){
                throw new Exception("Constraint on neighbor square " + squareCollection.get(tempIndex).getCoords() + " forbids shading square " + squareCollection.get(index).getCoords());
            }
        }

        tempIndex = -1;
        try{
            tempIndex = convertCoordsToIndex(x-1, y+1);
        }catch(Exception fallingStar){
            //put it in your pocket
        }
        if(tempIndex >= 0){
            tempSquare = squareCollection.get(tempIndex);
            if(tempSquare.getConstraint() != null && tempSquare.getConstraint() <= Grid.countShadedNeighbors(this, tempSquare)){
                throw new Exception("Constraint on neighbor square " + squareCollection.get(tempIndex).getCoords() + " forbids shading square " + squareCollection.get(index).getCoords());
            }
        }

        tempIndex = -1;
        try{
            tempIndex = convertCoordsToIndex(x, y+1);
        }catch(Exception fallingStar){
            //put it in your pocket
        }
        if(tempIndex >= 0){
            tempSquare = squareCollection.get(tempIndex);
            if(tempSquare.getConstraint() != null && tempSquare.getConstraint() <= Grid.countShadedNeighbors(this, tempSquare)){
                throw new Exception("Constraint on neighbor square " + squareCollection.get(tempIndex).getCoords() + " forbids shading square " + squareCollection.get(index).getCoords());
            }
        }

        tempIndex = -1;
        try{
            tempIndex = convertCoordsToIndex(x+1, y+1);
        }catch(Exception fallingStar){
            //put it in your pocket
        }
        if(tempIndex >= 0){
            tempSquare = squareCollection.get(tempIndex);
            if(tempSquare.getConstraint() != null && tempSquare.getConstraint() <= Grid.countShadedNeighbors(this, tempSquare)){
                throw new Exception("Constraint on neighbor square " + squareCollection.get(tempIndex).getCoords() + " forbids shading square " + squareCollection.get(index).getCoords());
            }
        }

        tempIndex = -1;
        try{
            tempIndex = convertCoordsToIndex(x+1, y);
        }catch(Exception fallingStar){
            //put it in your pocket
        }
        if(tempIndex >= 0){
            tempSquare = squareCollection.get(tempIndex);
            if(tempSquare.getConstraint() != null && tempSquare.getConstraint() <= Grid.countShadedNeighbors(this, tempSquare)){
                throw new Exception("Constraint on neighbor square " + squareCollection.get(tempIndex).getCoords() + " forbids shading square " + squareCollection.get(index).getCoords());
            }
        }

        tempIndex = -1;
        try{
            tempIndex = convertCoordsToIndex(x+1, y-1);
        }catch(Exception fallingStar){
            //put it in your pocket
        }
        if(tempIndex >= 0){
            tempSquare = squareCollection.get(tempIndex);
            if(tempSquare.getConstraint() != null && tempSquare.getConstraint() <= Grid.countShadedNeighbors(this, tempSquare)){
                throw new Exception("Constraint on neighbor square " + squareCollection.get(tempIndex).getCoords() + " forbids shading square " + squareCollection.get(index).getCoords());
            }
        }

        tempIndex = -1;
        try{
            tempIndex = convertCoordsToIndex(x, y-1);
        }catch(Exception fallingStar){
            //put it in your pocket
        }
        if(tempIndex >= 0){
            tempSquare = squareCollection.get(tempIndex);
            if(tempSquare.getConstraint() != null && tempSquare.getConstraint() <= Grid.countShadedNeighbors(this, tempSquare)){
                throw new Exception("Constraint on neighbor square " + squareCollection.get(tempIndex).getCoords() + " forbids shading square " + squareCollection.get(index).getCoords());
            }
        }
        return true;
    }
    
    //checks the current square for constraints that would prevent shading it
    //if no constraints, then the square is shaded and forward checking is performed
    public Boolean shadeSquare(int x, int y) throws Exception {
        int index;
        try {
            index = convertCoordsToIndex(x, y);
            //check neighbors for constraints that would prevent shading this square
            this.checkNeighborConstraints(x, y);
            
            columnAssignments[x].makeAssignment(y);
            squareCollection.get(index).setIsShaded(Boolean.TRUE);
            numLeftToAssign--;
            numShadedPerRow[y - 1]++;
            numShadedPerColumn[x - 1]++;
            //remove values from the domain of the appropriate variables
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

    //returns a string representation of the position of the square 
    public String getSquareCoords(int x, int y) throws Exception {
        int index = convertCoordsToIndex(x, y);
        return getSquareCoords(index);
    }

    //returns a string representation of the position of the square
    private String getSquareCoords(int index) throws Exception {
        validateIndex(index);
        return squareCollection.get(index).getCoords();
    }

    //returns a detailed description of the square
    public String getSquareInfoString(int x, int y) throws Exception {
        int index = convertCoordsToIndex(x, y);
        return getSquareInfoString(index);
    }

    //returns a detailed description of the square
    private String getSquareInfoString(int index) throws Exception {
        validateIndex(index);
        return squareCollection.get(index).toString();
    }

    //initializes a square to have a constraint.  also removes this square's value from the appropriate variable
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
        //System.out.println("removing " + y + " from " + x);
        this.columnAssignments[x].removeRemainingValues(new int[]{y});
    }

    //makes an identical grid.  used as "nodes" for testing new assignments
    public static Grid copyGrid(Grid grid) throws Exception {
        Grid newGrid = new Grid(grid.numColumns, grid.numRows);
        newGrid.numLeftToAssign = grid.numLeftToAssign;
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
        for(int i = 1; i <= grid.numColumns; i++){
            System.arraycopy(grid.columnAssignments[i].remainingValues, 0, newGrid.columnAssignments[i].remainingValues, 0, grid.numRows);
            newGrid.columnAssignments[i].numRemainingValues = grid.columnAssignments[i].numRemainingValues;
            newGrid.columnAssignments[i].assigned = grid.columnAssignments[i].assigned;
            newGrid.columnAssignments[i].assignment = grid.columnAssignments[i].assignment;
        }
        return newGrid;
    }
    
    //print out the domain of every variable
    public void printDomains(){
        for(int i = 0; i < numColumns; i++){
            System.out.println("Column " + (i + 1) + ": " + Arrays.toString(columnAssignments[i+1].remainingValues));
        }
    }

    //print a grid in a useful way.
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
                        out += " " + constraint + " "/* + Grid.countShadedNeighbors(grid, grid.squareCollection.get(index))*/ + "|";
                    } else if (grid.squareCollection.get(index).getIsShaded()) {
                        out += " X "/* + Grid.countShadedNeighbors(grid, grid.squareCollection.get(index))*/ + "|";
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
//            System.out.println("RowTracker: " + Arrays.toString(grid.numShadedPerRow));
//            System.out.println("ColumnTracker: " + Arrays.toString(grid.numShadedPerColumn));
        } catch (Exception e) {
            System.out.println("Error printing grid: " + e.getMessage());
        }
    }

    //checks the puzzle to see if all variables have been assigned, all constraints are satisfied
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

    //see if every column has one shaded square
    public static Boolean checkColumns(Grid grid) {
        for (int i = 0; i < grid.numColumns; i++) {
            if (grid.numShadedPerColumn[i] != 1) {
                return false;
            }
        }
        return true;
    }

    //see if every row has one shaded square
    public static Boolean checkRows(Grid grid) {
        for (int i = 0; i < grid.numRows; i++) {
            if (grid.numShadedPerRow[i] != 1) {
                return false;
            }
        }
        return true;
    }

    //if the current square has a constraint, check to see if the number of neighboring
    //shaded squares is equal to the constraint.  if it is, return true. otherwise false
    public static Boolean checkConstraintNeighbors(Grid grid, Square currentSquare) {
        if (currentSquare.getConstraint() != null) {
            if (Grid.countShadedNeighbors(grid, currentSquare) != currentSquare.getConstraint()) {
                return false;
            }
        }
        return true;
    }

    //if the current node is shaded, then no neighbors are allowed to be shaded.
    //this checks that and returns the appropriate boolean
    public static Boolean checkShadeNeighbors(Grid grid, Square currentSquare) {
        if (currentSquare.getIsShaded()) {
            if (Grid.countShadedNeighbors(grid, currentSquare) != 0) {
                return false;
            }
        }
        return true;
    }

    //this counts the number of shaded neighbors to a particular square
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
    
    //finds the column(s) with the lowest amount of remaining values in the domain and returns a list of them
    public int[] getColumnsMRV(){
        int min = Integer.MAX_VALUE;
        int[] mrvs = new int[numColumns];
        int count = 0;
        for(int i = 1; i <= this.numColumns; i++){
            if(this.columnAssignments[i].assigned == false){
                //System.out.println(Arrays.toString(columnAssignments[i].remainingValues));
                //System.out.println("has " + this.columnAssignments[i].numRemainingValues);
                mrvs[count++] = this.columnAssignments[i].numRemainingValues;
                if(this.columnAssignments[i].numRemainingValues > 0 && this.columnAssignments[i].numRemainingValues < min){
                    min = this.columnAssignments[i].numRemainingValues;
                    //count++;
                }
            }
        }
        if(min == Integer.MAX_VALUE){
            return new int[]{-1};
        }
        //System.out.println("before sort: " + Arrays.toString(mrvs));
        Arrays.sort(mrvs);
        //System.out.println("after sort: " + Arrays.toString(mrvs));
        int[] columns = new int[numColumns];
        //int columnIndex = 0;
        Boolean assigned, valueUsed;
        count = 0;
        for(int i = 0; i < this.numColumns; i++){
            assigned = false;
            for(int j = 1; j <= this.numColumns && !assigned; j++){
                if(mrvs[i] > 0 && this.columnAssignments[j].numRemainingValues == mrvs[i] && mrvs[i] == min){
                    valueUsed = false;
                    for(int k = 0; k < i; k++){
                        if(columns[k] == j){
                            valueUsed = true;
                            break;
                        }
                    }
                    if(valueUsed){
                        continue;
                    }
                    columns[count++] = j;
                    assigned = true;
                }
            }
        }
        return columns;
    }
    
    //returns the domain of the specified column
    public int[] getDomain(int column) throws Exception{
        if(0 < column && column <= numColumns){
            int domainIndex = 0;
            int[] domain = new int[this.columnAssignments[column].numRemainingValues];
            for(int i = 0; i < columnAssignments[column].remainingValues.length; i++){
                if(columnAssignments[column].remainingValues[i] > 0){
                    domain[domainIndex++] = columnAssignments[column].remainingValues[i];
                }
            }
            return domain;
        }
        throw new Exception("Invalid column index.  Expected: 1 <= column <= " + numColumns + ".  Actual: " + column);
    }
    
    //finds the next variable based on heuristics
    //first, the variable must have no assigned value
    //second, the variable must have the lowest number of values in its domain (or be tied)
    //in a case of a tie for the second, then third, it must have the highest degree
    public int getUnassignedVar(){
            int[] columns = this.getColumnsMRV();
            //System.out.println("Columns with lowest MRV " + Arrays.toString(columns));
            if(columns[0] == -1){
                //indicates no remaining values for any variable
                //System.out.println("No remaining values for any column");
                return -1;
            }
            int sameCount = 0;
            for(int i = 0; i < columns.length; i++){
                if(columns[i] > 0){
                    sameCount++;
                }
            }
            int[] degrees = new int[sameCount];
            int[] columnsSortedByDegrees = new int[sameCount];
            if(sameCount > 1){
                //sort by degree heuristic
                //find the degrees of each column
                for(int i = 0; i < sameCount; i++){
                    degrees[i] = this.getDegree(columns[i]);
                }
                //System.out.println("degrees: " + Arrays.toString(degrees));
                Arrays.sort(degrees);
                for(int i = 0; i < sameCount; i++){
                    Boolean assigned = false;
                    for(int j = 0; j < sameCount && !assigned; j++){
                        int degreeIndex = sameCount - i - 1;
                        //degreeIndex = i;
                        if(columns[j] > 0 && this.getDegree(columns[j]) == degrees[degreeIndex]){
                            Boolean valueUsed = false;
                            for(int k = 0; k < i; k++){
                                if(columnsSortedByDegrees[k] == columns[j]){
                                    valueUsed = true;
                                    break;
                                }
                            }
                            if(!valueUsed){
                                columnsSortedByDegrees[i] = columns[j];
                                assigned = true;
                            }
                        }
                    }
                }
            } else {
                columnsSortedByDegrees[0] = columns[0];
            }
        return columnsSortedByDegrees[0];
    }
    
    //calculates the degree of a specific column
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
    
    //returns the next assignment that can be made to a specific variable
    public int getNextValidAssignment(int column) throws Exception{
        validateX(column);
        return this.columnAssignments[column].getNextValidAssignment();
    }
    
    //removes the specified value from the domain of the specified column
    public void removeValueFromColumnDomain(int column, int value){
        if(0 < column && column <= numColumns && 0 < value && value <= numRows){
            this.columnAssignments[column].removeRemainingValues(new int[]{value});
        }
    }
}
