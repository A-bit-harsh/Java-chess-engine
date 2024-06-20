package com.chess.Pieces;

import com.chess.Alliance;
import com.chess.board.Move;
import com.chess.board.Move.MajorMove;
import com.chess.board.board;
import com.chess.board.boardUtils;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn extends piece{

    private final static int[] CANDIDATE_MOVE_COORDINATES = {8, 16, 7, 9};
    Pawn(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES){
            final int candidateDestinationCoordinate = this.piecePosition + (this.getPieceAlliance().getDirection() * currentCandidateOffset);

            if (!boardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
                continue;
            }
            if (currentCandidateOffset == 8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
            } else if (currentCandidateOffset == 16 && this.isFirstMove() &&
                      (boardUtils.SECOND_ROW[this.piecePosition] && this.getPieceAlliance().isBlack()) ||
                      (boardUtils.SEVENTH_ROW[this.piecePosition] && this.getPieceAlliance().isWhite())) {
                final int behindCandidateDestinationCoordinate = this.piecePosition + (this.pieceAlliance.getDirection() * 8);
                if (!board.getTile(behindCandidateDestinationCoordinate).isTileOccupied() &&
                    !board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                    legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));

                } else if (currentCandidateOffset == 7 &&
                           !((boardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite() ||
                            (boardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack())))) {
                    if (board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                        final piece pieceOnCandidateDestinationCoordinate = board.getTile(candidateDestinationCoordinate).getPiece();
                        if (this.pieceAlliance != pieceOnCandidateDestinationCoordinate.getPieceAlliance()){
                            legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));

                        }
                    }
                    
                } else if (currentCandidateOffset == 9 &&
                           ((boardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack()) ||
                                   (boardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite()))) {
                    if (board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                        final piece pieceOnCandidateDestinationCoordinate = board.getTile(candidateDestinationCoordinate).getPiece();
                        if (this.pieceAlliance != pieceOnCandidateDestinationCoordinate.getPieceAlliance()){
                            legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));

                        }
                    }
                    
                }

            }
        }



        return ImmutableList.copyOf(legalMoves);
    }
}
