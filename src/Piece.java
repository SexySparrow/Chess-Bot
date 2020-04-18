public abstract class Piece {
    //true is for white
    //false is for niggers
    public boolean color = true;
    public int value;

    //returneaza, intr-un obiect de tip move, cea mai buna miscare pentru
    //piesa a carei coordonate sunt primite
    public abstract Move getMove(Piece[][] board , int x, int y);
    //calculeaza miscarile posibile pentru negru
    public abstract Move makeMoveBlack(Piece[][] board, Move move);
    //si pentru alb
    public abstract Move makeMoveWhite(Piece[][] board, Move move);
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
