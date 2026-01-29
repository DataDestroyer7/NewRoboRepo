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
import dev.nextftc.core.commands.groups.ParallelDeadlineGroup;
import dev.nextftc.core.commands.groups.ParallelGroup;
import dev.nextftc.core.commands.groups.SequentialGroup;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.extensions.pedro.FollowPath;
import dev.nextftc.extensions.pedro.PedroComponent;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;

@Autonomous(name = "Short Blue 2 Auto")
public class ShortBlue2 extends NextFTCOpMode {
    public ShortBlue2() {
        addComponents(
                new SubsystemComponent(FlyWheels.INSTANCE, intakeSys.INSTANCE),
                BulkReadComponent.INSTANCE,
                new PedroComponent(Constants::createFollower)
        );
    }

    public PathChain Path1;
    public PathChain Path2;

    public void Paths() {
        // THIS IS SHORT BLUE BY CHANDLER
        Path1 = PedroComponent.follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(0, 0), new Pose(58, 0))
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .build();
        // THIS IS LONG BLUE BY JOSIAH AND FACING WRONG DIRECTION
        Path2 = PedroComponent.follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(58, 0), new Pose(58, -16))
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .build();
    }

    private Command autonomousRoutine() {
        return new SequentialGroup(
                new FollowPath(Path1),
                new ParallelDeadlineGroup(
                        new Delay(4),
                        FlyWheels.INSTANCE.flyWheelSlow,
                        new SequentialGroup(
                                new Delay(0.5),
                                intakeSys.INSTANCE.intakeStart
                        )
                ),
                new ParallelDeadlineGroup(
                        new Delay(2),
                        FlyWheels.INSTANCE.flyWheelStop,
                        intakeSys.INSTANCE.intakeStop
                ),
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