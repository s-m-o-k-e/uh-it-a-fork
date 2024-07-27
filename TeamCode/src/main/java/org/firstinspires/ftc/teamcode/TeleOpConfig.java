package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;

@Config
public class TeleOpConfig {
    public static double PRECISION_POWER_MULTIPLIER = 0.5; //Multiplier for motor power (for precision mode)
    public static double PRECISION_TURN_MULTIPLIER = 0.5; // Multiplier for turning speed (for precision mode)


    public static double CLAW_OPEN_DEGREES = 0.3;
    public static double CLAW_CLOSE_DEGREES = 0.8;

    public static long CLAW_TIMER = 1000;

    public static double LAUNCHER_HOLD_DEGREES = 0.3;
    public static double LAUNCHER_FIRE_DEGREES = 0;

    public static double TRACKWIDTH = 14;
    public static double TICKS_TO_INCHES = 1;

    public static double WHEEL_RADIUS = 1.889764; //in

    public static int RESTRICTED_X = 12;
    public static int RESTRICTED_Y = 12;

    public static double INTAKE_SPEED = 1.5;
}
