import java.util.ArrayList;

public class Rook extends Piece {
    public Rook(boolean color)
    {
        this.color = color;
        this.value = 300;
    }

    @Override
    public String toString() {
        return " R  ";
    }

    @Override
    public void getMoves(Piece[][] board, int x, int y, ArrayList<Move> moves) {
        Move move = new Move();
        move.start_x = x;
        move.start_y = y;

        if(board[x][y].color)
            makeMoveWhite(board, move, moves);
        else
            makeMoveBlack(board, move, moves);
    }

    @Override
    public void makeMoveBlack(Piece[][] board, Move move, ArrayList<Move> moves) {
        Move[] moves2 = new Move[4];
        for (int i = 0; i < 4; i++)
        {
            moves2[i].value = Integer.MIN_VALUE;
            moves2[i].start_x = move.start_x;
            moves2[i].start_y = move.start_y;
        }
        move.value = Integer.MIN_VALUE;
        //sus
        bestPosition(board,moves2[0],1,0, moves);
        //jos
        bestPosition(board,moves2[1], -1,0, moves);
        //dreapta
        bestPosition(board,moves2[2],0,1, moves);
        //stanga
        bestPosition(board,moves2[3], 0,-1, moves);
    }
    //turele sunt piese simetrice pentru ambele culori
    @Override
    public void makeMoveWhite(Piece[][] board, Move move, ArrayList<Move> moves) {
         makeMoveBlack(board, move, moves);
    }

    private void bestPosition(Piece[][] board, Move move, int increment_x, int increment_y, ArrayList<Move> moves)
    {
        int x = move.start_x, y = move.start_y;
        x += increment_x;
        y += increment_y;
        while (isValid(x, y)){

            if(board[x][y] != null)
            {
                if(board[x][y].color != board[move.start_x][move.start_y].color){
                    move.value = board[x][y].value;
                    move.final_x = x;
                    move.final_y = y;
                    moves.add(new Move(move.start_x, move.start_y, x, y, move.value));
                }
            }
            else
            {
                moves.add(new Move(move.start_x, move.start_y,x,y,0));
            }
            x += increment_x;
            y += increment_y;
        }
    }
}

