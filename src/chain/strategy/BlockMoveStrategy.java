package chain.strategy;

import fais.zti.oramus.gomoku.*;
import singleton.GomokuConfig;

public class BlockMoveStrategy extends ScenarioStrategy {
    protected GomokuConfig config = GomokuConfig.getInstance();
    protected Integer licznik =0, przedGapie, poGapie, rowGapa, colGapa, modCol, modRow, trap5=0;
    protected Mark obserwowanyMarek= Mark.CROSS;
    protected Position modPosition, pozycjaGapy, blokadaGapy, blokada_2;
    protected String linia;
    @Override
    protected boolean findScheme(Position position, Mark nextMoveMark) throws TheWinnerIsException, ResignException {
        modRow = position.row() % config.getBoardSize();
        modCol = position.col() % config.getBoardSize();
        modPosition = new Position(modCol, modRow);

        if (obserwowanyMarek == nextMoveMark) {
            obserwowanyMarek = Mark.NOUGHT;
        }
        Mark boardMark = board.getMarkAt(modPosition);

        if ((boardMark != obserwowanyMarek) && (boardMark != Mark.NULL)){
            licznik=0;
            przedGapie=0;
            poGapie=0;
            pozycjaGapy = null;
            trap5=0;
            return false;
        }
        if (newRow){
            if (boardMark == Mark.NULL) {
                licznik = 1;
                przedGapie = 0;
                poGapie = 0;
                pozycjaGapy = modPosition;
                trap5=1;
            }else{
                licznik=1;
                przedGapie=1;
                poGapie=0;
                trap5=0;
                pozycjaGapy = null;
            }
            return false;
        }
        CheckTrap5(boardMark, nextMoveMark);
        if (pozycjaGapy == null){
            if (boardMark == Mark.NULL) {
                licznik ++;
                przedGapie ++;
                poGapie = 0;
                pozycjaGapy = modPosition;
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
                pozycjaGapy = modPosition;
            }else{
                licznik++;
                przedGapie=0;
                poGapie++;
            }
        }

        if(licznik==5){
            if (licznikBlokad == 0){
                blokadaGapy = pozycjaGapy;
                rowGapa = pozycjaGapy.row() % config.getBoardSize();
                colGapa = pozycjaGapy.col() % config.getBoardSize();
                blokadaGapy = new Position(colGapa, rowGapa);
                returnMove = new Move(blokadaGapy, nextMoveMark);
                config.setReturnMove(returnMove);
                board.makeMove(returnMove);
                licznik=0;
                pozycjaGapy=null;
                licznikBlokad++;
            } else {
                rowGapa = pozycjaGapy.row() % config.getBoardSize();
                colGapa = pozycjaGapy.col() % config.getBoardSize();
                blokada_2 = new Position(colGapa, rowGapa);
                if (!blokada_2.equals(blokadaGapy)) {
                    licznikBlokad++;
                    throw new ResignException();
                }else {
                    licznik=0;
                    pozycjaGapy=null;
                }
            }
        }
        return false;
    }

    protected void CheckTrap5(Mark boardMark, Mark nextMoveMark) throws ResignException {
        if (boardMark == Mark.NULL){
            if (trap5==5){// _ X X X X _
//                System.out.println("Trap5");
                throw new ResignException();
            } else {
                trap5=1;
            }
        } else if (boardMark == nextMoveMark) {
            trap5=0;
        }else{
            if (trap5 > 0){
                trap5++;
            }
        }
    }
}