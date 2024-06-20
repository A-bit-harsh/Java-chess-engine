package com.chess.Pieces;

import com.chess.Alliance;
import com.chess.board.Move;
import com.chess.board.board;
import com.chess.board.boardUtils;
import com.chess.board.tile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class King extends piece{

    private final static int[] CANDIDATE_MOVE_COORDINATES = {-9, -8, -7, -1, 1, 7, 8, 9};

    King(int piecePosition, com.chess.Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(board board) {
        final List<Move> legalMoves = new ArrayList<>();

        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES) {

            final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;

            if (boardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {

                if (isFirstColumnExclusion(this.piecePosition, currentCandidateOffset) || isEighthColumnExclusion(this.piecePosition, currentCandidateOffset)) {
                    continue;
                }

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
                    }
                }
            }

        }
        return null;
    }
    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
        return boardUtils.FIRST_COLUMN[currentPosition] && ((candidateOffset == -9) || (candidateOffset == -1) || (candidateOffset == 7));
    }

    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset){
        return boardUtils.EIGHTH_COLUMN[currentPosition] && ((candidateOffset == 9) || (candidateOffset == 1) || (candidateOffset == -7));
    }
}
