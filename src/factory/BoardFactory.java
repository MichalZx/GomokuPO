package factory;

import fais.zti.oramus.gomoku.Move;
import fais.zti.oramus.gomoku.WrongBoardStateException;

import java.util.Set;

public interface BoardFactory {
    Board createBoard(int size, boolean isPeriodic, Set<Move> moves) throws WrongBoardStateException;
}

