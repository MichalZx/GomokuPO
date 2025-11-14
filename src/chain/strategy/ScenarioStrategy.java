package chain.strategy;

import factory.Board;
import fais.zti.oramus.gomoku.*;
import singleton.GomokuConfig;

public abstract class ScenarioStrategy implements Strategy {
    public Board board;
    public Boolean newRow, CzyMozliwoscObrony, CzyZagrozenie, czyKoniec=false;
    public Move returnMove = null;
    public Integer licznikBlokad=0;

    @Override
    public final Move analyze(Board board, Mark nextMoveMark) throws TheWinnerIsException, ResignException {
        CzyMozliwoscObrony = false;
        int i,j, x, y;
        Position pos;
        GomokuConfig config = GomokuConfig.getInstance();
        CzyZagrozenie = config.getReturnMove() != null;
        this.board = board;

        for (int trybSkanera = 0; trybSkanera <= 1; trybSkanera++) {
            newRow = true;
            for (i = 0; i < board.getSize(); i++) {
                for (j = 0; j < board.getSize(); j++) {
                    if (trybSkanera == 0) {
                        // poziomo ==
                        pos = new Position(j, i);
                    } else {
                        // pionowo ||
                        pos = new Position(i, j);
                    }
                    if (findScheme(pos, nextMoveMark)) {
                        return returnMove;
                    }
                    newRow = false;
                }
                newRow = true;
            }
        }
//ukos \\
        for (x = board.getSize()-1; x >= 0; x--) {
            i = x;
            j = 0;
            while (i < board.getSize() && j < board.getSize()) {
                pos = new Position(i, j);
                if (findScheme(pos, nextMoveMark)) {
                    return returnMove;
                }
                i++;
                j++;
                newRow = false;
            }
            newRow = true;
        }
        for (y = 1; y < board.getSize(); y++) {
            i = 0;
            j = y;
            while (i < board.getSize() && j < board.getSize()) {
                pos = new Position(i, j);
                if (findScheme(pos, nextMoveMark)) {
                    return returnMove;
                }
                i++;
                j++;
                newRow = false;
            }
            newRow = true;
        }
//ukos //
        for (x = 0; x < board.getSize(); x++) {
            i = x;
            j = 0;
            while (i >= 0 && j < board.getSize()) {
                pos = new Position(i, j);
                if (findScheme(pos, nextMoveMark)) {
                    return returnMove;
                }
                i--;
                j++;
                newRow = false;
            }
            newRow = true;
        }
        for (y = 1; y < board.getSize(); y++) {
            i = board.getSize()-1;
            j = y;
            while (i >= 0 && j < board.getSize()) {
                pos = new Position(i, j);
                if (findScheme(pos, nextMoveMark)) {
                    return returnMove;
                }
                i--;
                j++;
                newRow = false;
            }
            newRow = true;
        }
        if (licznikBlokad > 1){
            throw new ResignException();
        }
        if (CzyMozliwoscObrony){
            return config.getReturnMove();
        }
        if (czyKoniec){
            if (licznikBlokad >= 1){
                return config.getReturnMove();
            }
        }
        return null;
    }

    protected abstract boolean findScheme(Position position, Mark mark) throws TheWinnerIsException, ResignException;
}

