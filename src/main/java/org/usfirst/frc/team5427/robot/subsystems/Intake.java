/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5427.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {

	private SpeedController topFlywheel;

    private SpeedController bottomFlywheel;

	public Intake(SpeedController topFlywheel, SpeedController bottomFlywheel) {
		this.topFlywheel = topFlywheel;
		this.bottomFlywheel = bottomFlywheel;
	}

	public void setSpeed(double speed) {
        topFlywheel.set(-speed);
        bottomFlywheel.set(speed);
	}

	@Override
	public void initDefaultCommand() {}

	public void stop() {
		setSpeed(0);
	}

}
