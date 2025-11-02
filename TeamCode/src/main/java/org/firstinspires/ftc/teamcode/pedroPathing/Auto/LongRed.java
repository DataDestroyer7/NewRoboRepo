package org.firstinspires.ftc.teamcode.pedroPathing.Auto;

import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.FlyWheels;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.delays.Delay;
import dev.nextftc.core.commands.groups.SequentialGroup;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.extensions.pedro.FollowPath;
import dev.nextftc.extensions.pedro.PedroComponent;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import static dev.nextftc.extensions.pedro.PedroComponent.follower;

@Autonomous(name = "Short Red Autonomous")
public class LongRed extends NextFTCOpMode {
    public LongRed() {
        addComponents(
                new SubsystemComponent(FlyWheels.INSTANCE),
                BulkReadComponent.INSTANCE,
                new PedroComponent(Constants::createFollower)
        );
    }

    public PathChain Path1;
    public PathChain Path2;

    public void Paths() {
        Path1 = PedroComponent.follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(0, 0), new Pose(-58, 0))
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .build();

        Path2 = PedroComponent.follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(-58, 0), new Pose(-58, -16))
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .build();
    }

    private Command autonomousRoutine() {
        return new SequentialGroup(
                new FollowPath(Path1),

                new Delay(1),
                FlyWheels.INSTANCE.moveFlyWheelsFast,
                new Delay(2),
                FlyWheels.INSTANCE.moveServo,
                new Delay(0.25),
                FlyWheels.INSTANCE.stopFlyWheels,
                new Delay(1),
                FlyWheels.INSTANCE.returnServo,

                new Delay(1),
                FlyWheels.INSTANCE.moveFlyWheelsFast,
                new Delay(2),
                FlyWheels.INSTANCE.moveServo,
                new Delay(0.25),
                FlyWheels.INSTANCE.stopFlyWheels,
                new Delay(1),
                FlyWheels.INSTANCE.returnServo,

                new Delay(1),
                FlyWheels.INSTANCE.moveFlyWheelsFast,
                new Delay(2),
                FlyWheels.INSTANCE.moveServo,
                new Delay(0.25),
                FlyWheels.INSTANCE.stopFlyWheels,
                new Delay(1),
                FlyWheels.INSTANCE.returnServo,

                new FollowPath(Path2)
        );
    }

    @Override
    public void onInit() {
        Paths();
    }

    @Override
    public void onStartButtonPressed() {
        autonomousRoutine().schedule();
    }
}