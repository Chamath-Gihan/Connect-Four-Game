package lk.ijse.dep.service;

public class BoardImpl implements Board {
    Piece[][] pieces;
    BoardUI boardUI;

    public BoardImpl(BoardUI boardUI){
        this.boardUI = boardUI;                         // initialize boardUI
        pieces = new Piece[NUM_OF_COLS][NUM_OF_ROWS];   // initialize pieces

        for (int i = 0; i < NUM_OF_COLS; i++) {         //
            for (int j = 0; j < NUM_OF_ROWS; j++) {     //
                this.pieces[i][j] = Piece.EMPTY;        // assign value EMPTY into the pieces array
            }                                           //
        }                                               //
    }

    @Override
    public BoardUI getBoardUI() {
        return this.boardUI;                            // return boardUI reference variable
    }

    @Override
    public int findNextAvailableSpot(int col) {
        for (int i = 0; i < NUM_OF_ROWS; i++) {     //
            if (pieces[col][i] == Piece.EMPTY) {    // assign value EMPTY to available row spot and
                return i;                           // return it
            }                                       //
        }                                           //
        return -1;                                      // if not have any empty spot in row return -1
    }

    @Override
    public boolean isLegalMove(int col) {
        boolean answer = false;                         // create and initialize answer

        if (findNextAvailableSpot(col) != -1){          // check col is available or not and if not
            answer = true;                              // equal to -1 assign true to answer
        }                                               //

        return answer;                                  // else return answer with false
    }

    @Override
    public boolean existLegalMove() {
        boolean answer = false;                         // create and initialize answer

        for (int i = 0; i < NUM_OF_COLS; i++) {
            for (int j = 0; j < NUM_OF_ROWS; j++) {
                if (pieces[i][j] == Piece.EMPTY) {
                    answer = true;
                }
            }
        }
        return answer;
    }

    @Override
    public void updateMove(int col, Piece move) {
        this.pieces[col][findNextAvailableSpot(col)] = move;
    }


    int col1;
    int row1;
    int col2;
    int row2;
    
    @Override
    public Winner findWinner() {
        Piece winningPiece;

        for (int i = 0; i < NUM_OF_ROWS; i++) {// col 6 column checker

            if (pieces[2][i] == pieces[3][i] && pieces[3][i] == pieces[4][i] && pieces[4][i] == pieces[5][i]) {
                winningPiece = pieces[2][i];
                if (winningPiece != Piece.EMPTY) {
                    col1 = 2;
                    col2 = 5;
                    row1 = i;
                    row2 = i;
                    return new Winner(winningPiece, col1, row1, col2, row2);
                }
            } else if (pieces[0][i] == pieces[1][i] && pieces[1][i] == pieces[2][i] && pieces[2][i] == pieces[3][i]) {
                winningPiece = pieces[0][i];
                if (winningPiece != Piece.EMPTY) {
                    col1 = i;
                    col2 = i;
                    row1 = 0;
                    row2 = 3;
                    return new Winner(winningPiece, col1, row1, col2, row2);
                }
            } else if (pieces[1][i] == pieces[2][i] && pieces[2][i] == pieces[3][i] && pieces[3][i] == pieces[4][i]) {
                winningPiece = pieces[1][i];
                if (winningPiece != Piece.EMPTY) {
                    col1 = 1;
                    col2 = 4;
                    row1 = i;
                    row2 = i;
                    return new Winner(winningPiece, col1, row1, col2, row2);
                }
            }
        }

        for (int i = 0; i < NUM_OF_COLS; i++) {// rows 5 row checker

            if (pieces[i][0] == pieces[i][1] && pieces[i][1] == pieces[i][2] && pieces[i][2] == pieces[i][3]) {
                winningPiece = pieces[i][0];
                if (winningPiece != Piece.EMPTY) {
                    col1 = i;
                    col2 = i;
                    row1 = 0;
                    row2 = 3;
                    return new Winner(winningPiece, col1, row1, col2, row2);
                }
            } else if (pieces[i][1] == pieces[i][2] && pieces[i][2] == pieces[i][3] && pieces[i][3] == pieces[i][4]) {
                winningPiece = pieces[i][1];
                if (winningPiece != Piece.EMPTY) {
                    col1 = i;
                    col2 = i;
                    row1 = 1;
                    row2 = 4;
                    return new Winner(winningPiece, col1, row1, col2, row2);
                }
            }
        }
        return new Winner(Piece.EMPTY);
    }
    @Override
    public void updateMove(int col, int rows, Piece move) {
        pieces[col][rows] = move;
    }
}
