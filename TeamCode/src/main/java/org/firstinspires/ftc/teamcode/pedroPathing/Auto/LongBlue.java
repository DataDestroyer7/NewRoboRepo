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

@Autonomous(name = "Long Blue Autonomous")
public class LongBlue extends NextFTCOpMode {
    public LongBlue() {
        addComponents(
                new SubsystemComponent(FlyWheels.INSTANCE),
                BulkReadComponent.INSTANCE,
                new PedroComponent(Constants::createFollower)
        );
    }

    public PathChain Path1;
    public PathChain Path2;

    public void Paths() {
        Path1 = follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(60.000, 132.000), new Pose(72.000, 71.000))
                )
                .setLinearHeadingInterpolation(Math.toRadians(270), Math.toRadians(135))
                .build();

        Path2 = follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(72.000, 71.000), new Pose(67.000, 45.000))
                )
                .setLinearHeadingInterpolation(Math.toRadians(135), Math.toRadians(135))
                .build();
    }

    private Command autonomousRoutine() {
        return new SequentialGroup(
                new FollowPath(Path1),
                new Delay(1),
                //[Fire balls in sequence here]
                new FollowPath(Path2)
        );
    }

    @Override
    public void onStartButtonPressed() {
        autonomousRoutine().schedule();
    }
}