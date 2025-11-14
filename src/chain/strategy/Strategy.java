package chain.strategy;

import factory.Board;
import fais.zti.oramus.gomoku.Mark;
import fais.zti.oramus.gomoku.Move;
import fais.zti.oramus.gomoku.ResignException;
import fais.zti.oramus.gomoku.TheWinnerIsException;

public interface Strategy {

    Move analyze(Board board, Mark mark) throws TheWinnerIsException, ResignException;
}

