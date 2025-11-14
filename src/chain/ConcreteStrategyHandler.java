package chain;

import chain.strategy.Strategy;
import factory.Board;
import fais.zti.oramus.gomoku.Mark;
import fais.zti.oramus.gomoku.Move;
import fais.zti.oramus.gomoku.ResignException;
import fais.zti.oramus.gomoku.TheWinnerIsException;

public class ConcreteStrategyHandler extends StrategyHandler {
    private final Strategy strategy;

    public ConcreteStrategyHandler(Strategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public Move handle(Board board, Mark nextMoveMark) throws TheWinnerIsException, ResignException{
        Move move = strategy.analyze(board, nextMoveMark);
        if (move != null) {
            return move;
        } else if (next != null) {
            return next.handle(board, nextMoveMark);
        } else {
            return null;
        }
    }

    @Override
    protected Strategy getStrategy() {
        return strategy;
    }
}

