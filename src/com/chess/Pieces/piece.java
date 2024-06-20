package com.chess.Pieces;

import com.chess.Alliance;
import com.chess.board.Move;
import com.chess.board.board;

import java.util.Collection;

public abstract class piece {

    protected final int piecePosition;
    protected final Alliance pieceAlliance;

    protected final boolean isFirstMove;

    piece(final int piecePosition, final Alliance pieceAlliance){
        this.pieceAlliance = pieceAlliance;
        this.piecePosition = piecePosition;
        this.isFirstMove = false;
    }

    piece Alliance;

    public int getPiecePosition(){
        return this.piecePosition;
    }
    public com.chess.Alliance getPieceAlliance() {
        return this.pieceAlliance;
    }

    public boolean isFirstMove(){
        return this.isFirstMove;
    }

    public abstract Collection<Move> calculateLegalMoves(final board board);
}
