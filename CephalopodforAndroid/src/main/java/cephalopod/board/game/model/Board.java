package cephalopod.board.game.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import cephalopod.board.game.model.Cell.Size;
import cephalopod.board.game.model.Cell.Type;

//TODO Add Java Doc comments.
public class Board {
    public static final int ROWS = 5;

    public static final int COLS = 5;
    private int turn = 0;
    private boolean gameOver = false;
    private Cell cells[][] = initial();

    private static Cell[][] initial() {
        Cell result[][] = {
                {new Cell(Type.EMPTY, Size.ZERO), new Cell(Type.EMPTY, Size.ZERO), new Cell(Type.EMPTY, Size.ZERO),
                        new Cell(Type.EMPTY, Size.ZERO), new Cell(Type.EMPTY, Size.ZERO)},
                {new Cell(Type.EMPTY, Size.ZERO), new Cell(Type.RED, Size.FIVE), new Cell(Type.EMPTY, Size.ZERO),
                        new Cell(Type.EMPTY, Size.ZERO), new Cell(Type.EMPTY, Size.ZERO)},
                {new Cell(Type.EMPTY, Size.ZERO), new Cell(Type.EMPTY, Size.ZERO), new Cell(Type.EMPTY, Size.ZERO),
                        new Cell(Type.EMPTY, Size.ZERO), new Cell(Type.EMPTY, Size.ZERO)},
                {new Cell(Type.EMPTY, Size.ZERO), new Cell(Type.EMPTY, Size.ZERO), new Cell(Type.EMPTY, Size.ZERO),
                        new Cell(Type.BLUE, Size.FIVE), new Cell(Type.EMPTY, Size.ZERO)},
                {new Cell(Type.EMPTY, Size.ZERO), new Cell(Type.EMPTY, Size.ZERO), new Cell(Type.EMPTY, Size.ZERO),
                        new Cell(Type.EMPTY, Size.ZERO), new Cell(Type.EMPTY, Size.ZERO)},};

        return result;
    }

    /**
     * Initialize the game in the starting conditions.
     */
    public void reset() {
        this.cells = initial();
    }

	/*
     * Check if the move is valid.
	 * @return true if it is.
	 * false otherwise.
	 * */

    private boolean validMove(int x, int y) {

        if (x < 0 || x >= 5 || y < 0 || y >= 5) {
            return false;
        }
        if (cells[x][y].getType() != Type.EMPTY) {
            return false;
        }
        return true;
    }

    /**
     * @param yourX x of your cell
     * @param yourY y of your cell
     * @param x1
     * @param y1
     * @param x2
     * @param y2    parameters of cells which you want to take.
     * @return new size of the cell which you clicked
     */
    private Size chooseTwoCells(int yourX, int yourY, int x1, int y1, int x2, int y2) {
        int sum = cells[x1][y1].getSize().getValue() + cells[x2][y2].getSize().getValue();
        if (sum <= 6) {
            cells[yourX][yourY].setSize(cells[yourX][yourY].getSize().instanceOf(sum));
        } else {
            cells[yourX][yourY].setSize(Size.ONE);
        }

        return cells[yourX][yourY].getSize();
    }

    /**
     * @param yourX x of your cell
     * @param yourY y of your cell
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param x3
     * @param y3    parameters of cells which you want to take.
     * @return new size of the cell which you clicked
     */
    private Size chooseThreeCells(int yourX, int yourY, int x1, int y1, int x2, int y2, int x3, int y3) {
        int sum = cells[x1][y1].getSize().getValue() + cells[x2][y2].getSize().getValue()
                + cells[x3][y3].getSize().getValue();
        if (sum <= 6) {
            cells[yourX][yourY].setSize(cells[yourX][yourY].getSize().instanceOf(sum));
        } else {
            cells[yourX][yourY].setSize(Size.ONE);
        }
        return cells[yourX][yourY].getSize();
    }

    /**
     * @param yourX x of your cell
     * @param yourY y of your cell
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param x3
     * @param y3
     * @param x4
     * @param y4    parameters of cells which you want to take.
     * @return new size of the cell which you clicked
     */
    private Size chooseFourCells(int yourX, int yourY, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        int sum = cells[x1][y1].getSize().getValue() + cells[x2][y2].getSize().getValue() + cells[x3][y3].getSize().getValue() + cells[x4][y4].getSize().getValue();
        if (sum <= 6) {
            cells[yourX][yourY].setSize(cells[yourX][yourY].getSize().instanceOf(sum));
        } else {
            cells[yourX][yourY].setSize(Size.ONE);
        }
        return cells[yourX][yourY].getSize();
    }

    /**
     * @param x    x of your cell
     * @param y    y of your cell
     * @param type type of your cell(RED or BLUE)
     * @return true if the click is valid and calculate the size of the cell
     * false otherwise
     */
    public boolean click(int x, int y, Type type) {
        cells[x][y].setType(type);
        cells[x][y].showType();
        // for cell[0][0]
        if (validMove(0, 0) && cells[x][y + 1].getType() == Type.EMPTY || cells[x + 1][y].getType() == Type.EMPTY) {
            cells[0][0].setSize(Size.ONE);
            return true;
        }
        if (validMove(0, 0) && cells[x][y + 1].getType() != Type.EMPTY && cells[x + 1][y].getType() != Type.EMPTY) {
            cells[0][0].setSize(chooseTwoCells(x, y, x + 1, y, x, y + 1));
            return true;
        }
        // for cell[4][0];
        if (validMove(4, 0) && cells[x][y + 1].getType() == Type.EMPTY || cells[x - 1][y].getType() == Type.EMPTY) {
            cells[4][0].setSize(Size.ONE);
            return true;
        }
        if (validMove(4, 0) && cells[x][y + 1].getType() != Type.EMPTY && cells[x - 1][y].getType() != Type.EMPTY) {
            cells[4][0].setSize(chooseTwoCells(x, y, x, y + 1, x - 1, y));
            return true;
        }
        // for cell[4][4];
        if (validMove(4, 4) && cells[x][y - 1].getType() == Type.EMPTY || cells[x - 1][y].getType() == Type.EMPTY) {
            cells[4][4].setSize(Size.ONE);
            return true;
        }
        if (validMove(4, 4) && cells[x][y - 1].getType() != Type.EMPTY && cells[x - 1][y].getType() != Type.EMPTY) {
            cells[4][4].setSize(chooseTwoCells(x, y, x, y - 1, x - 1, y));
            return true;
        }
        // for cell[0][4]
        if (validMove(0, 4) && cells[x][y - 1].getType() == Type.EMPTY || cells[x + 1][y].getType() == Type.EMPTY) {
            cells[0][4].setSize(Size.ONE);
            return true;
        }
        if (validMove(0, 4) && cells[x][y - 1].getType() != Type.EMPTY && cells[x + 1][y].getType() != Type.EMPTY) {
            cells[0][4].setSize(chooseTwoCells(x, y, x, y - 1, x + 1, y));
            return true;
        }
        // for cells on column - 0 except [0][0] and [4][0];

        Scanner sc = new Scanner(System.in);
        if (x >= 1 && x <= 3 && y == 0) {
            if (validMove(x, y) && (cells[x - 1][y].getType() == Type.EMPTY && cells[x][y + 1].getType() == Type.EMPTY) || cells[x + 1][y].getType() == Type.EMPTY) {
                cells[x][y].setSize(Size.ONE);
                return true;
            }
            if (validMove(x, y) && (cells[x + 1][y].getType() == Type.EMPTY && cells[x][y + 1].getType() == Type.EMPTY) || cells[x - 1][y].getType() == Type.EMPTY) {
                cells[x][y].setSize(Size.ONE);
                return true;
            }
            if (validMove(x, y) && (cells[x + 1][y].getType() == Type.EMPTY && cells[x - 1][y].getType() == Type.EMPTY) || cells[x][y + 1].getType() == Type.EMPTY) {
                cells[x][y].setSize(Size.ONE);
                return true;
            }
            if (validMove(x, y) && cells[x + 1][y].getType() != Type.EMPTY && cells[x - 1][y].getType() != Type.EMPTY && cells[x][y + 1].getType() == Type.EMPTY) {
                cells[x][y].setSize(chooseTwoCells(x, y, x + 1, y, x - 1, y));
                return true;
            }
            if (validMove(x, y) && cells[x + 1][y].getType() != Type.EMPTY && cells[x][y + 1].getType() != Type.EMPTY && cells[x - 1][y].getType() == Type.EMPTY) {
                cells[x][y].setSize(chooseTwoCells(x, y, x + 1, y, x, y + 1));
                return true;
            }
            if (validMove(x, y) && cells[x - 1][y].getType() != Type.EMPTY && cells[x][y + 1].getType() != Type.EMPTY && cells[x + 1][y].getType() == Type.EMPTY) {
                cells[x][y].setSize(chooseTwoCells(x, y, x - 1, y, x, y + 1));
                return true;
            }
            if (validMove(x, y) && cells[x - 1][y].getType() != Type.EMPTY && cells[x][y + 1].getType() != Type.EMPTY && cells[x + 1][y].getType() != Type.EMPTY) {
                System.out.println("Choose how many cells do you want to take - 2 or 3?");
                int n = sc.nextInt();
                if (n == 2) {
                    System.out.println("Choose 2 cells which  do you want ? U-upper ,R-right,D-down");
                    char c = sc.next().charAt(0);
                    char c2 = sc.next().charAt(0);
                    if ((c == 'U' && c2 == 'R') || (c == 'R' && c2 == 'U')) {
                        cells[x][y].setSize(chooseTwoCells(x, y, x - 1, y, x, y + 1));
                    }
                    if ((c == 'U' && c2 == 'D') || (c == 'D' && c2 == 'U')) {
                        cells[x][y].setSize(chooseTwoCells(x, y, x - 1, y, x + 1, y));
                    }
                    if ((c == 'D' && c2 == 'R') || (c == 'R' && c2 == 'D')) {
                        cells[x][y].setSize(chooseTwoCells(x, y, x, y + 1, x + 1, y));
                    } else {
                        System.out.println("You have choosen wrong cells!");
                    }
                }
                if (n == 3) {

                    cells[x][y].setSize(chooseThreeCells(x, y, x, y + 1, x + 1, y, x - 1, y));
                } else {
                    System.out.println("Invalid number of cells!");
                }
                return true;
            }

        }
        //for cells on row - 4 except [4][0] and [4][4];
        if (x == 4 && y >= 1 && y <= 3) {
            if (validMove(x, y) && (cells[x][y - 1].getType() == Type.EMPTY && cells[x - 1][y].getType() == Type.EMPTY) || cells[x][y + 1].getType() == Type.EMPTY) {
                cells[x][y].setSize(Size.ONE);
                return true;
            }
            if (validMove(x, y) && (cells[x][y - 1].getType() == Type.EMPTY && cells[x][y + 1].getType() == Type.EMPTY) || cells[x - 1][y].getType() == Type.EMPTY) {
                cells[x][y].setSize(Size.ONE);
                return true;
            }
            if (validMove(x, y) && (cells[x][y + 1].getType() == Type.EMPTY && cells[x - 1][y].getType() == Type.EMPTY) || cells[x][y - 1].getType() == Type.EMPTY) {
                cells[x][y].setSize(Size.ONE);
                return true;
            }
            if (validMove(x, y) && cells[x][y - 1].getType() != Type.EMPTY && cells[x - 1][y].getType() != Type.EMPTY && cells[x][y + 1].getType() == Type.EMPTY) {
                cells[x][y].setSize(chooseTwoCells(x, y, x, y - 1, x - 1, y));
                return true;
            }
            if (validMove(x, y) && cells[x - 1][y].getType() != Type.EMPTY && cells[x][y + 1].getType() != Type.EMPTY && cells[x][y - 1].getType() == Type.EMPTY) {
                cells[x][y].setSize(chooseTwoCells(x, y, x - 1, y, x, y + 1));
                return true;
            }
            if (validMove(x, y) && cells[x][y - 1].getType() != Type.EMPTY && cells[x][y + 1].getType() != Type.EMPTY && cells[x - 1][y].getType() == Type.EMPTY) {
                cells[x][y].setSize(chooseTwoCells(x, y, x, y - 1, x, y + 1));
                return true;
            }
            if (validMove(x, y) && cells[x][y - 1].getType() != Type.EMPTY && cells[x - 1][y].getType() != Type.EMPTY && cells[x][y + 1].getType() != Type.EMPTY) {
                System.out.println("Choose how many cells do you want to take - 2 or 3?");
                int n = sc.nextInt();
                if (n == 2) {
                    System.out.println("Choose 2 cells which  do you want ? U-upper ,R-right,L-left");
                    char c = sc.next().charAt(0);
                    char c2 = sc.next().charAt(0);
                    if ((c == 'U' && c2 == 'L') || (c == 'L' && c2 == 'U')) {
                        cells[x][y].setSize(chooseTwoCells(x, y, x, y - 1, x - 1, y));
                    }
                    if ((c == 'U' && c2 == 'R') || (c == 'R' && c2 == 'U')) {
                        cells[x][y].setSize(chooseTwoCells(x, y, x - 1, y, x, y + 1));
                    }
                    if ((c == 'L' && c2 == 'R') || (c == 'R' && c2 == 'L')) {
                        cells[x][y].setSize(chooseTwoCells(x, y, x, y - 1, x, y + 1));
                    } else {
                        System.out.println("You have choosen wrong cells!");
                    }
                }
                if (n == 3) {
                    cells[x][y].setSize(chooseThreeCells(x, y, x, y + 1, x, y - 1, x - 1, y));
                } else {
                    System.out.println("Invalid number of cells!");
                }
                return true;
            }

        }
        // for cells from column -4 except [4][4] and [0][4];
        if (x >= 1 && x <= 3 && y == 4) {
            if (validMove(x, y) && (cells[x + 1][y].getType() == Type.EMPTY && cells[x][y - 1].getType() == Type.EMPTY) || cells[x - 1][y].getType() == Type.EMPTY) {
                cells[x][y].setSize(Size.ONE);
                return true;
            }
            if (validMove(x, y) && (cells[x][y - 1].getType() == Type.EMPTY && cells[x - 1][y].getType() == Type.EMPTY) || cells[x + 1][y].getType() == Type.EMPTY) {
                cells[x][y].setSize(Size.ONE);
                return true;
            }
            if (validMove(x, y) && (cells[x - 1][y].getType() == Type.EMPTY && cells[x + 1][y].getType() == Type.EMPTY) || cells[x][y - 1].getType() == Type.EMPTY) {
                cells[x][y].setSize(Size.ONE);
                return true;
            }
            if (validMove(x, y) && cells[x + 1][y].getType() != Type.EMPTY && cells[x][y - 1].getType() != Type.EMPTY && cells[x - 1][y].getType() == Type.EMPTY) {
                cells[x][y].setSize(chooseTwoCells(x, y, x + 1, y, x, y - 1));
                return true;
            }
            if (validMove(x, y) && cells[x][y - 1].getType() != Type.EMPTY && cells[x - 1][y].getType() != Type.EMPTY && cells[x + 1][y].getType() == Type.EMPTY) {
                cells[x][y].setSize(chooseTwoCells(x, y, x, y - 1, x - 1, y));
                return true;
            }
            if (validMove(x, y) && cells[x - 1][y].getType() != Type.EMPTY && cells[x + 1][y].getType() != Type.EMPTY && cells[x][y - 1].getType() == Type.EMPTY) {
                cells[x][y].setSize(chooseTwoCells(x, y, x - 1, y, x + 1, y));
                return true;
            }
            if (validMove(x, y) && cells[x + 1][y].getType() != Type.EMPTY && cells[x][y - 1].getType() != Type.EMPTY && cells[x - 1][y].getType() != Type.EMPTY) {
                System.out.println("Choose how many cells do you want to take - 2 or 3?");
                int n = sc.nextInt();
                if (n == 2) {
                    System.out.println("Choose 2 cells which  do you want ? U-upper ,D-down,L-left");
                    char c = sc.next().charAt(0);
                    char c2 = sc.next().charAt(0);
                    if ((c == 'U' && c2 == 'L') || (c == 'L' && c2 == 'U')) {
                        cells[x][y].setSize(chooseTwoCells(x, y, x, y - 1, x - 1, y));
                    }
                    if ((c == 'U' && c2 == 'D') || (c == 'D' && c2 == 'U')) {
                        cells[x][y].setSize(chooseTwoCells(x, y, x - 1, y, x + 1, y));
                    }
                    if ((c == 'L' && c2 == 'D') || (c == 'D' && c2 == 'L')) {
                        cells[x][y].setSize(chooseTwoCells(x, y, x + 1, y, x, y - 1));
                    } else {
                        System.out.println("You have choosen wrong cells!");
                    }
                }
                if (n == 3) {
                    cells[x][y].setSize(chooseThreeCells(x, y, x + 1, y, x - 1, y, x, y - 1));
                } else {
                    System.out.println("Invalid number of cells!");
                }
                return true;
            }

        }
        //for first row except [0][0] and [0][4]
        if (x == 0 && y >= 1 && y <= 3) {
            if (validMove(x, y) && (cells[x][y - 1].getType() == Type.EMPTY && cells[x + 1][y].getType() == Type.EMPTY) || cells[x][y + 1].getType() == Type.EMPTY) {
                cells[x][y].setSize(Size.ONE);
                return true;
            }
            if (validMove(x, y) && (cells[x + 1][y].getType() == Type.EMPTY && cells[x][y + 1].getType() == Type.EMPTY) || cells[x][y - 1].getType() == Type.EMPTY) {
                cells[x][y].setSize(Size.ONE);
                return true;
            }
            if (validMove(x, y) && (cells[x][y - 1].getType() == Type.EMPTY && cells[x][y + 1].getType() == Type.EMPTY) || cells[x + 1][y].getType() == Type.EMPTY) {
                cells[x][y].setSize(Size.ONE);
                return true;
            }
            if (validMove(x, y) && cells[x][y - 1].getType() != Type.EMPTY && cells[x + 1][y].getType() != Type.EMPTY && cells[x][y + 1].getType() == Type.EMPTY) {
                cells[x][y].setSize(chooseTwoCells(x, y, x, y - 1, x + 1, y));
                return true;
            }
            if (validMove(x, y) && cells[x + 1][y].getType() != Type.EMPTY && cells[x][y + 1].getType() != Type.EMPTY && cells[x][y - 1].getType() == Type.EMPTY) {
                cells[x][y].setSize(chooseTwoCells(x, y, x + 1, y, x, y + 1));
                return true;
            }
            if (validMove(x, y) && cells[x][y - 1].getType() != Type.EMPTY && cells[x][y + 1].getType() != Type.EMPTY && cells[x + 1][y].getType() == Type.EMPTY) {
                cells[x][y].setSize(chooseTwoCells(x, y, x, y - 1, x, y + 1));
                return true;
            }
            if (validMove(x, y) && cells[x + 1][y].getType() != Type.EMPTY && cells[x][y - 1].getType() != Type.EMPTY && cells[x - 1][y].getType() != Type.EMPTY) {
                System.out.println("Choose how many cells do you want to take - 2 or 3?");
                int n = sc.nextInt();
                if (n == 2) {
                    System.out.println("Choose 2 cells which  do you want ? U-upper ,D-down,L-left");
                    char c = sc.next().charAt(0);
                    char c2 = sc.next().charAt(0);
                    if ((c == 'D' && c2 == 'L') || (c == 'L' && c2 == 'D')) {
                        cells[x][y].setSize(chooseTwoCells(x, y, x + 1, y, x, y - 1));
                    }
                    if ((c == 'D' && c2 == 'R') || (c == 'R' && c2 == 'D')) {
                        cells[x][y].setSize(chooseTwoCells(x, y, x + 1, y, x, y + 1));
                    }
                    if ((c == 'L' && c2 == 'R') || (c == 'R' && c2 == 'L')) {
                        cells[x][y].setSize(chooseTwoCells(x, y, x, y - 1, x, y + 1));
                    } else {
                        System.out.println("You have choosen wrong cells!");
                    }
                }
                if (n == 3) {
                    cells[x][y].setSize(chooseThreeCells(x, y, x + 1, y, x, y - 1, x, y + 1));
                } else {
                    System.out.println("Invalid number of cells!");
                }
                return true;
            }

        }
        //for small square 3x3
        if (x >= 1 && x <= 3 && y >= 1 && y <= 3) {
            if (validMove(x, y) && cells[x][y - 1].getType() == Type.EMPTY && cells[x + 1][y].getType() == Type.EMPTY && cells[x][y + 1].getType() == Type.EMPTY && cells[x - 1][y].getType() == Type.EMPTY) {
                cells[x][y].setSize(Size.ONE);
                return true;
            }
            if (validMove(x, y) && cells[x][y - 1].getType() == Type.EMPTY && cells[x + 1][y].getType() == Type.EMPTY && cells[x][y + 1].getType() == Type.EMPTY && cells[x - 1][y].getType() != Type.EMPTY) {
                cells[x][y].setSize(Size.ONE);
                return true;
            }
            if (validMove(x, y) && cells[x + 1][y].getType() == Type.EMPTY && cells[x][y + 1].getType() == Type.EMPTY && cells[x - 1][y].getType() == Type.EMPTY && cells[x][y - 1].getType() != Type.EMPTY) {
                cells[x][y].setSize(Size.ONE);
                return true;
            }
            if (validMove(x, y) && cells[x][y + 1].getType() == Type.EMPTY && cells[x - 1][y].getType() == Type.EMPTY && cells[x][y - 1].getType() == Type.EMPTY && cells[x + 1][y].getType() != Type.EMPTY) {
                cells[x][y].setSize(Size.ONE);
                return true;
            }
            if (validMove(x, y) && cells[x - 1][y].getType() == Type.EMPTY && cells[x][y - 1].getType() == Type.EMPTY && cells[x + 1][y].getType() == Type.EMPTY && cells[x][y + 1].getType() != Type.EMPTY) {
                cells[x][y].setSize(Size.ONE);
                return true;
            }
            if (validMove(x, y) && cells[x - 1][y].getType() != Type.EMPTY && cells[x][y - 1].getType() != Type.EMPTY && cells[x + 1][y].getType() == Type.EMPTY && cells[x][y + 1].getType() == Type.EMPTY) {
                cells[x][y].setSize(chooseTwoCells(x, y, x - 1, y, x, y - 1));
                return true;
            }
            if (validMove(x, y) && cells[x][y - 1].getType() != Type.EMPTY && cells[x + 1][y].getType() != Type.EMPTY && cells[x][y + 1].getType() == Type.EMPTY && cells[x - 1][y].getType() == Type.EMPTY) {
                cells[x][y].setSize(chooseTwoCells(x, y, x, y - 1, x + 1, y));
                return true;
            }
            if (validMove(x, y) && cells[x + 1][y].getType() != Type.EMPTY && cells[x][y + 1].getType() != Type.EMPTY && cells[x - 1][y].getType() == Type.EMPTY && cells[x][y - 1].getType() == Type.EMPTY) {
                cells[x][y].setSize(chooseTwoCells(x, y, x + 1, y, x, y + 1));
                return true;
            }
            if (validMove(x, y) && cells[x][y + 1].getType() != Type.EMPTY && cells[x - 1][y].getType() != Type.EMPTY && cells[x][y - 1].getType() == Type.EMPTY && cells[x + 1][y].getType() == Type.EMPTY) {
                cells[x][y].setSize(chooseTwoCells(x, y, x, y + 1, x - 1, y));
                return true;
            }
            if (validMove(x, y) && cells[x - 1][y].getType() != Type.EMPTY && cells[x][y - 1].getType() != Type.EMPTY && cells[x + 1][y].getType() != Type.EMPTY && cells[x][y + 1].getType() == Type.EMPTY) {
                System.out.println("Choose how many cells do you want to take - 2 or 3?");
                int n = sc.nextInt();
                if (n == 2) {
                    System.out.println("Choose 2 cells which  do you want ? U-upper ,D-down,L-left");
                    char c = sc.next().charAt(0);
                    char c2 = sc.next().charAt(0);
                    if ((c == 'U' && c2 == 'L') || (c == 'L' && c2 == 'U')) {
                        cells[x][y].setSize(chooseTwoCells(x, y, x - 1, y, x, y - 1));
                    }
                    if ((c == 'U' && c2 == 'D') || (c == 'D' && c2 == 'U')) {
                        cells[x][y].setSize(chooseTwoCells(x, y, x + 1, y, x - 1, y));
                    }
                    if ((c == 'L' && c2 == 'D') || (c == 'D' && c2 == 'L')) {
                        cells[x][y].setSize(chooseTwoCells(x, y, x, y - 1, x + 1, y));
                    } else {
                        System.out.println("You have choosen wrong cells!");
                    }
                }
                if (n == 3) {
                    cells[x][y].setSize(chooseThreeCells(x, y, x + 1, y, x, y - 1, x - 1, y));
                } else {
                    System.out.println("Invalid number of cells!");
                }
                return true;
            }
            if (validMove(x, y) && cells[x][y - 1].getType() != Type.EMPTY && cells[x + 1][y].getType() != Type.EMPTY && cells[x][y + 1].getType() != Type.EMPTY && cells[x - 1][y].getType() == Type.EMPTY) {
                System.out.println("Choose how many cells do you want to take - 2 or 3?");
                int n = sc.nextInt();
                if (n == 2) {
                    System.out.println("Choose 2 cells which  do you want ? D-down,L-left,R-right");
                    char c = sc.next().charAt(0);
                    char c2 = sc.next().charAt(0);
                    if ((c == 'L' && c2 == 'R') || (c == 'R' && c2 == 'L')) {
                        cells[x][y].setSize(chooseTwoCells(x, y, x, y - 1, x, y + 1));
                    }
                    if ((c == 'D' && c2 == 'L') || (c == 'L' && c2 == 'D')) {
                        cells[x][y].setSize(chooseTwoCells(x, y, x, y - 1, x + 1, y));
                    }
                    if ((c == 'D' && c2 == 'R') || (c == 'R' && c2 == 'D')) {
                        cells[x][y].setSize(chooseTwoCells(x, y, x + 1, y, x, y + 1));
                    } else {
                        System.out.println("You have choosen wrong cells!");
                    }
                }
                if (n == 3) {
                    cells[x][y].setSize(chooseThreeCells(x, y, x, y - 1, x, y + 1, x + 1, y));
                } else {
                    System.out.println("Invalid number of cells!");
                }
                return true;
            }
            if (validMove(x, y) && cells[x + 1][y].getType() != Type.EMPTY && cells[x][y + 1].getType() != Type.EMPTY && cells[x - 1][y].getType() != Type.EMPTY && cells[x][y - 1].getType() == Type.EMPTY) {
                System.out.println("Choose how many cells do you want to take - 2 or 3?");
                int n = sc.nextInt();
                if (n == 2) {
                    System.out.println("Choose 2 cells which  do you want ? U-up,D-down,R-right");
                    char c = sc.next().charAt(0);
                    char c2 = sc.next().charAt(0);
                    if ((c == 'U' && c2 == 'D') || (c == 'D' && c2 == 'U')) {
                        cells[x][y].setSize(chooseTwoCells(x, y, x - 1, y, x + 1, y));
                    }
                    if ((c == 'U' && c2 == 'R') || (c == 'R' && c2 == 'U')) {
                        cells[x][y].setSize(chooseTwoCells(x, y, x - 1, y, x, y + 1));
                    }
                    if ((c == 'D' && c2 == 'R') || (c == 'R' && c2 == 'D')) {
                        cells[x][y].setSize(chooseTwoCells(x, y, x + 1, y, x, y + 1));
                    } else {
                        System.out.println("You have choosen wrong cells!");
                    }
                }
                if (n == 3) {
                    cells[x][y].setSize(chooseThreeCells(x, y, x + 1, y, x, y + 1, x - 1, y));
                } else {
                    System.out.println("Invalid number of cells!");
                }
                return true;
            }
            if (validMove(x, y) && cells[x][y + 1].getType() != Type.EMPTY && cells[x - 1][y].getType() != Type.EMPTY && cells[x][y - 1].getType() != Type.EMPTY && cells[x + 1][y].getType() == Type.EMPTY) {
                System.out.println("Choose how many cells do you want to take - 2 or 3?");
                int n = sc.nextInt();
                if (n == 2) {
                    System.out.println("Choose 2 cells which  do you want ? U-up,L-left,R-right");
                    char c = sc.next().charAt(0);
                    char c2 = sc.next().charAt(0);
                    if ((c == 'L' && c2 == 'R') || (c == 'R' && c2 == 'L')) {
                        cells[x][y].setSize(chooseTwoCells(x, y, x, y - 1, x, y + 1));
                    }
                    if ((c == 'U' && c2 == 'R') || (c == 'R' && c2 == 'U')) {
                        cells[x][y].setSize(chooseTwoCells(x, y, x - 1, y, x, y + 1));
                    }
                    if ((c == 'U' && c2 == 'L') || (c == 'L' && c2 == 'U')) {
                        cells[x][y].setSize(chooseTwoCells(x, y, x - 1, y, x, y - 1));
                    } else {
                        System.out.println("You have choosen wrong cells!");
                    }
                }
                if (n == 3) {
                    cells[x][y].setSize(chooseThreeCells(x, y, x, y + 1, x - 1, y, x, y - 1));
                } else {
                    System.out.println("Invalid number of cells!");
                }
                return true;
            }
            if (validMove(x, y) && cells[x][y + 1].getType() != Type.EMPTY && cells[x - 1][y].getType() != Type.EMPTY && cells[x][y - 1].getType() != Type.EMPTY && cells[x + 1][y].getType() != Type.EMPTY) {
                System.out.println("Choose how many cells do yo want to take - 2 ,3 or 4");
                int n = sc.nextInt();
                if (n == 2) {
                    System.out.println("Choose 2 cells which  do you want ? U-up,L-left,R-right,D-down");
                    char c = sc.next().charAt(0);
                    char c2 = sc.next().charAt(0);
                    if ((c == 'L' && c2 == 'R') || (c == 'R' && c2 == 'L')) {
                        cells[x][y].setSize(chooseTwoCells(x, y, x, y - 1, x, y + 1));
                    }
                    if ((c == 'U' && c2 == 'R') || (c == 'R' && c2 == 'U')) {
                        cells[x][y].setSize(chooseTwoCells(x, y, x - 1, y, x, y + 1));
                    }
                    if ((c == 'U' && c2 == 'L') || (c == 'L' && c2 == 'U')) {
                        cells[x][y].setSize(chooseTwoCells(x, y, x - 1, y, x, y - 1));
                    }
                    if ((c == 'U' && c2 == 'D') || (c == 'D' && c2 == 'U')) {
                        cells[x][y].setSize(chooseTwoCells(x, y, x - 1, y, x + 1, y));
                    }
                    if ((c == 'L' && c2 == 'D') || (c == 'D' && c2 == 'L')) {
                        cells[x][y].setSize(chooseTwoCells(x, y, x + 1, y, x, y - 1));
                    }
                    if ((c == 'D' && c2 == 'R') || (c == 'R' && c2 == 'D')) {
                        cells[x][y].setSize(chooseTwoCells(x, y, x + 1, y, x, y + 1));
                    } else {
                        System.out.println("You have choosen wrong cells!");
                    }
                    return true;

                }
                if (n == 3) {
                    System.out.println("Choose 3 cells which  do you want ? U-up,L-left,R-right,D-down");
                    char c = sc.next().charAt(0);
                    char c2 = sc.next().charAt(0);
                    char c3 = sc.next().charAt(0);
                    if ((c == 'L' && c2 == 'D' && c3 == 'R') || (c == 'L' && c2 == 'R' && c3 == 'D') || (c == 'D' && c2 == 'L' && c3 == 'R')
                            || (c == 'D' && c2 == 'R' && c3 == 'L') || (c == 'R' && c2 == 'D' && c3 == 'L') || (c == 'R' && c2 == 'L' && c3 == 'D')) {
                        cells[x][y].setSize(chooseThreeCells(x, y, x, y - 1, x + 1, y, x, y + 1));
                    }
                    if ((c == 'D' && c2 == 'R' && c3 == 'U') || (c == 'D' && c2 == 'U' && c3 == 'R') || (c == 'R' && c2 == 'D' && c3 == 'U')
                            || (c == 'R' && c2 == 'U' && c3 == 'D') || (c == 'U' && c2 == 'D' && c3 == 'D') || (c == 'U' && c2 == 'R' && c3 == 'D')) {
                        cells[x][y].setSize(chooseThreeCells(x, y, x + 1, y, x, y + 1, x - 1, y));
                    }
                    if ((c == 'R' && c2 == 'U' && c3 == 'L') || (c == 'R' && c2 == 'L' && c3 == 'U') || (c == 'U' && c2 == 'R' && c3 == 'L')
                            || (c == 'U' && c2 == 'L' && c3 == 'R') || (c == 'L' && c2 == 'U' && c3 == 'R') || (c == 'L' && c2 == 'R' && c3 == 'U')) {
                        cells[x][y].setSize(chooseThreeCells(x, y, x, y + 1, x - 1, y, x, y - 1));
                    }
                    if ((c == 'L' && c2 == 'U' && c3 == 'D') || (c == 'L' && c2 == 'D' && c3 == 'U') || (c == 'U' && c2 == 'L' && c3 == 'D')
                            || (c == 'U' && c2 == 'D' && c3 == 'L') || (c == 'D' && c2 == 'U' && c3 == 'L') || (c == 'D' && c2 == 'L' && c3 == 'U')) {
                        cells[x][y].setSize(chooseThreeCells(x, y, x, y - 1, x - 1, y, x + 1, y));
                    } else {
                        System.out.println("You have choosen wrong cells!");
                    }

                }
                if (n == 4) {
                    cells[x][y].setSize(chooseFourCells(x, y, x, y - 1, x, y + 1, x + 1, y, x - 1, y));
                } else {
                    System.out.println("Invalid number of cells!");
                }
                return true;
            }

        }


        return false;
    }

    /**
     * Check for winner on the board.
     *
     * @return True if there is a winner, false otherwise.
     */
    public boolean hasWinner() {
        if (this.gameOver == true) {
            return true;
        }
        return false;
    }

    /**
     * Provide winner type.
     *
     * @return Winner type.
     */
    public Cell.Type winner() {
        int counterForEmpty = 0;
        int counterForRed = 0;
        int counterForBlue = 0;
        if (this.gameOver == true) {
            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[0].length; j++) {
                    if (cells[i][j].getType() == Type.EMPTY) {
                        counterForEmpty++;
                    }
                    if (cells[i][j].getType() == Type.RED) {
                        counterForRed++;
                    }
                    if (cells[i][j].getType() == Type.BLUE) {
                        counterForBlue++;
                    }
                }
            }
        } else {
            System.out.println("You havent finish the game yet.");
        }
        Type t = Type.EMPTY;
        if (counterForBlue > counterForRed) {
            t = Type.BLUE;
        }
        if (counterForBlue < counterForRed) {
            t = Type.RED;
        } else {
            t = null;
        }

        return t;
    }

    /**
     * Evaluate scores of different players.
     *
     * @return Counters with the success of the different players on the board.
     */
    public Map<Type, Integer> score() {
        Map<Type, Integer> result = new HashMap<Type, Integer>();
        int counterForEmpty = 0;
        int counterForRed = 0;
        int counterForBlue = 0;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                if (cells[i][j].getType() == Type.EMPTY) {
                    counterForEmpty++;

                }
                if (cells[i][j].getType() == Type.RED) {
                    counterForRed++;
                    result.put(Type.RED, counterForRed);
                }
                if (cells[i][j].getType() == Type.BLUE) {
                    counterForBlue++;
                    result.put(Type.BLUE, counterForBlue);
                }
            }
        }

        return result;
    }

    // TODO Create constructors. - no need of them

    // TODO Create getters and setters.
    public int getTurn() {
        return turn;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    // TODO Create equals and hash code methods. - no need of them
}