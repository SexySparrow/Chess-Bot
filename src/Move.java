public class Move {
	public int start_x, start_y;
	public int final_x, final_y;
	public int value;

	public Move(int start_x, int start_y, int final_x, int final_y, int value) {
		this.start_x = start_x;
		this.start_y = start_y;
		this.final_x = final_x;
		this.final_y = final_y;
		this.value = value;
	}

	public Move() {
	}
}
