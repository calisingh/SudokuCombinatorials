import java.util.*;

public class SimpledokuGrid {
	private int nCellsPerSide;
	private int[][] values;

	public SimpledokuGrid(int nCellsPerSide) {
		this.nCellsPerSide = nCellsPerSide;
		values = new int[nCellsPerSide][nCellsPerSide];
	}

	// This is called a "copy constructor". A copy ctor has 1 arg, which is the same
	// type as
	// the current class. The ctor should make the new instance look just like the
	// "source"
	// instance. CAUTION: The source and the current grid should each have their own
	// "values"
	// array. So don't say "this.values = source.values". You have to create a new
	// values
	// array, and copy numbers from source.values into the new array, one at a time.

	public SimpledokuGrid(SimpledokuGrid source) {
		this.nCellsPerSide = source.nCellsPerSide;
		// create a an array for values
		this.values = new int[nCellsPerSide][nCellsPerSide];

		// this copies the source values to the array
		for (int i = 0; i < this.nCellsPerSide; i++) {

			// this copies each row
			for (int j = 0; j < this.nCellsPerSide; j++) {

				// and then this copies individual values
				this.values[i][j] = source.values[i][j];
			}
		}
	}

	//
	// Returns true if the input list contains a repeated value that isn't zero.
	// Call this from isLegal().
	//
	private boolean containsNonZeroRepeats(ArrayList<Integer> ints) {

		// nested loops to check for repeating values
		for (int i = 0; i < ints.size(); i++) {
			for (int j = i + 1; j < ints.size(); j++) {
				// checks for non-zero values
				if (ints.get(i) != 0) {
					// check for repeating values within those no zeros
					if (ints.get(i).equals(ints.get(j))) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean isLegal() {
		// Check all rows. For each row, put the corresponding numbers from
		// the values[][] array into an ArrayList<Integer>. Then pass the array
		// list to containsNonZeroRepeats(). If containsNonZeroRepeats() return true,
		// then this grid isn't legal, so return false.
		for (int row = 0; row < nCellsPerSide; row++) {
			// crate an array list to check non-zero repeats
			ArrayList<Integer> list = new ArrayList<Integer>();
			// puts each values from row to array list
			for (int i = 0; i < nCellsPerSide; i++) {
				list.add(values[row][i]);
			}
			// checks if the list contains repeat
			if (containsNonZeroRepeats(list)) {
				return false;
			}
		}

		// Check all columns. For each column, put the corresponding numbers from
		// the values[][] array into an ArrayList<Integer>. Then pass the array
		// list to containsNonZeroRepeats(). If containsNonZeroRepeats() return true,
		// then this grid isn't legal, so return false.
		for (int col = 0; col < nCellsPerSide; col++) {

			// creates an array list to check non-zero repeats
			ArrayList<Integer> list = new ArrayList<Integer>();

			// puts each values from column to array list
			for (int i = 0; i < nCellsPerSide; i++) {
				list.add(values[i][col]);
			}

			// check if the list contains repeat
			if (containsNonZeroRepeats(list)) {
				return false; //not legal
			}
		}

		// Checks top-left to bottom-right diagonal.
		ArrayList<Integer> top_left = new ArrayList<Integer>();
		for (int i = 0; i < nCellsPerSide; i++) {
			// add diagonal integers to the list
			top_left.add(values[i][i]);
		}
		
		// check if the list contains repeat
		if (containsNonZeroRepeats(top_left)) {
			// grid is not legal
			return false;
		}

		// Check top-right to bottom-left diagonal.
		ArrayList<Integer> top_right = new ArrayList<Integer>();
		for (int i = 0; i < nCellsPerSide; i++) {
			// add diagonal integers to the list
			top_right.add(values[nCellsPerSide - i - 1][i]);
		}
		// check if the list contains repeat
		if (containsNonZeroRepeats(top_right)) {
			return false; // not legal
		}
		return true;
	}

	// Returns true if all members of the values[][] array are non-zero.
	public boolean isFull() {
		// check all rows
		for (int i = 0; i < nCellsPerSide; i++) {
			// check all columns
			for (int j = 0; j < nCellsPerSide; j++) {
				
				// check for zero values
				if (values[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	// Returns the Evaluation corresponding to the state of this grid. The return
	// will be
	// Evaluation.ABANDON, Evaluation.ACCEPT, or Evaluation.CONTINUE. To determine
	// what to
	// return, call isLegal() and isFull().
	public Evaluation evaluate() {
		if (!isLegal()) {
			return Evaluation.ABANDON;
		} else if (isFull()) {
			return Evaluation.ACCEPT;
		} else {
			return Evaluation.CONTINUE;
		}
	}

	// Returns a size=2 array of ints that index the next empty cell in the
	// values[][] array.
	// The 2 ints in the returned array are the first and second indices into the
	// values[][] array.
	// Returns null if this grid is full.
	private int[] getIndicesOfNextEmptyCell() {
		int[] xy = new int[2];

		for (xy[0] = 0; xy[0] < nCellsPerSide; xy[0]++)
			for (xy[1] = 0; xy[1] < nCellsPerSide; xy[1]++)
				if (values[xy[0]][xy[1]] == 0)
					return xy;

		return null;
	}

	//
	// COMPLETE THIS
	//
	//
	// Finds an empty member of values[][]. Returns an array list containing
	// nCellsPerSide grids that look like the
	// current grid, except the empty member contains 1, 2, 3 .... nCellsPerSide.
	//
	//
	// Example: if this grid = 12300
	// 00000
	// 00000
	// 00000
	// 00000
	//
	// Then the returned array list contains:
	//
	// 12310 12320 12330 12340 12350
	// 00000 00000 00000 00000 00000
	// 00000 00000 00000 00000 00000
	// 00000 00000 00000 00000 00000
	// 00000 00000 00000 00000 00000
	//
	ArrayList<SimpledokuGrid> generateNextGrids() {
		// Get next empty cell from this grid
		int[] indicesOfNext = getIndicesOfNextEmptyCell();
		// Creates a list of grids to return
		ArrayList<SimpledokuGrid> nextGrids = new ArrayList<SimpledokuGrid>();
		// Generate some grids and put them in nextGrids. Be sure that every
		// grid is a separate object.
		// creates a loop for each grid from 1 to nCellsPerSide
		for (int i = 0; i < nCellsPerSide; i++) {
			// Create a new grid // not the instance of "this", separate of object
			SimpledokuGrid newGrid = new SimpledokuGrid(this);
			// Fills the first empty cell
			newGrid.values[indicesOfNext[0]][indicesOfNext[1]] = i + 1; // add 1 as index are 0 based
			// Adds new grid to the grid list
			nextGrids.add(newGrid);
		}
		return nextGrids;
	}

	// Use this for debugging if it's helpful.
	public String toString() {
		String s = "";
		for (int j = 0; j < nCellsPerSide; j++) {
			for (int i = 0; i < nCellsPerSide; i++) {
				if (values[j][i] == 0) {
					s += ".";
				} else {
					s += ("" + values[j][i]);
				}
			}
			s += "\n";
		}
		return s;
	}

}
