package com.chess.board;

import com.chess.Alliance;
import com.chess.Pieces.piece;
import java.util.Map;

public class board {



    public board(Builder builder) {
    }

    public tile getTile(final int tileCoordinate){
        return null;

    }


    public static class Builder{
        Map<Integer, piece> boardConfig;
        Alliance nextMoveMaker;

        public Builder(){
        }

        public Builder setPiece(final piece piece){
            this.boardConfig.put(piece.getPiecePosition(), piece);
            return this;
        }

        public Builder setMoveMaker(final Alliance nextMoveMaker){
            this.nextMoveMaker = nextMoveMaker;
            return this;
        }

        public board build(){
            return new board(this);
        }
    }
}
