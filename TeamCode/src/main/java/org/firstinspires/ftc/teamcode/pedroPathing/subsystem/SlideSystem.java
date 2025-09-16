package org.firstinspires.ftc.teamcode.pedroPathing.subsystem;

import static org.firstinspires.ftc.teamcode.pedroPathing.Globals.ServoPositions.LINKAGE_POSITION_EXTEND;
import static org.firstinspires.ftc.teamcode.pedroPathing.Globals.ServoPositions.LINKAGE_POSITION_INIT;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.groups.SequentialGroup;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.controllable.MotorGroup;
import dev.nextftc.hardware.controllable.RunToPosition;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.impl.ServoEx;
import dev.nextftc.hardware.positionable.SetPositions;

public class SlideSystem implements Subsystem {
    public static final SlideSystem INSTANCE = new SlideSystem();
    private SlideSystem() { }

    private MotorEx leftS = new MotorEx("slideLeft")
            .zeroed()
            .reversed()
            .brakeMode();
    private MotorEx rightS = new MotorEx("slideRight")
            .zeroed()
            .brakeMode();
    MotorGroup slides = new MotorGroup(leftS, rightS);
    // leftL is reversed(use 1-position logic)
    private ServoEx leftL = new ServoEx("leftLinkage");
    private ServoEx rightL = new ServoEx("rightLinkage");

    private ControlSystem controlSystem = ControlSystem.builder()
            .posPid(0.01, 0.0, 0.00003)
            .elevatorFF(0)
            .build();

    public Command liftToDefault = new RunToPosition(controlSystem, 0).requires(this);
    public Command liftToBucket = new RunToPosition(controlSystem, 3000).requires(this);

    public Command slideToInit = new SetPositions(
            leftL.to(1-LINKAGE_POSITION_INIT),
            rightL.to(LINKAGE_POSITION_INIT)
    );
    public Command slideToExtend = new SetPositions(
            leftL.to(1-LINKAGE_POSITION_EXTEND),
            rightL.to(LINKAGE_POSITION_EXTEND)
    );
    public Command slideToRetract = new SetPositions(
            leftL.to(1-LINKAGE_POSITION_EXTEND),
            rightL.to(LINKAGE_POSITION_EXTEND)
    );

    @Override
    public void periodic() {
        slides.setPower(controlSystem.calculate(slides.getState()));
    }
}
