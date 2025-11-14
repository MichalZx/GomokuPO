package factory;

import fais.zti.oramus.gomoku.Mark;
import fais.zti.oramus.gomoku.Move;
import fais.zti.oramus.gomoku.WrongBoardStateException;

import java.util.Set;

public class NormalBoard extends Board {
    public NormalBoard(int size, boolean isPeriodic, Set<Move> moves) throws WrongBoardStateException {
        super(size, isPeriodic);
        for (Move move : moves) {
            if (getMarkAt(move.position()) != Mark.NULL){
                throw new WrongBoardStateException();
            }
            boardMap.put(move.position(), move.mark());
        }
    }
}

