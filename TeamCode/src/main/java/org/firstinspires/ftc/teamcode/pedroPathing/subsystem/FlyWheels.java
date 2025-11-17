package org.firstinspires.ftc.teamcode.pedroPathing.subsystem;


import static org.firstinspires.ftc.teamcode.pedroPathing.Globals.ServoPositions.*;

import com.qualcomm.robotcore.hardware.Servo;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.control.KineticState;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.groups.SequentialGroup;
import dev.nextftc.core.commands.utility.InstantCommand;
import dev.nextftc.core.commands.utility.LambdaCommand;
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

    public double targetVelocity = 0;

    // leftIn is reversed(use 1-position logic)
    private ServoEx ballLauncher = new ServoEx("ball_launcher");

    private MotorEx leftFlyWheel = new MotorEx("left_fly").reversed().brakeMode();
    private MotorEx rightFlyWheel = new MotorEx("right_fly").brakeMode();

    MotorGroup myMotorGroup = new MotorGroup(leftFlyWheel, rightFlyWheel);

    //When battery is less than 13 volts
    public Command moveFlyWheelsFast = new SetPower(myMotorGroup, 0.8);

    public Command stopFlyWheels = new SetPower(myMotorGroup, 0);

    public ControlSystem controlSystem = ControlSystem.builder()
            .velPid(0.01, 0, 0)
            .build();


    public Command moveServo = new SetPosition(ballLauncher, 0.7);
    public Command returnServo = new SetPosition(ballLauncher, 0.83);

    public Command flyWheelSlow = new InstantCommand(() -> {
       targetVelocity = 1000;
    });
    public Command flyWheelFast = new InstantCommand(() -> {
        targetVelocity = 1500;
    });
    public Command flyWheelStop = new InstantCommand(() -> {
        targetVelocity = 0;
    });

    @Override
    public void periodic(){
        myMotorGroup.setPower(
                controlSystem.calculate(
                        new KineticState(0, targetVelocity)
                )
        );
    }



}

