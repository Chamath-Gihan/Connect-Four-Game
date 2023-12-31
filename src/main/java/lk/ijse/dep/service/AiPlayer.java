package lk.ijse.dep.service;

import java.util.Random;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class AiPlayer extends Player {
    public AiPlayer(Board newBoard) {
        this.board = newBoard;
    }

    @Override
    public void movePiece(int col){
        col = bestMove();
        board.updateMove(col, Piece.GREEN);
        board.getBoardUI().update(col, false);
        Winner winner = board.findWinner();
        if (winner.getWinningPiece() == Piece.GREEN) {
            board.getBoardUI().notifyWinner(winner);
        } else if (!board.existLegalMove()) {
            board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
        }
    }

    private int minimax(int depth, boolean maximizingPlayer) {
        Winner winner = board.findWinner();
        if (winner.getWinningPiece() == Piece.GREEN) {
            return 1;
        } else if (winner.getWinningPiece() == Piece.BLUE) {//
            return -1;
        } else if (board.existLegalMove() && depth <= 5) {
            int heuristicVal;
            if (maximizingPlayer) {
                int maxEvA = -1000;
                for (int i = 0; i < 6; ++i) {
                    if (board.isLegalMove(i)) {
                        int row = board.findNextAvailableSpot(i);
                        board.updateMove(i, Piece.GREEN);
                        heuristicVal = minimax(depth + 1, false);
                        maxEvA = max(maxEvA,heuristicVal);
                        board.updateMove(i, row, Piece.EMPTY);
                        if (heuristicVal == 1) {
                            return maxEvA;
                        }
                    }
                }

            } else {

                int minEva= 1000;
                for (int i = 0; i < 6; ++i) {
                    if (board.isLegalMove(i)) {
                        int row = board.findNextAvailableSpot(i);
                        board.updateMove(i, Piece.BLUE);
                        heuristicVal = minimax(depth + 1, true);
                        minEva= min (minEva,heuristicVal);
                        board.updateMove(i, row, Piece.EMPTY);
                        if (heuristicVal == -1) {
                            return minEva;
                        }
                    }
                }
            }
            return 0;
        } else {
            return 0;
        }
    }

    private int bestMove() {
        boolean isUserWinning = false;
        int winningCol = 0;

        for (int i = 0; i < 6; ++i) {
            if (board.isLegalMove(i)) {
                int row = board.findNextAvailableSpot(i);
                board.updateMove(i, Piece.GREEN);
                int heuristicVal = minimax(0, false);
                board.updateMove(i, row, Piece.EMPTY);

                if (heuristicVal == 1) {
                    return i;
                }else if (heuristicVal == -1) {
                    isUserWinning = true;

                } else {
                    winningCol = i;

                }
            }
        }

        if (isUserWinning && board.isLegalMove(winningCol)) {
            System.out.println("winning"+winningCol);
            return winningCol;
        } else {
            int j;
            do {
                j = (int) ((Math.random() * ((5 - 0) + 1)) + 0);
            } while (!board.isLegalMove(j));
            return j;
        }
    }
}
