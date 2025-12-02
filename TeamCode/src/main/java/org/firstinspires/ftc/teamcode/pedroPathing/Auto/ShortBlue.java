package org.firstinspires.ftc.teamcode.pedroPathing.Auto;


import com.pedropathing.follower.Follower;
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

@Autonomous(name = "Long Red Autonomous")
public class ShortBlue extends NextFTCOpMode {
    public ShortBlue() {
        addComponents(
                new SubsystemComponent(FlyWheels.INSTANCE),
                BulkReadComponent.INSTANCE,
                new PedroComponent(Constants::createFollower)
        );
    }

    private Follower follower;
    public PathChain Path1;
    public PathChain Path2;


    public void Paths() {
        Path1 = PedroComponent.follower() //Quang remember when the follower was an argument and it didnt work so we put parentheses at the end? I think this is from that but I cant find the original example project we copied from
                .pathBuilder()
                .addPath(
                        new BezierLine(new Pose(0, 0), new Pose(0, -24))
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .build();


    }

    private Command autonomousRoutine() {
        return new SequentialGroup(
                new FollowPath(Path1)
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