public class Pawn extends Piece {

    public Pawn(boolean color)
    {
        this.color = color;
    }

    @Override
    public String toString() {
        return " P  ";
    }


    public int move(Piece[][] board, int poz_i, int poz_j) {
        if(this.color)
            return moveWhite(board,poz_i,poz_j);
        return moveBlack(board,poz_i,poz_j);
    }

    public int moveWhite(Piece[][] board, int poz_i, int poz_j) {
        return 0;
    }
    public int moveBlack(Piece[][] board, int poz_i, int poz_j){
        return 0;
    }
}
