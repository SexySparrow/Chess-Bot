public class Bishop extends Piece {
    public Bishop(boolean color)
    {
        this.color = color;
    }

    @Override
    public String toString() {
        return " B  ";
    }

    //TODO implementeaza motode din Piece

    public static void bishop (Piece[][] board, int i , int j, ArrayList moveList, boolean color) {

    	int aux_i = i;
    	int aux_j = j;

    	while (isValid(aux_i+1, aux_j+1)) {

    		if(board[aux_i+1][aux_j+1]==null) {
    			moveList.add(new Move(i,j,aux_i+1,aux_j+1));
    			aux_i++;
    			aux_j++;
    			continue;
    		}

    		if(board[aux_i+1][aux_j+1].color == color)
    			break;
    		if(board[aux_i+1][aux_j+1].color != color) {
    			moveList.add(new Move(i,j,aux_i+1,aux_j+1));
    			aux_i++;
    			aux_j++;
    			break;
    		}
    	}

    	aux_i = i;
    	aux_j = j;

    	while (isValid(aux_i+1, aux_j-1)) {

    		if(board[aux_i+1][aux_j-1]==null) {
    			moveList.add(new Move(i,j,aux_i+1,aux_j-1));
    			aux_i++;
    			aux_j--;
    			continue;
    		}

    		if(board[aux_i+1][aux_j-1].color == color)
    			break;
    		if(board[aux_i+1][aux_j-1].color != color) {
    			moveList.add(new Move(i,j,aux_i+1,aux_j-1));
    			aux_i++;
    			aux_j--;
    			break;
    		}
    	}
    	aux_i = i;
    	aux_j = j;
    	while (isValid(aux_i-1, aux_j-1)) {

    		if(board[aux_i-1][aux_j-1]==null) {
    			moveList.add(new Move(i,j,aux_i-1,aux_j-1));
    			aux_i--;
    			aux_j--;
    			continue;
    		}

    		if(board[aux_i-1][aux_j-1].color == color)
    			break;
    		if(board[aux_i-1][aux_j-1].color != color) {
    			moveList.add(new Move(i,j,aux_i-1,aux_j-1));
    			aux_i--;
    			aux_j--;
    			break;
    		}
    	}

    	aux_i = i;
    	aux_j = j;

    	while (isValid(aux_i-1, aux_j+1)) {

    		if(board[aux_i-1][aux_j+1]==null) {
    			moveList.add(new Move(i,j,aux_i-1,aux_j+1));
    			aux_i--;
    			aux_j++;
    			continue;
    		}

    		if(board[aux_i-1][aux_j+1].color == color)
    			break;
    		if(board[aux_i-1][aux_j+1].color != color) {
    			moveList.add(new Move(i,j,aux_i-1,aux_j+1));
    			aux_i--;
    			aux_j++;
    			break;
    		}
    	}
    }
}
