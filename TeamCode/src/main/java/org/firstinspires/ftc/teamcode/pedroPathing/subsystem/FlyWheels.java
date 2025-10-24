package org.firstinspires.ftc.teamcode.pedroPathing.subsystem;


import static org.firstinspires.ftc.teamcode.pedroPathing.Globals.ServoPositions.*;

import com.qualcomm.robotcore.hardware.Servo;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.groups.SequentialGroup;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.controllable.MotorGroup;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.impl.ServoEx;
import dev.nextftc.hardware.positionable.SetPosition;
import dev.nextftc.hardware.positionable.SetPositions;
import dev.nextftc.hardware.powerable.SetPower;

public class FlyWheels implements Subsystem {
    public static final FlyWheels INSTANCE = new FlyWheels();
    private FlyWheels() {}

    // leftIn is reversed(use 1-position logic)
    private ServoEx ballLauncher = new ServoEx("ball_launcher");

    private MotorEx leftFlyWheel = new MotorEx("left_fly").reversed();
    private MotorEx rightFlyWheel = new MotorEx("right_fly");
    MotorGroup myMotorGroup = new MotorGroup(leftFlyWheel, rightFlyWheel);


    public Command moveFlyWheels = new SetPower(myMotorGroup, 0.7);
    public Command stopFlyWheels = new SetPower(myMotorGroup, 0);


    public Command moveServo = new SetPosition(ballLauncher, 0.3);
    public Command returnServo = new SetPosition(ballLauncher, 0.25);
}

