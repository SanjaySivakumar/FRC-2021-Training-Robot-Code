package org.usfirst.frc.team5427.robot.subsystems;

import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Wrist extends Subsystem
{
    private SpeedController wristMotor;
    public Wrist(SpeedController wristMotor)
    {
        this.wristMotor = wristMotor;
    }
    
    public void moveWrist(double speed) { wristMotor.set(((speed > 0 && Robot.wristPot.get() >= Config.WRIST_LIMIT_TOP) || (speed < 0 && Robot.wristPot.get() <= Config.WRIST_LIMIT_BOTTOM)) ? speed : 0); }

    protected void initDefaultCommand() {}

    public void stop() { wristMotor.set(0); }
}