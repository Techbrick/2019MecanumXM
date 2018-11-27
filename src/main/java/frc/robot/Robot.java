/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import com.ctre.phoenix.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.*;
import com.kauailabs.navx.frc.AHRS;

/**
 * This is a sample program that uses mecanum drive with a gyro sensor to
 * maintian rotation vectorsin relation to the starting orientation of the robot
 * (field-oriented controls).
 */
public class Robot extends IterativeRobot {
  // gyro calibration constant, may need to be adjusted;
  // gyro value of 360 is set to correspond to one full revolution
  private static final double kVoltsPerDegreePerSecond = 0.0128;



  private SrxMecanum m_robotDrive;
  private final AHRS navX = new AHRS(SPI.Port.kMXP);
  private final Joystick driveStick = new Joystick(Constants.driveStickChannel);

  @Override
  public void robotInit() {
    TalonSRX frontLeft = new TalonSRX(Constants.leftFrontDriveChannel);
    TalonSRX rearLeft = new TalonSRX(Constants.rightFrontDriveChannel);
    TalonSRX frontRight = new TalonSRX(Constants.leftRearDriveChannel);
    TalonSRX rearRight = new TalonSRX(Constants.rightRearDriveChannel);

    // Invert the left side motors.
    // You may need to change or remove this to match your robot.
    frontLeft.setInverted(true);
    rearLeft.setInverted(true);

    m_robotDrive = new SrxMecanum(frontLeft, rearLeft, frontRight, rearRight);

    
  }

  /**
   * Mecanum drive is used with the gyro angle as an input.
   */
  @Override
  public void teleopPeriodic() {
    boolean gyroAlive = navX.isConnected();
    if  (gyroAlive){
      m_robotDrive.driveCartesian(driveStick.getX(), driveStick.getY(),
      driveStick.getZ(), navX.getAngle());
    }else {
      //  if the navX is failed then avoid passing an unknown number to the drivetrain.
      m_robotDrive.driveCartesian(driveStick.getX(), driveStick.getY(),
      driveStick.getZ());
    }
  }
}
