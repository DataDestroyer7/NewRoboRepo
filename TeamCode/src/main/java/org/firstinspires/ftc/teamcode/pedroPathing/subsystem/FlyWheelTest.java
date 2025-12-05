package org.firstinspires.ftc.teamcode.pedroPathing.subsystem;


import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.control.KineticState;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.utility.InstantCommand;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.controllable.MotorGroup;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.impl.ServoEx;
import dev.nextftc.hardware.positionable.SetPosition;
import dev.nextftc.hardware.powerable.SetPower;

public class FlyWheelTest implements Subsystem {
    public static final FlyWheelTest INSTANCE = new FlyWheelTest();
    private FlyWheelTest() {}


    public double targetVelocity = 0;

    TelemetryManager panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();;


    public boolean isFlyWheelOn = false;

    private MotorEx leftFlyWheel = new MotorEx("left_fly").reversed().brakeMode();


    //When battery is less than 13 volts
    public Command moveFlyWheelsFast = new SetPower(leftFlyWheel, 0.8);

    public Command stopFlyWheels = new SetPower(leftFlyWheel, 0);

    public ControlSystem controlSystem = ControlSystem.builder()
            .velPid(0.01, 0, 0)
            .build();




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
        leftFlyWheel.setPower(
                controlSystem.calculate(
                        new KineticState(0, targetVelocity)
                )


        );
        panelsTelemetry.addData("ACTUAL SPEED OF FLYWHEEL: ",leftFlyWheel.getVelocity());
        panelsTelemetry.addData("TARGET SPEED OF FLYWHEEL: ", targetVelocity);
        panelsTelemetry.update();
    }

}

