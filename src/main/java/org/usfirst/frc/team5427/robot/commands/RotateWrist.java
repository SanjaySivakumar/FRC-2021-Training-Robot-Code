package org.usfirst.frc.team5427.robot.commands;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.command.Command;

public class RotateWrist extends Command {

    private double speed;

    public RotateWrist(double speed) {
        requires(Robot.wrist);
        this.speed = speed;
    }

    protected void initialize()
    {
        this.setInterruptible(true);
    }

    protected void execute()
    {
        Robot.wrist.moveWrist(speed);
    }

    protected boolean isFinished() { return (speed > 0) ? !Robot.oi.getJoy().getRawButton(Config.BUTTON_WRIST_UP) : (speed < 0) && !Robot.oi.getJoy().getRawButton(Config.BUTTON_WRIST_UP); }

    protected void end() { Robot.wrist.stop(); }
}