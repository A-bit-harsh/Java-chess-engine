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

public abstract class Knight extends piece{
    //finding all the candidate moves that could be possible
    private final static int[] CANDIDATE_MOVE_COORDINATES = {-17, -15, -10, -6, 6, 10, 15, 17};

    public Knight(final int piecePosition,final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final board board){

        final List<Move> legalMoves = new ArrayList<>();

        for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES){

            final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;

            if(boardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){

                if(isFirstColumnExclusion(this.piecePosition,currentCandidateOffset) || isSecondColumnExclusion(this.piecePosition,currentCandidateOffset) || isSeventhColumnExclusion(this.piecePosition,currentCandidateOffset) || isEighthColumnExclusion(this.piecePosition,currentCandidateOffset)){
                    continue;
                }

                final tile candidateDestinationtile = board.getTile(candidateDestinationCoordinate);

                if(!candidateDestinationtile.isTileOccupied()){
                    legalMoves.add(new Move.MajorMove(board, this , candidateDestinationCoordinate));
                }else {
                    final piece pieceAtDestination = candidateDestinationtile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();

                    if(this.pieceAlliance != pieceAlliance){
                        legalMoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
                    }
                }
            }

        }

        return ImmutableList.copyOf(legalMoves);
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
        return boardUtils.FIRST_COLUMN[currentPosition] && ((candidateOffset == -17) || (candidateOffset == -10) || (candidateOffset == 6) || (candidateOffset == 15));
    }

    private static boolean isSecondColumnExclusion(final int currentPosition, final int candidateOffset){
        return boardUtils.SECOND_COLUMN[currentPosition] && ((candidateOffset == -10) || (candidateOffset == 6));
    }

    private static boolean isSeventhColumnExclusion(final int currentPosition, final int candidateOffset){
        return boardUtils.SEVENTH_COLUMN[currentPosition] && ((candidateOffset == 10) || (candidateOffset == -6));
    }

    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset){
        return boardUtils.EIGHTH_COLUMN[currentPosition] && ((candidateOffset == 17) || (candidateOffset == 10) || (candidateOffset == -6) || (candidateOffset == -15));
    }

}
