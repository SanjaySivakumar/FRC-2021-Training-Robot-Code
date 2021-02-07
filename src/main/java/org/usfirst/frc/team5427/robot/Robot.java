/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5427.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team5427.robot.subsystems.Arm;
import org.usfirst.frc.team5427.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5427.robot.subsystems.Intake;
import org.usfirst.frc.team5427.robot.subsystems.Wrist;
import org.usfirst.frc.team5427.util.Config;

public class Robot extends TimedRobot {

    /**
     * The Operator Interface.
     */
    public static OI oi;

    /**
     * The motor controller for the top left motor of the drive train. 
     */
    private static SpeedController driveLeftTop;

    /**
     * The motor controller for the bottom left motor of the drive train. 
     */
    private static SpeedController driveLeftBottom;

    /**
     * The motor controller for the middle right motor of the drive train. 
     */
    private static SpeedController driveRightMiddle;

    /**
     * The motor controller for the bottom right motor of the drive train. 
     */
    private static SpeedController driveRightBottom;

    /**
     * The group of motor controllers for the left side of the drive train. 
     */
    private static SpeedControllerGroup driveLeft;

    /**
     * The group of motor controllers for the right side of the drive train.
     */
    private static SpeedControllerGroup driveRight;

    /**
     * The drive train subsystem. 
     */
    public static DriveTrain driveTrain;

    /**
     * The drive base. 
     */
    private static DifferentialDrive drive;

    /**
     * The speed controller for the arm motor. 
     */
    private static SpeedController armMotor;

    /**
     * The speed controller for the wrist motor. 
     */
    private static SpeedController wristMotor;

    /**
     * The speed controller for the top intake motor. 
     */
    private static SpeedController intakeTopMotor;

    /**
     * The speed controller for the bottom intake motor. 
     */
    private static SpeedController intakeBottomMotor;

    /**
     * The arm subsystem. 
     */
    public static Arm arm;

    /**
     * The wrist subsystem. 
     */
    public static Wrist wrist;

    /**
     * The intake subsystem. 
     */
    public static Intake intake;

    /**
     * The solenoid for the piston to change drive train gear ratio. 
     */
    public static Solenoid solenoidGearShifter;

    /**
     * The solenoid for the piston for the hatch collector. 
     */
    public static Solenoid solenoidHatchShifter;

    /**
     * For the safety light (connected to PCM). 
     */
    private static Solenoid solenoidLight;

    /**
     * The solenoid for the pistons for the climber. 
     */
    private static Solenoid solenoidClimb;

    //the dreaded potentiometers

    /**
     * The potentiometer for the wrist. 
     */
    public static AnalogPotentiometer wristPot;

    /**
     * The potentiometer for the arm. 
     */
    public static AnalogPotentiometer armPot;

    /**
     * The ultrasonic sensor for delivering hatches/cargo. 
     */
    private static Ultrasonic ultra;
    
    /**
     * The encoder for the climber. 
     */
    private static Encoder climb_enc;

    /**
     * The CameraServer instance. 
     */
    private static CameraServer camServer;

    /**
     * The USB Camera for the driver. 
     */
    private static UsbCamera cam1;

    /**
     * The USB Camera for the driver. 
     */
    private static UsbCamera cam2;

    private static Encoder encoder;

    public void robotInit()
    {
        //initialization of all subsystems
        driveLeftTop = new WPI_VictorSPX(Config.LEFT_TOP_MOTOR);
        driveLeftBottom = new WPI_VictorSPX(Config.LEFT_BOTTOM_MOTOR);
        driveRightMiddle = new WPI_VictorSPX(Config.RIGHT_MIDDLE_MOTOR);
        driveRightBottom = new WPI_VictorSPX(Config.RIGHT_BOTTOM_MOTOR);
        
        driveLeft = new SpeedControllerGroup(driveLeftTop,driveLeftBottom);
        driveRight = new SpeedControllerGroup(driveRightMiddle,driveRightBottom);

        drive = new DifferentialDrive(driveLeft, driveRight);
        driveTrain = new DriveTrain(driveLeft, driveRight, drive);

        armMotor = new WPI_VictorSPX(Config.ARM_MOTOR);
        arm = new Arm(armMotor);

        wristMotor = new WPI_VictorSPX(Config.WRIST_MOTOR);
        wrist = new Wrist(wristMotor);

        intakeTopMotor = new WPI_VictorSPX(Config.INTAKE_TOP_MOTOR);
        intakeBottomMotor = new WPI_VictorSPX(Config.INTAKE_BOTTOM_MOTOR);
        intake = new Intake(intakeTopMotor, intakeBottomMotor);

        //ultrasonic initialization
        ultra = new Ultrasonic(Config.ULTRA_PORT2, Config.ULTRA_PORT1);
        ultra.setAutomaticMode(true);
      
        //solenoid initialization
        solenoidGearShifter = new Solenoid(Config.PCM_ID, Config.SOLENOID_ONE_CHANNEL);
        solenoidLight = new Solenoid(Config.PCM_ID, Config.SOLENOID_LIGHT_CHANNEL);
        solenoidHatchShifter = new Solenoid(Config.PCM_ID, Config.SOLENOID_HATCH_CHANNEL);
        solenoidClimb = new Solenoid(Config.PCM_ID, Config.SOLENOID_CLIMB_CHANNEL);
        solenoidLight.set(true);

        //sensor initialization
        climb_enc = new Encoder(Config.ENCODER_CLIMB_PORT_1, Config.ENCODER_CLIMB_PORT_2, false, EncodingType.k4X);
        wristPot = new AnalogPotentiometer(Config.ROTATION_POTENTIOMETER_PORT_WRIST, 121);
        armPot = new AnalogPotentiometer(Config.ROTATION_POTENTIOMETER_PORT_ARM, 118);

        //camera setup
        camServer = CameraServer.getInstance();
        cam1 = camServer.startAutomaticCapture(0);
        cam1.setBrightness (35);
        cam1.setFPS(30);
        cam1.setResolution(100, 100);

        cam2 = camServer.startAutomaticCapture(1);
        cam2.setBrightness(40);
        cam2.setFPS(30);
        cam2.setResolution(100, 100);

        encoder = new Encoder(0, 1);

        //this should be initialized last in robotInit()
        oi = new OI();
    }

    public void robotPeriodic()
    {
        //needs to be here (scheduler handles commands during robot program). 
        Scheduler.getInstance().run();

        //shuffleboard sensor value displays
        SmartDashboard.putNumber("climb encoder", climb_enc.get());
        SmartDashboard.putNumber("arm pot wpi angle", armPot.get());
        SmartDashboard.putNumber("wrist pot wpi angle", wristPot.get());

        SmartDashboard.putNumber("Ultrasonic", ultra.getRangeInches());
        SmartDashboard.putBoolean("Distance Hatch", ultra.getRangeInches() >= 11 && ultra.getRangeInches() <= 13);
        SmartDashboard.putBoolean("Distance Cargo Loading", ultra.getRangeInches() >= 19 && ultra.getRangeInches() <= 21);
        SmartDashboard.putBoolean("Distance Ship Shoot", ultra.getRangeInches() >= 23 && ultra.getRangeInches() <= 25);
        SmartDashboard.putNumber("Encoder distance", encoder.getDistance());
    }
}
