package factory;

import fais.zti.oramus.gomoku.Mark;
import fais.zti.oramus.gomoku.Move;
import fais.zti.oramus.gomoku.Position;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Board {
    protected int size;
    protected boolean isPeriodic;
    protected Map<Position, Mark> boardMap;

    public Board(int size, boolean isPeriodic) {
        this.size = size;
        this.isPeriodic = isPeriodic;
        this.boardMap = new HashMap<>();
    }

    public int getSize() {
        return size;
    }

    public boolean isPeriodic() {
        return isPeriodic;
    }

    public Map<Position, Mark> getBoardMap() {
        return boardMap;
    }

    public Mark getMarkAt(Position position) {
        return boardMap.getOrDefault(position, Mark.NULL);
    }

    public void makeMove(Move move) {
        boardMap.put(move.position(), move.mark());
    }
}


