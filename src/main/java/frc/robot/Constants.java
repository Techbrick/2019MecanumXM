package frc.robot;

import java.util.Properties;

public class Constants{

    public static final int leftFrontDriveChannel = 0;
    public static final int leftRearDriveChannel = 1;
    public static final int rightFrontDriveChannel = 2;
    public static final int rightRearDriveChannel = 3;
    public static final int armMotrChannel = 4;
    public static final int driveStickChannel = 0;
    public static final int opStickChannel = 1;


    public static double driveEncoderTicksPerInch = 2048;
    public static double kp_distance = .3;
    public static double kp_Turn = .3;
    public static double minDrivePower;
    public static double minTurnPower = .2;
    public static double twistReductionFactor = 2;

}