package lt.vgtu.isk.psp;

public class RandomFactor implements IRandomFactor{

	public boolean randomChangeHappened() {
		return Math.random()*10 > 8;
	}
}
