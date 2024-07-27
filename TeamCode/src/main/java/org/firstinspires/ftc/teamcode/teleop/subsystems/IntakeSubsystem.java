package org.firstinspires.ftc.teamcode.teleop.subsystems;

import static org.firstinspires.ftc.teamcode.TeleOpConfig.INTAKE_SPEED;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * @author Jude Naramor
 */

//I copied the format of the other system without the drive and using the number of motors for intake that the engineers told me.
//I think this works. - Jude
public class IntakeSubsystem extends SubsystemBase {
    private final MotorEx intakeMotor;
    private final MotorEx rightMotorID;

    //public IntakeSubsystem(MotorEx intakeMotor) {

      //  this.intakeMotor = intakeMotor;
    //}+

    //public IntakeSubsystem(HardwareMap hw, String intakeMotor) {
      //  this.intakeMotor = new MotorEx(hw, intakeMotor);
    //}

    public IntakeSubsystem(HardwareMap hw, String intakeMotor, String rightMotorID) {
        this.intakeMotor = new MotorEx(hw, intakeMotor);
        this.rightMotorID = new MotorEx(hw, rightMotorID);


        //his.rightMotorID.setInverted(true);
        //this.intakeMotor.setInverted(true);
        //this.rightMotorID.motor.setDirection(DcMotorSimple.Direction.REVERSE);

    }


    public void startIntake() {
        intakeMotor.setVelocity(INTAKE_SPEED); //this might not be the method you want for motors but you can correct this later
        rightMotorID.setVelocity(INTAKE_SPEED);
    }

    public void stopIntake() {
        rightMotorID.setVelocity(0);
        intakeMotor.setVelocity(0);
    }

    public void reverseIntake() {
        intakeMotor.setVelocity(-INTAKE_SPEED); //I feel like being able to move backwards is useful for getting things unstuck
        rightMotorID.setVelocity(-INTAKE_SPEED);
    }



    public void move(double power) {

        intakeMotor.set(power);
        rightMotorID.set(power);
    }
}
