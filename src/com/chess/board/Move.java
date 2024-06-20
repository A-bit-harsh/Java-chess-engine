package com.chess.board;

import com.chess.Pieces.piece;

public abstract class Move {
    final board board;
    final piece movedPiece;
    final int destinationCoordinate;

    private Move (final board board,
          final piece movedPiece,
          final int destinationCoordinate){
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destinationCoordinate;
    }

    public static final class MajorMove extends Move {

        public MajorMove(com.chess.board.board board, piece movedPiece, int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }
    }

    public static final class AttackMove extends Move {

        final piece attackedPiece;
        public AttackMove(final com.chess.board.board board,final piece movedPiece,final int destinationCoordinate,final piece attackedPiece) {
            super(board, movedPiece, destinationCoordinate);
            this.attackedPiece = attackedPiece;
        }
    }

}
