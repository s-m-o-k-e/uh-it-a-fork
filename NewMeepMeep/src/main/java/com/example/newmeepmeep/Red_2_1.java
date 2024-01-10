package com.example.newmeepmeep;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.util.Vector;

/**
 * @author Anna Lynch
 */


// Path for center radomization
public class Red_2_1 {
    public enum Randomizations {
        LEFT,
        CENTER,
        RIGHT;
    }

    public enum Routes  {
        OUTSIDE,
        INSIDE,
    }


    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        Randomizations randomizationValue = Randomizations.LEFT;
        Routes routeValue = Routes.OUTSIDE;

        Pose2d beforeTapeLinePose;
        Pose2d tapeLinePose;
        Pose2d scoringPose;

        Vector2d waystation;
        Vector2d corner;

        switch (randomizationValue) {
            case LEFT:
                beforeTapeLinePose = new Pose2d(14.5, -35.5, Math.toRadians(45));
                tapeLinePose = new Pose2d(11, -29.5, Math.toRadians(0));
                scoringPose = new Pose2d(45, -29.5, Math.toRadians(0));
                break;
            case CENTER:
                tapeLinePose = new Pose2d(9, -34, Math.toRadians(270));
                beforeTapeLinePose = new Pose2d(11.5, -49.5, Math.toRadians(180));
                scoringPose = new Pose2d(45, -35.5, Math.toRadians(0));
                break;
            case RIGHT:
                beforeTapeLinePose = new Pose2d(10, -37, Math.toRadians(135));
                tapeLinePose =  new Pose2d(11.5, -32, Math.toRadians(180));
                scoringPose = new Pose2d(45, -41.3, Math.toRadians(0));
                break;
            default:
                tapeLinePose = new Pose2d(10, -38, Math.toRadians(180));
                beforeTapeLinePose = new Pose2d(11.5, -32, Math.toRadians(180));
                scoringPose = new Pose2d(45, -41.3, Math.toRadians(0));
                break;
        }

        if (routeValue == Routes.OUTSIDE) {
            waystation = new Vector2d(tapeLinePose.getX(), -57.0);
            corner = new Vector2d(45.0, -57.0);
        } else {
            waystation = new Vector2d(tapeLinePose.getX(), -35);
            corner = new Vector2d(45, -35);
        }

        Pose2d startingPose = new Pose2d(12, -63, Math.toRadians(90));
        Pose2d finalBeforeTapeLinePose = beforeTapeLinePose;
        Pose2d finalTapeLinePose = tapeLinePose;
        Pose2d finalScoringPose = scoringPose;

        Vector2d finalWaystation = waystation;
        Vector2d finalCorner = corner;

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 18)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startingPose)
                                // Run vision
                                .lineToLinearHeading(finalBeforeTapeLinePose)
                                .lineToLinearHeading(finalTapeLinePose)
                                // Vomit pixel
                                .lineToConstantHeading(finalWaystation)
                                .lineToConstantHeading(finalCorner)
                                .lineToLinearHeading(finalScoringPose)
                                // Extend claw in hopes of scoring
                                // Extend by running in loop between scoring location and pixel stack
                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}