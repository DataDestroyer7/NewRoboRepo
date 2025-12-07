package org.firstinspires.ftc.teamcode.pedroPathing.subsystem;


import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.control.KineticState;
import dev.nextftc.control.feedback.PIDCoefficients;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.groups.ParallelGroup;
import dev.nextftc.core.commands.groups.SequentialGroup;
import dev.nextftc.core.commands.utility.InstantCommand;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.controllable.MotorGroup;
import dev.nextftc.hardware.controllable.RunToVelocity;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.impl.ServoEx;
import dev.nextftc.hardware.positionable.SetPosition;
import dev.nextftc.hardware.powerable.SetPower;
public class FlyWheels implements Subsystem {
    public static final FlyWheels INSTANCE = new FlyWheels();
    private FlyWheels() {}


    public  double kP = 0.001;
    public  double kI = 0.0;
    public  double kD = 0.0;
    public  double kV = 0.00035;
    public  double kA = 0.0;
    public  double kS = 0.0;

    TelemetryManager panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();;


    private MotorEx leftFlyWheel = new MotorEx("left_fly").brakeMode();
    private MotorEx rightFlyWheel = new MotorEx("right_fly").brakeMode();

    public ControlSystem leftControlSystem = ControlSystem.builder()
            .velPid(kP, kI, kD)
            .basicFF(kV,kA,kS)
            .build();
    public ControlSystem rightControlSystem = ControlSystem.builder()
            .velPid(kP, kI, kD)
            .basicFF(0.00036,kA,kS)
            .build();

    public Command flyWheelStop = new ParallelGroup(
            new RunToVelocity(leftControlSystem, 0),
            new RunToVelocity(rightControlSystem, 0)
    );
    public Command flyWheelFast = new ParallelGroup(
            new RunToVelocity(leftControlSystem, -1500),
            new RunToVelocity(rightControlSystem, 1500)
    );
    public Command flyWheelSlow = new ParallelGroup(
            new RunToVelocity(leftControlSystem, -1000),
            new RunToVelocity(rightControlSystem, 1000)
    );

    @Override
    public void periodic(){
        leftFlyWheel.setPower(
                leftControlSystem.calculate(
                        leftFlyWheel.getState()
                )


        );
        rightFlyWheel.setPower(
                rightControlSystem.calculate(
                        rightFlyWheel.getState()
                )


        );
    }

}

