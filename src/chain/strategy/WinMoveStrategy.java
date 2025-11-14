package chain.strategy;

import fais.zti.oramus.gomoku.*;
import singleton.GomokuConfig;

public class WinMoveStrategy extends ScenarioStrategy {
    protected GomokuConfig config = GomokuConfig.getInstance();
    protected Integer licznik =0, przedGapie, poGapie, rowGapa, colGapa;
    protected Mark obserwowanyMarek= Mark.NULL;
    protected Position pozycjaGapy;
    @Override
    protected boolean findScheme(Position position, Mark nextMoveMark) throws TheWinnerIsException, ResignException {
        Mark boardMark = board.getMarkAt(position);
        if ((boardMark != nextMoveMark) && (boardMark != Mark.NULL)){
            licznik=0;
            przedGapie=0;
            poGapie=0;
            pozycjaGapy = null;
            return false;
        }
        if (newRow){
            if (boardMark == Mark.NULL) {
                licznik = 1;
                przedGapie = 0;
                poGapie = 0;
                pozycjaGapy = position;
            }else{
                licznik=1;
                przedGapie=1;
                poGapie=0;
                pozycjaGapy = null;
            }
            return false;
        }
        if (pozycjaGapy == null){
            if (boardMark == Mark.NULL) {
                licznik ++;
                przedGapie ++;
                poGapie = 0;
                pozycjaGapy = position;
            }else{
                licznik++;
                przedGapie++;
                poGapie=0;
            }
        }else{
            if (boardMark == Mark.NULL) {
                licznik = poGapie + 1;
                przedGapie = poGapie;
                poGapie = 0;
                pozycjaGapy = position;
            }else{
                licznik++;
                przedGapie=0;
                poGapie++;
            }
        }

        if(licznik==5 && pozycjaGapy != null){
            rowGapa = pozycjaGapy.row() % config.getBoardSize();
            colGapa = pozycjaGapy.col() % config.getBoardSize();
            returnMove = new Move(new Position(colGapa, rowGapa), nextMoveMark);
            return true;
        }
        return false;
    }
}

