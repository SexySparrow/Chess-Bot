import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop(boolean color)
    {
        this.color = color;
        this.value = 200;
    }

    @Override
    public String toString() {
        return " B  ";
    }


    //TODO implementeaza motode din Piece
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
        move.value = Integer.MIN_VALUE;
        //sus
        bestPosition(board,move,1,1, moves);
        //jos
        bestPosition(board,move, -1,-1, moves);
        //dreapta
        bestPosition(board,move,-1,1, moves);
        //stanga
        bestPosition(board,move, 1,-1, moves);
    }

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
