package chain.strategy;

import fais.zti.oramus.gomoku.*;
import singleton.GomokuConfig;

public class FinishedStrategy extends ScenarioStrategy {
    protected GomokuConfig config = GomokuConfig.getInstance();
    protected Integer licznik =0;
    protected Mark obserwowanyMarek= Mark.NULL;
    @Override
    protected boolean findScheme(Position position, Mark nextMoveMark) throws TheWinnerIsException, ResignException {
        Mark boardMark = board.getMarkAt(position);
        if (boardMark == Mark.NULL){
            licznik=0;
            return false;
        }
        if (newRow) licznik=0;

        if (obserwowanyMarek == boardMark) licznik++;
        else{
            licznik = 1;
            obserwowanyMarek = boardMark;
        }
        if (licznik == 5){
            throw new TheWinnerIsException(obserwowanyMarek);
        }
        return false;
    }
}

