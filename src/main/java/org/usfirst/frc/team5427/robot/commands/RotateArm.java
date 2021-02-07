package org.usfirst.frc.team5427.robot.commands;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.command.Command;

public class RotateArm extends Command {

    private double speed;

    public RotateArm(double speed) {
        requires(Robot.arm);
        this.speed = speed;
    }

    protected void initialize()
    {
        this.setInterruptible(true);
    }

    protected void execute()
    {
        Robot.arm.moveArm(speed);
    }

    protected boolean isFinished() { return (speed > 0) ? !Robot.oi.getJoy().getRawButton(Config.BUTTON_ARM_UP) : (speed < 0) && !Robot.oi.getJoy().getRawButton(Config.BUTTON_ARM_DOWN); }

    protected void end()
    {
        Robot.arm.stop();
    }
}