package singleton;

import fais.zti.oramus.gomoku.Mark;
import fais.zti.oramus.gomoku.Move;

public class GomokuConfig {

    private static GomokuConfig instance = null;

    private Mark firstMark;
    private int size;
    private boolean periodic;
    private Move returnMove;

    private GomokuConfig() {
        this.firstMark = null;
        this.size = 0;
        this.periodic = false;
        this.returnMove = null;
    }

    public static GomokuConfig getInstance() {
        if (instance == null) {
            synchronized (GomokuConfig.class) {
                if (instance == null) {
                    instance = new GomokuConfig();
                }
            }
        }
        return instance;
    }

    public void reset() {
        this.firstMark = null;
        this.size = 0;
        this.periodic = false;
        this.returnMove = null;
    }

    public void setFirstMark(Mark mark) {
        this.firstMark = mark;
    }

    public void setBoardSize(int size) {
        this.size = size;
    }

    public void setPeriodic(boolean periodic) {
        this.periodic = periodic;
    }

    public int getBoardSize() {
        return this.size;
    }

    public boolean isPeriodic() {
        return this.periodic;
    }

    public Mark getFirstMark() {
        return this.firstMark;
    }

    public Move getReturnMove() {return this.returnMove;}

    public void setReturnMove(Move returnMove) {this.returnMove = returnMove;}
}
