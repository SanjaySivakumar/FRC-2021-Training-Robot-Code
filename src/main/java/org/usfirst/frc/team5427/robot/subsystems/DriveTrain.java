package org.usfirst.frc.team5427.robot.subsystems;



import org.usfirst.frc.team5427.robot.commands.DriveWithJoystick;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain extends Subsystem {

	private DifferentialDrive drive;
	private SpeedControllerGroup driveLeft;
	private SpeedControllerGroup driveRight;

	public DriveTrain(SpeedControllerGroup drive_Left, SpeedControllerGroup drive_Right, DifferentialDrive drive) {
		this.drive = drive;
		this.driveLeft = drive_Left;
		this.driveRight = drive_Right;
	}

	protected void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick());
	}

	public void takeJoystickInputs(Joystick joy) { drive.arcadeDrive(joy.getY() * 0.70, -joy.getZ() * 0.75 * 0.70); }

	public void tankDrive(double leftSpeed, double rightSpeed) {
		driveRight.set(rightSpeed);
		driveLeft.set(leftSpeed);
	}

	public void stop() {
		drive.stopMotor();
	}
}