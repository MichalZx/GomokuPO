package factory;

import fais.zti.oramus.gomoku.Mark;
import fais.zti.oramus.gomoku.Move;
import fais.zti.oramus.gomoku.Position;
import fais.zti.oramus.gomoku.WrongBoardStateException;

import java.util.Set;

public class PeriodicBoard extends Board {
    public PeriodicBoard(int size, Set<Move> moves) throws WrongBoardStateException {
        super(size * 2, true);

        for (Move move : moves) {
            if (getMarkAt(move.position()) != Mark.NULL){
                throw new WrongBoardStateException();
            }
            Position pos = move.position();
            Mark mark = move.mark();

            int row = pos.row();
            int col = pos.col();

            if (getMarkAt(new Position(col + size, row)) != Mark.NULL){
                throw new WrongBoardStateException();
            }
            if (getMarkAt(new Position(col, row + size)) != Mark.NULL){
                throw new WrongBoardStateException();
            }
            if (getMarkAt(new Position(col + size, row + size)) != Mark.NULL){
                throw new WrongBoardStateException();
            }

            boardMap.put(new Position(col, row), mark);                        //OG
            boardMap.put(new Position(col + size, row), mark);             // prawa gorna
            boardMap.put(new Position(col, row + size), mark);             // dolna
            boardMap.put(new Position(col + size, row + size), mark);  // prawa dolna
        }
    }
}


