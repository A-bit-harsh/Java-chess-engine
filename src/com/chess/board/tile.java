package com.chess.board;
import com.chess.Pieces.piece;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.HashMap;
public abstract class tile {
    protected final int tileCoordinate;


    //creating all empty tiles already so that when the com.chess.board.tile is needed to be empty we can just put it there

    private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();

    private static Map<Integer,EmptyTile> createAllPossibleEmptyTiles() {
        final Map<Integer , EmptyTile> emptyTileMap = new HashMap<>();

        for(int i = 0; i < boardUtils.NUM_TILES; i++){
            emptyTileMap.put(i, new EmptyTile(i));
        }


        return ImmutableMap.copyOf(emptyTileMap);
    }
    public static tile createTile(final int tileCoordinate, final piece piece){
        return piece != null ? new occupiedTile(tileCoordinate, piece) : EMPTY_TILES_CACHE.get(tileCoordinate);
    }



    private tile(final int tileCoordinate){
        this.tileCoordinate = tileCoordinate;
    }

    public abstract boolean isTileOccupied();
    public abstract piece getPiece();

    //*method to identify if a com.chess.board.tile is empty*//
    public static final class EmptyTile extends tile{
        private EmptyTile(final int coordinate){
            super(coordinate);
        }

        @Override
        public boolean isTileOccupied(){
            return false;
        }

        @Override
        public piece getPiece(){
            return null;
        }

    }

    //*method to identify if a com.chess.board.tile is occupied*//
    public static final class occupiedTile extends tile{
        private final piece pieceOnTile;
        private occupiedTile(int coordinate,final piece pieceOnTile){
            super(coordinate);
            this.pieceOnTile = pieceOnTile;
        }

        @Override
        public boolean isTileOccupied(){
            return true;
        }

        @Override
        public piece getPiece(){
            return this.pieceOnTile;
        }
    }
}
