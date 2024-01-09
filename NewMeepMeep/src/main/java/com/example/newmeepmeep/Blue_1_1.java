package com.example.newmeepmeep;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

/**
 * @author Anna Lynch
 */


// Path for center radomization
public class Blue_1_1 {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        Pose2d centerScoringPose = new Pose2d(45, -35.3, Math.toRadians(0));

        Pose2d middleRandomization = new Pose2d(-32, -34, Math.toRadians(90));
        Pose2d rightRandomization = new Pose2d(-27, -34, Math.toRadians(90));
        Pose2d leftRandomization = new Pose2d(-37, -34, Math.toRadians(90));

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 18)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-35.00, -63.00, Math.toRadians(90)))
                                // Run vision
                                .lineToLinearHeading(middleRandomization)
                                .lineToConstantHeading(new Vector2d(-32, -37))
                                .lineToLinearHeading(new Pose2d(5.00, -37, Math.toRadians(90)))
                                .lineToLinearHeading(centerScoringPose)
                                .lineToConstantHeading(new Vector2d(-59, -35.3))
                                .lineToLinearHeading(centerScoringPose)
                                .lineToConstantHeading(new Vector2d(-59, -35.3))
                                .lineToLinearHeading(centerScoringPose)
                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}