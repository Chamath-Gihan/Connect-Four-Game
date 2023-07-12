package lk.ijse.dep.service;

public class HumanPlayer extends Player{
    boolean isTrue;
    Winner winner;
    public HumanPlayer(Board newBoard) {
        this.board = newBoard;
    }

    @Override
    public void movePiece(int col1){
//        if (this.board.isLegalMove(col)){
//            this.board.updateMove(col, Piece.BLUE);
//            this.board.getBoardUI().update(col, true);
//
//            Winner winner = this.board.findWinner();
//            if (winner != null){
//                board.getBoardUI().notifyWinner(winner);
//            }
//        }
        isTrue = board.isLegalMove(col1);
        if(isTrue) {
            board.updateMove(col1, Piece.BLUE);
            board.getBoardUI().update(col1, isTrue);

            winner = board.findWinner();

            if (winner.getWinningPiece() != Piece.EMPTY) {
                board.getBoardUI().notifyWinner(winner);
            } else {
                if (board.existLegalMove() != true) {
                    board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
                }
            }
        }
    }
}
