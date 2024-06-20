package com.chess.Pieces;

import com.chess.Alliance;
import com.chess.board.Move;
import com.chess.board.board;
import com.chess.board.boardUtils;
import com.chess.board.tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Rook extends piece{

    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {-8,-1,1,8};
    Rook(int piecePosition, com.chess.Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for (final int candidateCoordinateOffset : CANDIDATE_MOVE_VECTOR_COORDINATES) {

            int candidateDestinationCoordinate = this.piecePosition;

            while (boardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                candidateDestinationCoordinate += candidateCoordinateOffset;

                if (boardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                    final tile candidateDestinationtile = board.getTile(candidateDestinationCoordinate);

                    if (!candidateDestinationtile.isTileOccupied()) {
                        legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
                    } else {
                        final piece pieceAtDestination = candidateDestinationtile.getPiece();
                        final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();

                        if (this.pieceAlliance != pieceAlliance) {
                            legalMoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
                        }
                        break;
                    }
                }
            }
        }

        return ImmutableList.copyOf(legalMoves);
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
        return boardUtils.FIRST_COLUMN[currentPosition] && ((candidateOffset == -1));
    }

    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset){
        return boardUtils.EIGHTH_COLUMN[currentPosition] && ((candidateOffset == 1));
    }
}
