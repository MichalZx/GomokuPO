package chain.strategy;

import fais.zti.oramus.gomoku.*;
import singleton.GomokuConfig;

public class Trap3Strategy extends ScenarioStrategy {
    protected GomokuConfig config = GomokuConfig.getInstance();
    protected Integer modRow, modCol, trap3_1 = 0, trap3_2 = 0, trap3_3 = 0, trap3_4 = 0, trap3_5 = 0, trap3_5_alt = 0;
    protected Mark obserwowanyMarek_1 = null, obserwowanyMarek_2 = null, obserwowanyMarek_3 = null, obserwowanyMarek_4 = null, obserwowanyMarek_5 = null ,obserwowanyMarek_5_alt = null;
    protected Position modPosition, pozycjaGapy_4, pozycjaGapy_1, pozycjaGapy_2, pozycjaGapy_3, pozycjaGapy_5, pozycjaGapy_5_alt;
    protected Move lokalnyMove;

    @Override
    protected boolean findScheme(Position position, Mark nextMoveMark) throws TheWinnerIsException, ResignException {

        /*
       / 1 2 3 4 5 6
       1 _ X X * _ _
       2 _ _ X X * _
       3 _ * X X _ _
       4 _ _ * X X _
       5 _ X * X _
         */
        modRow = position.row() % config.getBoardSize();
        modCol = position.col() % config.getBoardSize();
        modPosition = new Position(modCol, modRow);
        Mark boardMark = board.getMarkAt(modPosition);
        czyKoniec=true;

        if (newRow) {
            if (boardMark == Mark.NULL) {
                ClearAndSet1();
            } else {
                ClearCounters();
            }
            return false;
        }
        if (boardMark == Mark.NULL) {
//1 _ X X * _ _
            if (trap3_1 == 3) pozycjaGapy_1 = modPosition;
            if (trap3_1 == 5){
                if (CheckForTrap(pozycjaGapy_1, obserwowanyMarek_1, nextMoveMark)){
                    return true;
                }
                trap3_1 = 1;
                pozycjaGapy_1 = null;
                obserwowanyMarek_1 = null;
            }else if (trap3_1 >= 3){
                trap3_1 ++;
            }else{
                trap3_1 = 1;
                pozycjaGapy_1 = null;
                obserwowanyMarek_1 = null;
            }
//2 _ _ X X * _
            if (trap3_2 == 4) pozycjaGapy_2 = modPosition;
            if (trap3_2 == 5){
                if (CheckForTrap(pozycjaGapy_2, obserwowanyMarek_2, nextMoveMark)){
                    return true;
                }
                trap3_2 = 2;
                pozycjaGapy_2 = null;
                obserwowanyMarek_2 = null;
            }else if (trap3_2 <= 1 || trap3_2 >= 4) trap3_2 ++;
            else if (trap3_2 == 2) trap3_2 = 2;
            else{
                trap3_2 = 1;
                pozycjaGapy_2 = null;
                obserwowanyMarek_2 = null;
            }
//3 _ * X X _ _
            if (trap3_3 == 1) pozycjaGapy_3 = modPosition;
            if (trap3_3 == 5){
                if (CheckForTrap(pozycjaGapy_3, obserwowanyMarek_3, nextMoveMark)){
                    return true;
                }
                trap3_3 = 2;
                pozycjaGapy_3 = null;
                obserwowanyMarek_3 = null;
            }else if (trap3_3 == 2){
                trap3_3 = 2;
                pozycjaGapy_3 = modPosition;
            }else if (trap3_3 <= 1 || trap3_3 >= 4){
                trap3_3 ++;
            }else{
                trap3_3 = 1;
                pozycjaGapy_3 = null;
                obserwowanyMarek_3 = null;
            }
//4 _ _ * X X _
            if (trap3_4 == 2) pozycjaGapy_4 = modPosition;
            if (trap3_4 == 3){
                pozycjaGapy_4 = modPosition;
                trap3_4 = 3;
            }else if (trap3_4 == 5){
                if (CheckForTrap(pozycjaGapy_4, obserwowanyMarek_4, nextMoveMark)){
                    return true;
                }
                trap3_4 = 1;
                pozycjaGapy_4 = null;
                obserwowanyMarek_4 = null;
            }else if (trap3_4 <= 2) trap3_4 ++;
            else{
                trap3_4 = 1;
                pozycjaGapy_4 = null;
                obserwowanyMarek_4 = null;
            }
//5 _ X * X _
            if(trap3_5 == 2) pozycjaGapy_5 = modPosition;
            if (trap3_5 == 4){
//                System.out.println("Trap 5 patern: "+ pozycjaGapy_5);
                if (CheckForTrap(pozycjaGapy_5, obserwowanyMarek_5, nextMoveMark)){
                    return true;
                }
                trap3_5 = 1;
                pozycjaGapy_5 = null;
                obserwowanyMarek_5 = null;
            }else if (trap3_5 == 0 || trap3_5 == 2){
                trap3_5 ++;
            }else{
                trap3_5 = 1;
            }
//5 _ X * X _ ALT
            if(trap3_5_alt == 2) pozycjaGapy_5_alt = modPosition;
            if (trap3_5_alt == 4){
//                System.out.println("Trap 5_ALT patern: "+ pozycjaGapy_5_alt);
                if (CheckForTrap(pozycjaGapy_5_alt, obserwowanyMarek_5_alt, nextMoveMark)){
                    return true;
                }
                trap3_5_alt = 1;
                pozycjaGapy_5_alt = null;
                obserwowanyMarek_5_alt = null;
            }else if ((trap3_5_alt == 0 && trap3_5 > 1) || trap3_5_alt == 2){
                trap3_5_alt ++;
            }else{
                trap3_5_alt = 0;
            }
        }else {
            //===================================================
//1 _ X X * _ _
            if (trap3_1 == 1) obserwowanyMarek_1 = boardMark;
            if ((trap3_1 >= 1) && (trap3_1 <= 2) && (obserwowanyMarek_1 == boardMark)) {
                trap3_1++;
            } else {
                trap3_1 = 0;
                pozycjaGapy_1 = null;
                obserwowanyMarek_1 = null;
            }
//2 _ _ X X * _
            if (trap3_2 == 2) obserwowanyMarek_2 = boardMark;
            if (trap3_2 >= 2 && trap3_2 <= 3 && obserwowanyMarek_2 == boardMark) {
                trap3_2++;
            } else {
                trap3_2 = 0;
                pozycjaGapy_2 = null;
                obserwowanyMarek_2 = null;
            }
//3 _ * X X _ _
            if (trap3_3 == 2) {
                obserwowanyMarek_3 = boardMark;
            }
            if (trap3_3 >= 2 && trap3_3 <= 3 && obserwowanyMarek_3 == boardMark) {
                trap3_3++;
            }else{
                trap3_3 = 0;
                pozycjaGapy_3=null;
                obserwowanyMarek_3=null;
            }
//4 _ _ * X X _
            if (trap3_4 == 3){
                obserwowanyMarek_4 = boardMark;
            }
            if (trap3_4 >= 3 && trap3_4 <= 4 && obserwowanyMarek_4== boardMark){
                trap3_4 ++;
            }else {
                trap3_4 = 0;
                pozycjaGapy_4=null;
                obserwowanyMarek_4=null;
            }
//5 _ X * X _
            if (trap3_5 == 1) obserwowanyMarek_5 = boardMark;
            if ((trap3_5 == 1 || trap3_5 == 3) && (obserwowanyMarek_5 == boardMark)) {
                trap3_5 ++;
            }else{
                trap3_5 = 0;
                pozycjaGapy_5 = null;
                obserwowanyMarek_5 = null;
            }
//5 _ X * X _ ALT
            if (trap3_5_alt == 1) obserwowanyMarek_5_alt = boardMark;
            if ((trap3_5_alt == 1 || trap3_5_alt == 3) && (obserwowanyMarek_5_alt == boardMark)) {
                trap3_5_alt ++;
            }else{
                trap3_5_alt = 0;
                pozycjaGapy_5_alt = null;
                obserwowanyMarek_5_alt = null;
            }
        }

        return false;
    }

    protected boolean SzukajTrap3(Position gapaPosition, Mark obserwowanyMarek){
        int[][] directions = {
                {-1, -1}, { 1, 1}, // ukos \\
                { 0, -1}, { 0, 1}, // Pion ||
                { 1, -1}, {-1, 1}, // ukos //
                {-1, 0} , { 1, 0}, // poziom ==
        };
        /*
            1       2
    #1    _ _ X * X _ _
    #2  _ _ X X * _ _
         */
        StringBuilder linia_1 = new StringBuilder(), linia_2= new StringBuilder();
        int pModCol, pModRow, newCol, newRow, dx, dy, j, licznikLini=0;
        Mark marek;
        j=0;
        for (int[] dir : directions) {
            dx = dir[0];
            dy = dir[1];
            j++;

            for (int i = 1; i <= 4; i++) {
                newCol = gapaPosition.col() + dx * i;
                newRow = gapaPosition.row() + dy * i;
                pModRow = newRow % config.getBoardSize();
                pModCol = newCol % config.getBoardSize();

                if ((newCol >= 0) && (newCol < board.getSize()) && (newRow >= 0) && (newRow < board.getSize())) {
                    Position pos = new Position(pModCol, pModRow);
                    marek = board.getMarkAt(pos);
                    if (marek == Mark.NULL){
                        if(j%2==0) linia_1.append("*");
                        else linia_2.append("*");
                    } else if (marek == obserwowanyMarek){
                        if(j%2==0) linia_1.append("x");
                        else linia_2.append("x");
                    }else{
                        break;
                    }
                }else{
                    break;
                }
            }
            if(j%2==0){
//                System.out.println(j + " " + linia_1 + " / " + linia_2);
                if ( (linia_1.toString().startsWith("x**") && linia_2.toString().startsWith("x*")) ||
                     (linia_1.toString().startsWith("x*") && linia_2.toString().startsWith("x**"))) {
                    licznikLini++;
                }else if ((linia_1.toString().startsWith("xx**") && linia_2.toString().startsWith("*")) ||
                         (linia_1.toString().startsWith("xx*") && linia_2.toString().startsWith("**"))||
                         (linia_1.toString().startsWith("*") && linia_2.toString().startsWith("xx**"))||
                         (linia_1.toString().startsWith("**") && linia_2.toString().startsWith("xx*"))) {
                    licznikLini++;
                }
                if (licznikLini==2){
                    return true;
                }
                linia_1 = new StringBuilder();
                linia_2= new StringBuilder();
            }
        }
        return false;
    }

    protected void ClearCounters(){
        trap3_1 = 0;
        trap3_2 = 0;
        trap3_3 = 0;
        trap3_4 = 0;
        trap3_5 = 0;
        trap3_5_alt = 0;

        UstawNulle();
    }

    protected void ClearAndSet1(){
        trap3_1 = 1;
        trap3_2 = 1;
        trap3_3 = 1;
        trap3_4 = 1;
        trap3_5 = 1;
        trap3_5_alt = 0;
        UstawNulle();
    }

    private void UstawNulle() {
        pozycjaGapy_1 = null;
        pozycjaGapy_2 = null;
        pozycjaGapy_3 = null;
        pozycjaGapy_4 = null;
        pozycjaGapy_5 = null;
        pozycjaGapy_5_alt = null;
        obserwowanyMarek_1 = null;
        obserwowanyMarek_2 = null;
        obserwowanyMarek_3 = null;
        obserwowanyMarek_4 = null;
        obserwowanyMarek_5 = null;
        obserwowanyMarek_5_alt = null;
    }

    protected boolean Trap3Detected(Move lokalnyMove, Mark obserwowanyMarek) throws ResignException{
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

    private boolean CheckForTrap(Position pozycjaGapy, Mark obserwowanyMarek, Mark nextMoveMark) throws ResignException {
        if (SzukajTrap3(pozycjaGapy, obserwowanyMarek)) {
            lokalnyMove = new Move(pozycjaGapy, nextMoveMark);
            board.makeMove(lokalnyMove);
            if (Trap3Detected(lokalnyMove, obserwowanyMarek)){
                return true;
            }
            ClearAndSet1();
        }
        return false;
    }
}