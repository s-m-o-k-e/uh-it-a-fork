package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;

@Config
public class TeleOpConfig {
    public static double PRECISION_POWER_MULTIPLIER = 0.5; //Multiplier for motor power (for precision mode)
    public static double PRECISION_TURN_MULTIPLIER = 0.5; // Multiplier for turning speed (for precision mode)


    public static double CLAW_OPEN_DEGREES = 180;
    public static double CLAW_CLOSE_DEGREES = 0;

    public static double TRACKWIDTH = 14;
    public static double TICKS_TO_INCHES = 1;

    public static double WHEEL_RADIUS = 1.889764; //in
}
