import java.util.ArrayList;

public abstract class Piece {
    //true is for white
    //false is for niggers
    public boolean color = true;
    public int value;

    //returneaza, intr-un obiect de tip move, cea mai buna miscare pentru
    //piesa a carei coordonate sunt primite
    public abstract void getMoves(Piece[][] board , int x, int y, ArrayList<Move> moves);
    //calculeaza miscarile posibile pentru negru
    public abstract void makeMoveBlack(Piece[][] board, Move move, ArrayList<Move> moves);
    //si pentru alb
    public abstract void makeMoveWhite(Piece[][] board, Move move, ArrayList<Move> moves);
    //verfica daca indexul se afla pe tabla
    public boolean isValid (int x, int y)
    {
        if ( x > 7 || x < 0)
            return false;
        if ( y > 7 || y < 0)
            return false;
        return true;
    }
}
