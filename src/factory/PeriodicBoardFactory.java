package factory;

import fais.zti.oramus.gomoku.Move;
import fais.zti.oramus.gomoku.WrongBoardStateException;

import java.util.Set;

public class PeriodicBoardFactory implements BoardFactory {
    @Override
    public Board createBoard(int size, boolean isPeriodic, Set<Move> moves) throws WrongBoardStateException {
        return new PeriodicBoard(size, moves);
    }
}

