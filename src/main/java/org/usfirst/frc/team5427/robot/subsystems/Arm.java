package org.usfirst.frc.team5427.robot.subsystems;


import org.usfirst.frc.team5427.robot.Robot;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Arm extends Subsystem {
    
    private SpeedController armMotor;

    public Arm(SpeedController armMotor)
    {
        this.armMotor = armMotor;
    }

    public void moveArm(double armSpeed) { armMotor.set(((armSpeed > 0 && Robot.armPot.get() >= Config.ARM_LIMIT_TOP) || (armSpeed < 0 && Robot.armPot.get() <= Config.ARM_LIMIT_BOTTOM)) ? armSpeed : 0); }

    public void stop(){
        armMotor.set(0);
    }

    protected void initDefaultCommand() {}
}