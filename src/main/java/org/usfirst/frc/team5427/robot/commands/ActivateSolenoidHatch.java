package org.usfirst.frc.team5427.robot.commands;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.command.Command;

public class ActivateSolenoidHatch extends Command {

    private static boolean aBoolean = true;

    public ActivateSolenoidHatch() {}

    protected void initialize()
    {
        Robot.solenoidHatchShifter.set(aBoolean);
    }

    protected void execute()
    {
        Robot.solenoidHatchShifter.set(aBoolean);
    }

    protected void end()
    {
        aBoolean = !aBoolean;
    }

    protected boolean isFinished()
    {
        return !Robot.oi.getJoy().getRawButtonPressed(Config.PCM_JOYSTICK_PORT);
    }
}