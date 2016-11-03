package lt.vgtu.isk.psp;
public class Ghost {
	int x;
	int y;
	int dx;
	int dy;
	
	public Ghost(int x, int y, int dx, int dy){
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
	}

	public void move() {
		x+= dx;
		y+= dy;
	}
	
	public void revertMovementVectors() {
		dx *= -1;
		dy *= -1;
	}
	
	public void swapMovementVectors() {
		int t = dx;
		dx=dy;
		dy=t;
	}
}