package org.firstinspires.ftc.teamcode.pedroPathing.Auto;

import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.FlyWheels;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.intakeSys;

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
                new SubsystemComponent(FlyWheels.INSTANCE, intakeSys.INSTANCE),
                BulkReadComponent.INSTANCE,
                new PedroComponent(Constants::createFollower)
        );
    }

    public PathChain Path1;
    public PathChain Path2;
    public PathChain Path3;
    public PathChain Path4;

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

        Path3 = PedroComponent.follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(89.289, 7.003), new Pose(89.945, 33.921))
                )
                .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(180))
                .addPath(
                        new BezierLine(new Pose(89.945, 33.921), new Pose(128.900, 33.921))
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
                .addPath(
                        new BezierLine(new Pose(128.900, 33.921), new Pose(71.781, 71.781))
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(45))
                .build();
        Path4 = PedroComponent.follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(71.781, 71.781), new Pose(105.483, 71.125))
                )
                .setLinearHeadingInterpolation(Math.toRadians(45), Math.toRadians(0))
                .build();
    }

    private Command autonomousRoutine() {
        return new SequentialGroup(
                intakeSys.INSTANCE.intakeStart,
                new FollowPath(Path3),
                FlyWheels.INSTANCE.flyWheelFast,
                new Delay(3),
                new FollowPath(Path4)



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