package lt.vgtu.isk.psp;

public class ExitCommand extends AbstractCommand {

	@Override
	public void execute() {
		System.exit(1);
	}

}
