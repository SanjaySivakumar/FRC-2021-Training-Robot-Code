package org.usfirst.frc.team5427.robot.commands;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.command.Command;

public class ActivateSolenoid extends Command {

    private static boolean aBoolean = true;

    public ActivateSolenoid() {}

    protected void initialize()
    {
        Robot.solenoidGearShifter.set(aBoolean);
    }

    protected void end() { aBoolean = !aBoolean; }

    protected boolean isFinished()
    {
        return !Robot.oi.getJoy().getRawButtonPressed(Config.PCM_JOYSTICK_PORT);
    }
}