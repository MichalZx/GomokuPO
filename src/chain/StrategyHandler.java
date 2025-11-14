package chain;

import chain.strategy.Strategy;
import factory.Board;
import fais.zti.oramus.gomoku.Mark;
import fais.zti.oramus.gomoku.Move;
import fais.zti.oramus.gomoku.ResignException;
import fais.zti.oramus.gomoku.TheWinnerIsException;

public abstract class StrategyHandler {
    protected StrategyHandler next;

    public StrategyHandler setNext(StrategyHandler nextHandler) {
        this.next = nextHandler;
        return nextHandler;
    }

    public abstract Move handle(Board board, Mark mark) throws TheWinnerIsException, ResignException;

    protected abstract Strategy getStrategy();
}

