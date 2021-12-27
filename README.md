# Sudoku Combinatorials
Sudoku is a combinatorial number-placement puzzle with a given cell grid that is not completely filled in with numbers. The goal is to fill the remaining, with numbers 1-9, so that each row and column will have only one number of each kind. I approached this problem in several methods with various algorithms. 

The first being the simplest, a backtracking algorithm that tries to solve the puzzle by testing each cell for a valid solution. It tries all possible solutions, if a violation arises, then the algorithm moves back to the previous cell and increases the value of that cell.

Heuristic search to find columns and return a subset of the matrix:
The matrix will have 9^3 rows, one row for every single possible space of every possible number. 9x9 for 9 numbers.
