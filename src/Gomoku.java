import chain.ConcreteStrategyHandler;
import chain.StrategyHandler;
import chain.strategy.*;
import factory.Board;
import factory.BoardFactory;
import factory.NormalBoardFactory;
import factory.PeriodicBoardFactory;
import fais.zti.oramus.gomoku.*;
import singleton.GomokuConfig;

import java.util.HashSet;
import java.util.Set;

public class Gomoku implements Game {
    public Gomoku() {
        GomokuConfig.getInstance().reset();
    }

    @Override
    public void firstMark(Mark first) {
        GomokuConfig.getInstance().setFirstMark(first);
    }

    @Override
    public void size(int size) {
        GomokuConfig.getInstance().setBoardSize(size);
    }

    @Override
    public void periodicBoundaryConditionsInUse() {
        GomokuConfig.getInstance().setPeriodic(true);
    }

    @Override
    public Move nextMove(Set<Move> boardState, Mark nextMoveMark)
            throws ResignException, TheWinnerIsException, WrongBoardStateException {

        GomokuConfig config = GomokuConfig.getInstance();
        int boardSize = config.getBoardSize();
        boolean isPeriodic = config.isPeriodic();
        CheckBoardState(boardState , config.getFirstMark(), nextMoveMark, boardSize);

        config.setReturnMove(null);
        BoardFactory factory = isPeriodic ? new PeriodicBoardFactory() : new NormalBoardFactory();
        Board board = factory.createBoard(boardSize, isPeriodic, boardState);

        StrategyHandler finished = new ConcreteStrategyHandler(new FinishedStrategy());
        StrategyHandler win = new ConcreteStrategyHandler(new WinMoveStrategy());
        StrategyHandler blockWin = new ConcreteStrategyHandler(new BlockMoveStrategy());
        StrategyHandler trap4 = new ConcreteStrategyHandler(new Trap4Strategy());
        StrategyHandler trap3 = new ConcreteStrategyHandler(new Trap3Strategy());

        finished.setNext(win).setNext(blockWin).setNext(trap4).setNext(trap3);

        Move move = finished.handle(board, nextMoveMark);

        if (move != null)
                return move;
        else {
            return config.getReturnMove();
        }
    }

    private void CheckBoardState(Set<Move> boardState, Mark firstMark, Mark nextMoveMark, int size) throws WrongBoardStateException {
        int xCount ,oCount;
        Mark mark;
        Set<Position> positions = new HashSet<>();

        if (nextMoveMark == Mark.CROSS || nextMoveMark == Mark.NOUGHT){
            xCount = 0;
            oCount = 0;
        }else{
            throw new WrongBoardStateException(); //NULL
        }

        if(firstMark != Mark.CROSS && firstMark != Mark.NOUGHT){
            throw new WrongBoardStateException(); //NULL
        }

        if (size < 10 || size > 15){
            throw new WrongBoardStateException(); //NULL
        }

        for (Move move : boardState) {
            if (move.position().col() >= size || move.position().row() >= size || move.position().col() < 0 || move.position().row() < 0) {
                throw new WrongBoardStateException();
            }
            if (!positions.add(move.position())) {//jeżeli w boardState powtórzy się ruch na te samą pozycję  => Wyjątek!!!
                throw new WrongBoardStateException();
            }
            mark = move.mark();
            if (mark == Mark.CROSS) {
                xCount++;
            } else if (mark == Mark.NOUGHT) {
                oCount++;
            }else{
                throw new WrongBoardStateException();
            }
        }

        if ((xCount == oCount) && (nextMoveMark != firstMark)){
            throw new WrongBoardStateException();
        }else if (xCount != oCount){
            if(firstMark == Mark.CROSS){
                if(xCount - oCount != 1){
                    throw new WrongBoardStateException();
                }else if (nextMoveMark == Mark.CROSS){
                    throw new WrongBoardStateException();
                }
            }else{
                if(oCount - xCount != 1){
                    throw new WrongBoardStateException();
                }else if (nextMoveMark == Mark.NOUGHT){
                    throw new WrongBoardStateException();
                }
            }
        }

//        if (boardState.size() == size*size){
//            throw new WrongBoardStateException();
//        }
    }
}