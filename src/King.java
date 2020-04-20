import java.util.ArrayList;

public class King extends Piece {
    public King(boolean color)
    {
        this.color = color;
        this.value = 10000;
    }

    @Override
    public String toString() {
        return " K  ";
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

        int[] index_x = {1,1,1,0,0,-1,-1,-1};
        int[] index_y = {0,1,-1,1,-1,0,1,-1};

        for (int i = 0; i < 8; i++)
        {
            move.final_x = move.start_x + index_x[i];
            move.final_y = move.start_y + index_y[i];

            move = checkPosition(board, move);

            if(move != null)
                moves.add(new Move(move.start_x,move.start_y,move.final_x, move.final_y,move.value));
        }
    }

    @Override
    public void makeMoveWhite(Piece[][] board, Move move, ArrayList<Move> moves) {
        makeMoveBlack(board, move, moves);
    }
    //TODO implementeaza motode din Piece
    public Move checkPosition(Piece[][] board, Move move){

        move.value = Integer.MIN_VALUE;
        if (!isValid(move.final_x, move.final_y))
            return null;

        if(board[move.final_x][move.final_y] == null){
            move.value = 0;
            return move;
        }

        if(board[move.start_x][move.start_y].color != board[move.final_x][move.final_y].color){
            move.value = board[move.final_x][move.final_y].value;
            return move;
        }
        return null;
    }
}

