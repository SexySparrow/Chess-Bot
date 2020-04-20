public class Rook extends Piece {
    public Rook(boolean color)
    {
        this.color = color;
    }

    @Override
    public String toString() {
        return " R  ";
    }

    @Override
    public Move getMove(Piece[][] board, int x, int y) {
        Move move = new Move();
        move.start_x = x;
        move.start_y = y;

        if(board[x][y].color)
            move = makeMoveWhite(board, move);
        else
            move = makeMoveBlack(board, move);

        return move;
    }

    @Override
    public Move makeMoveBlack(Piece[][] board, Move move) {
        Move[] moves = new Move[4];
        for (int i = 0; i < 4; i++)
        {
            moves[i].value = Integer.MIN_VALUE;
            moves[i].start_x = move.start_x;
            moves[i].start_y = move.start_y;
        }
        move.value = Integer.MIN_VALUE;
        //sus
        bestPosition(board,moves[0],1,0);
        //jos
        bestPosition(board,moves[1], -1,0);
        //dreapta
        bestPosition(board,moves[2],0,1);
        //stanga
        bestPosition(board,moves[3], 0,-1);

        for (int i = 0; i < 4; i++){
            if(moves[i].value > move.value)
                move = moves[i];
        }
        return move;
    }
    //turele sunt piese simetrice pentru ambele culori
    @Override
    public Move makeMoveWhite(Piece[][] board, Move move) {
        return makeMoveBlack(board, move);
    }

    private void bestPosition(Piece[][] board, Move move, int increment_x, int increment_y)
    {
        int x = move.start_x, y = move.start_y;
        while (isValid(x, y)){
            x += increment_x;
            y += increment_y;

            if(board[x][y] != null)
            {
                if(board[x][y].color != board[move.start_x][move.start_y].color){
                    move.value = board[x][y].value;
                    move.final_x = x;
                    move.final_y = y;
                }
                else
                {
                    //daca piesa e aliata ramanem langa ea
                    move.value = 10;
                    move.final_y = y - increment_y;
                    move.final_x = x - increment_x;
                }
            }
        }
        //in cazul in care toata linia/coloana e libera
        if(!isValid(x,y))
        {
            move.value = 10;
            move.final_y = y - increment_y;
            move.final_x = x - increment_x;
        }
    }

    public static void rook (Piece[][] board, int i , int j, ArrayList moveList, boolean color, int depth) {

        int aux_i = i;
        int aux_j = j;
        int aux_depth=depth;
        while (isValid(aux_i+1, aux_j) && aux_depth) {
            aux_depth--;
            if(board[aux_i+1][aux_j]==null) {
                moveList.add(new Move (i,j,aux_i+1, aux_j));
                aux_i++;
                continue;
            }
            if(board[aux_i+1][aux_j].color == color)
                break;
            if(board[aux_i+1][aux_j].color != color) {
                moveList.add(new Move (i,j,aux_i+1, aux_j));
                break;
            }
            
        }
        aux_i = i
        aux_j = j;
        aux_depth=depth;
        while (isValid(aux_i-1, aux_j) && aux_depth) {
            aux_depth--;
            if(board[aux_i+1][aux_j]==null) {
                moveList.add(new Move (i,j,aux_i-1, aux_j));
                aux_i--;
                continue;
            }
            if(board[aux_i-1][aux_j].color == color)
                break;
            if(board[aux_i-1][aux_j].color != color) {
                moveList.add(new Move (i,j,aux_i-1, aux_j));
                break;
            }
            
        }

        aux_i = i
        aux_j = j;
        aux_depth=depth;

        while (isValid(aux_i, aux_j+1) && aux_depth) {
            aux_depth--;
            if(board[aux_i+1][aux_j]==null) {
                moveList.add(new Move (i,j,aux_i, aux_j+1));
                aux_j++;
                continue;
            }
            if(board[aux_i][aux_j+1].color == color)
                break;
            if(board[aux_i][aux_j+1].color != color) {
                moveList.add(new Move (i,j,aux_i, aux_j+1));
                break;
            }
            
        }

        aux_i = i
        aux_j = j;
        aux_depth=depth;

        while (isValid(aux_i, aux_j-1) && aux_depth) {
            aux_depth--;
            if(board[aux_i+1][aux_j]==null) {
                moveList.add(new Move (i,j,aux_i, aux_j-1));
                aux_j--;
                continue;
            }

            if(board[aux_i][aux_j-1].color == color)
                break;
            if(board[aux_i][aux_j-1].color != color) {
                moveList.add(new Move (i,j,aux_i, aux_j-1));
                break;
            }
            
        }
    }
}

