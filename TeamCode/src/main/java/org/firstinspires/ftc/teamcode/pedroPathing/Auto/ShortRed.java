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

@Autonomous(name = "Long Blue Autonomous")
public class ShortRed extends NextFTCOpMode {
    public ShortRed() {
        addComponents(
                new SubsystemComponent(FlyWheels.INSTANCE, intakeSys.INSTANCE),
                BulkReadComponent.INSTANCE,
                new PedroComponent(Constants::createFollower)
        );
    }

    public PathChain Path1;
    public PathChain Path2;

    public PathChain Path3;

    public void Paths() {
        Path1 = PedroComponent.follower()
                .pathBuilder()
                .addPath(new BezierLine(new Pose(0, 0), new Pose(0, 24)))
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .build();

        Path2 = PedroComponent.follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(127.805, 127.368), new Pose(86.444, 85.131))
                )
                .setLinearHeadingInterpolation(Math.toRadians(45), Math.toRadians(180))
                .addPath(
                        new BezierLine(new Pose(86.444, 85.131), new Pose(131.745, 84.474))
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
                .addPath(
                        new BezierLine(new Pose(131.745, 84.474), new Pose(84.036, 87.538))
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(45))
                .build();

        Path3 = PedroComponent.follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(86.225, 85.131), new Pose(131.745, 84.474))
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
                .build();

    }

    private Command autonomousRoutine() {
        return new SequentialGroup(
                intakeSys.INSTANCE.intakeStart,
                new FollowPath(Path2),
                FlyWheels.INSTANCE.flyWheelFast,
                new Delay(3),
                new FollowPath(Path3)

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