package org.usfirst.frc.team5427.robot.commands;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.command.Command;

public class MoveIntake extends Command {

    private double speed;

    public MoveIntake(double speed) {
        requires(Robot.intake);
        this.speed = speed;
    }

    protected void initialize()
    {
        this.setInterruptible(true);
    }

    protected void execute()
    {
        Robot.intake.setSpeed(speed);
    }

    protected boolean isFinished() { return (speed > 0) ? !Robot.oi.getJoy().getRawButton(Config.BUTTON_INTAKE_OUT) : (speed < 0) && !Robot.oi.getJoy().getRawButton(Config.BUTTON_INTAKE_IN); }

    protected void end()
    {
        Robot.intake.stop();
    }
}