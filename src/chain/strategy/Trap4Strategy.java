package chain.strategy;

import fais.zti.oramus.gomoku.*;
import singleton.GomokuConfig;

public class Trap4Strategy extends ScenarioStrategy {
    protected GomokuConfig config = GomokuConfig.getInstance();
    protected Integer modRow, modCol, trap4_0 = 0, trap4_1 = 0, trap4_1_alt, trap4_2 = 0, trap4_3 = 0;
    protected Mark obserwowanyMarek_1 = null,  obserwowanyMarek_2 = null, obserwowanyMarek_3 = null, obserwowanyMarek_0 = null, obserwowanyMarek_1_alt = null;
    protected Position modPosition, pozycjaGapy_0, pozycjaGapy_1, pozycjaGapy_1_alt, pozycjaGapy_2, pozycjaGapy_3;
    protected Move lokalnyMove;

    @Override
    protected boolean findScheme(Position position, Mark nextMoveMark) throws TheWinnerIsException, ResignException {
        /*
       0 _ _ X X X _
       1 _ x _ x x _
       2 _ x x _ x _
       3 _ X X X _ _
         */
        modRow = position.row() % config.getBoardSize();
        modCol = position.col() % config.getBoardSize();
        modPosition = new Position(modCol, modRow);
        Mark boardMark = board.getMarkAt(modPosition);

        if (newRow) {
            if (boardMark == Mark.NULL) {
                ClearAndSet1();
            } else {
                ClearCounters();
            }
            return false;
        }

        if (boardMark == Mark.NULL) {
//0 _ _ X X X _
            if (trap4_0 == 1 || trap4_0==2) pozycjaGapy_0=modPosition;
            if (trap4_0 <= 1){
                trap4_0 ++;
            }
            else if (trap4_0 == 5) {
                lokalnyMove = new Move(pozycjaGapy_0, nextMoveMark);
                board.makeMove(lokalnyMove);
                if (Trap4Detected(lokalnyMove, obserwowanyMarek_0)) {
                    return true;
                }
                ClearAndSet1();
            }else if (trap4_0 > 2) trap4_0 = 1;
//1 _ x _ x x _
            if (trap4_1 == 2) pozycjaGapy_1=modPosition;
            if (trap4_1 <= 1) trap4_1 = 1;
            else if (trap4_1 == 2) trap4_1++;
            else if (trap4_1 == 5){
                lokalnyMove = new Move(pozycjaGapy_1, nextMoveMark);
                board.makeMove(lokalnyMove);
                if (Trap4Detected(lokalnyMove, obserwowanyMarek_1)) {
                    return true;
                }
                ClearAndSet1();
            }else trap4_1 = 1;

//1 _ x _ x x _
            if (trap4_1_alt == 2) pozycjaGapy_1_alt=modPosition;
            if (trap4_1 == 3) trap4_1_alt = 1;
            else if (trap4_1_alt == 2) trap4_1_alt++;
            else if (trap4_1_alt == 5){
                lokalnyMove = new Move(pozycjaGapy_1_alt, nextMoveMark);
                board.makeMove(lokalnyMove);
                if (Trap4Detected(lokalnyMove, obserwowanyMarek_1_alt)) {
                    return true;
                }
                ClearCounters();
            }else trap4_1_alt = 0;

//2 _ x x _ x _
            if (trap4_2 == 3) pozycjaGapy_2=modPosition;
            if (trap4_2 <= 1) trap4_2 = 1;
            else if (trap4_2 == 3) trap4_2++;
            else if (trap4_2 == 5){
                lokalnyMove = new Move(pozycjaGapy_2, nextMoveMark);
                board.makeMove(lokalnyMove);
                if (Trap4Detected(lokalnyMove, obserwowanyMarek_2)) {
                    return true;
                }
                ClearCounters();
            }else trap4_2 = 1;

//3 _ X X X _ _
            if (trap4_3 == 4) pozycjaGapy_3=modPosition;
            if (trap4_3 <= 1) trap4_3 = 1;
            else if (trap4_3 == 4) trap4_3++;
            else if (trap4_3 == 5){
                lokalnyMove = new Move(pozycjaGapy_3, nextMoveMark);
                board.makeMove(lokalnyMove);
                if (Trap4Detected(lokalnyMove, obserwowanyMarek_3)) {
                    return true;
                }
                ClearCounters();
            }else trap4_3 = 1;
            //
        }else{
            //===================================================
//0 _ _ X X X _
            if (trap4_0 == 2)obserwowanyMarek_0 = boardMark;
            if ((trap4_0 <=4) && (trap4_0 >= 2) && (obserwowanyMarek_0 == boardMark)) {
                trap4_0++;
            }
            else {
                trap4_0 = 0;
                pozycjaGapy_0 = null;
                obserwowanyMarek_0 = null;
            }
//1 _ x _ x x _
            if (trap4_1 == 1)obserwowanyMarek_1 = boardMark;
            if ((trap4_1 == 3) && (obserwowanyMarek_1 != boardMark)){
                obserwowanyMarek_1 = boardMark;
                trap4_1 = 1;
            }
            if ((trap4_1 <=4) && (trap4_1 != 2) && (trap4_1 >= 1) && (obserwowanyMarek_1==boardMark)) trap4_1++;
            else {
                trap4_1 = 0;
                pozycjaGapy_1 = null;
                obserwowanyMarek_1 = null;
            }
//2 _ x x _ x _
            if (trap4_2 == 1)obserwowanyMarek_2 = boardMark;
            if ((trap4_2 == 4) && (obserwowanyMarek_2 != boardMark)){
                obserwowanyMarek_2 = boardMark;
                trap4_2 = 1;
            }
            if ((trap4_2 <=4) && (trap4_2 != 3) && (trap4_2 >= 1) && (obserwowanyMarek_2==boardMark)) trap4_2++;
            else {
                trap4_2 = 0;
                pozycjaGapy_2 = null;
                obserwowanyMarek_2 = null;
            }
//3 _ X X X _ _
            if (trap4_3 == 1) obserwowanyMarek_3 = boardMark;
            if (trap4_3 == 5){
                trap4_3 = 1;
                obserwowanyMarek_3 = boardMark;
            }
            if ((trap4_3 <= 3) && (trap4_3 >= 1) && (obserwowanyMarek_3 == boardMark)) trap4_3++;
            else {
                trap4_3 = 0;
                pozycjaGapy_3 = null;
                obserwowanyMarek_3 = null;
            }
//1 _ x _ x x _
            if (trap4_1_alt == 1)obserwowanyMarek_1_alt = boardMark;
            if ((trap4_1_alt == 3) && (obserwowanyMarek_1_alt != boardMark)){
                obserwowanyMarek_1_alt = boardMark;
                trap4_1_alt = 1;
            }
            if ((trap4_1_alt >= 1) && (trap4_1_alt <=4) && (trap4_1_alt != 2) && (obserwowanyMarek_1_alt == boardMark)) trap4_1_alt++;
            else {
                trap4_1_alt = 0;
                pozycjaGapy_1_alt = null;
                obserwowanyMarek_1_alt = null;
            }
        }

        return false;
    }

    protected void ClearCounters(){
        trap4_0 = 0;
        trap4_1 = 0;
        trap4_1_alt = 0;
        trap4_2 = 0;
        trap4_3 = 0;
        UstawNulle();
    }

    private void UstawNulle() {
        pozycjaGapy_0 = null;
        pozycjaGapy_1 = null;
        pozycjaGapy_1_alt = null;
        pozycjaGapy_2 = null;
        pozycjaGapy_3 = null;
        obserwowanyMarek_1 = null;
        obserwowanyMarek_1_alt = null;
        obserwowanyMarek_2 = null;
        obserwowanyMarek_3 = null;
        obserwowanyMarek_0 = null;
    }

    protected void ClearAndSet1(){
        trap4_0 = 1;
        trap4_1 = 1;
        trap4_1_alt = 0;
        trap4_2 = 1;
        trap4_3 = 1;
        UstawNulle();
    }

    protected boolean Trap4Detected(Move lokalnyMove, Mark obserwowanyMarek) throws ResignException{
        if (CzyZagrozenie){
            if (lokalnyMove.mark() != obserwowanyMarek){    // przeciwnika pulapka - GAME OVER
                throw new ResignException();
            }else{
                CzyMozliwoscObrony = true;
            }
        }else{
            if (lokalnyMove.mark() == obserwowanyMarek){    // moja pulapka - WIN
                returnMove = lokalnyMove;
                config.setReturnMove(lokalnyMove);
                return true;
            }else{
                if (config.getReturnMove() == null){
                    config.setReturnMove(lokalnyMove);
                }
                licznikBlokad++;
            }
        }
        return false;
    }
}