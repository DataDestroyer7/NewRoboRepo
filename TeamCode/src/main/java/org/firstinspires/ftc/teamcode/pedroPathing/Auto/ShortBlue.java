package org.firstinspires.ftc.teamcode.pedroPathing.Auto;

import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.FlyWheels;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.groups.SequentialGroup;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.extensions.pedro.FollowPath;
import dev.nextftc.extensions.pedro.PedroComponent;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import static dev.nextftc.extensions.pedro.PedroComponent.follower;

@Autonomous(name = "NextFTC Autonomous Program Java")
public class ShortBlue extends NextFTCOpMode {
    public ShortBlue() {
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
                        new BezierLine(new Pose(84.000, 9.000), new Pose(108.000, 9.000))
                )
                .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(90))
                .build();
    }

    private Command autonomousRoutine() {
        return new SequentialGroup(
                new FollowPath(Path1)
        );
    }

    @Override
    public void onStartButtonPressed() {
        autonomousRoutine().schedule();
    }
}