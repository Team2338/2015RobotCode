package team.gif.commands.elevator;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import team.gif.Globals;
import team.gif.Robot;

/**
 * @author Patrick Ubelhor
 */
public class ElevSetpoint extends Command {
	
	private double position;
	private double initTime;
	
	public ElevSetpoint(double setpoint) {
		requires(Robot.elevator);
		position = setpoint;
	}
	
	protected void initialize() {
		Globals.elevatorSetpoint = position;
		initTime = Timer.getFPGATimestamp();
	}
	
	protected void execute() {
		Robot.elevator.drive(position);
	}
	
	protected boolean isFinished() {
		if (Robot.elevator.getMin()) {
			Robot.elevator.reset();
			return true;
		}
		
		return (Math.abs(Robot.elevator.getError()) < 100 && Timer.getFPGATimestamp() - initTime > 0.01);
	}
	
	protected void end() {}
	
	protected void interrupted() {}
	
}
