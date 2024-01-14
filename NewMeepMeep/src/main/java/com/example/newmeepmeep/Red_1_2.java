package com.example.newmeepmeep;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class Red_1_2 {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        Pose2d centerScoringPose = new Pose2d(45, -35.3, Math.toRadians(0));

        //rotations for each randomization
        Pose2d middleRandomization = new Pose2d(-35, 34, Math.toRadians(270));
        Pose2d rightRandomization = new Pose2d(-35, 35, Math.toRadians(180));
        Pose2d leftRandomization = new Pose2d(-35, 35, Math.toRadians(0));

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 18)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-35.00, 63.00, Math.toRadians(270)))
                                // Run vision
                                .lineToLinearHeading(middleRandomization)
                                .splineToLinearHeading(rightRandomization, Math.toRadians(90))
                                .lineToConstantHeading(new Vector2d(-35, 60))
                                .lineToConstantHeading(new Vector2d(50, 60))
                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
