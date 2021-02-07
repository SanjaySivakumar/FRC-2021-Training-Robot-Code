/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package org.usfirst.frc.team5427.robot;

import org.usfirst.frc.team5427.robot.commands.*;
import org.usfirst.frc.team5427.util.Config;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;

public class OI {

	private Joystick joy1;
	private Button solenoidActivate;
	private Button solenoidHatchActivate;
	private Button intakeOut;
	private Button intakeIn;
	private Button armDown;
	private Button armUp;
	private Button wristUp;
	private Button wristDown;

	public OI() {

		joy1 = new Joystick(Config.JOYSTICK_PORT);

		intakeOut = new JoystickButton(joy1, Config.BUTTON_INTAKE_OUT);
		intakeIn = new JoystickButton(joy1, Config.BUTTON_INTAKE_IN);
		armDown = new JoystickButton(joy1, Config.BUTTON_ARM_DOWN);
		armUp = new JoystickButton(joy1, Config.BUTTON_ARM_UP);
		wristUp = new JoystickButton(joy1, Config.BUTTON_WRIST_UP);
		wristDown = new JoystickButton(joy1, Config.BUTTON_WRIST_DOWN);
		solenoidActivate = new JoystickButton(joy1, Config.PCM_JOYSTICK_PORT);
		solenoidHatchActivate = new JoystickButton(joy1,Config.BUTTON_PNEUMATIC_ACTIVATE);

		intakeOut.whileHeld(new MoveIntake(Config.INTAKE_SPEED_OUT));
		intakeIn.whileHeld(new MoveIntake(Config.INTAKE_SPEED_IN));
		armDown.whileHeld(new RotateArm(Config.ARM_SPEED_DOWN));
		armUp.whileHeld(new RotateArm(Config.ARM_SPEED_UP));
		wristDown.whenPressed(new RotateWrist(Config.WRIST_SPEED_DOWN));
		wristUp.whenPressed(new RotateWrist(Config.WRIST_SPEED_UP));
		solenoidActivate.whenPressed(new ActivateSolenoid());
		solenoidHatchActivate.whenPressed(new ActivateSolenoidHatch());
	}

	public Joystick getJoy()
	{
		return joy1;
	}
}