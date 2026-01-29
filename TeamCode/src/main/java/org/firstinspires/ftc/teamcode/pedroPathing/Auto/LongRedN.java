package org.firstinspires.ftc.teamcode.pedroPathing.Auto;


import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.FlyWheels;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.intakeSys;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.groups.SequentialGroup;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.extensions.pedro.FollowPath;
import dev.nextftc.extensions.pedro.PedroComponent;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;

@Autonomous(name = "Long Right Auto")
public class LongRedN extends NextFTCOpMode {
    public LongRedN() {
        addComponents(
                new SubsystemComponent(FlyWheels.INSTANCE, intakeSys.INSTANCE),
                BulkReadComponent.INSTANCE,
                new PedroComponent(Constants::createFollower)
        );
    }

    private Follower follower;
    public PathChain Path1;
    public PathChain Path2;

    public PathChain Path3;


    public void Paths() {
        // THIS IS FOR LONG RED BY CHANDLER
        Path1 = PedroComponent.follower() //Quang remember when the follower was an argument and it didnt work so we put parentheses at the end? I think this is from that but I cant find the original example project we copied from
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(0, 0), new Pose(0, -24))
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .build();
        // THIS IS FOR SHORT BLUE BY JOSIAH AND MAYBE FIXED?
        Path2 = PedroComponent.follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(16.413, 126.930), new Pose(56.900, 84.912))
                )
                .setLinearHeadingInterpolation(Math.toRadians(315), Math.toRadians(180))
                .addPath(
                        new BezierLine(new Pose(56.900, 84.912), new Pose(13.350, 84.693))
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .addPath(
                        new BezierLine(new Pose(13.350, 84.693), new Pose(58.213, 85.131))
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(315))
                .build();

        Path3 = PedroComponent.follower()
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(58.213, 85.131), new Pose(13.568, 84.474))
                )
                .setLinearHeadingInterpolation(Math.toRadians(315), Math.toRadians(180))
                .build();
    }

    private Command autonomousRoutine() {
        return new SequentialGroup(
                //intakeSys.INSTANCE.intakeStart,
                new FollowPath(Path1)
                //FlyWheels.INSTANCE.flyWheelSlow,
                //new Delay(3),
                //new FollowPath(Path3)
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