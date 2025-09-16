package org.firstinspires.ftc.teamcode.pedroPathing.subsystem;

import static org.firstinspires.ftc.teamcode.pedroPathing.Globals.ServoPositions.*;

import com.qualcomm.robotcore.hardware.Servo;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.groups.SequentialGroup;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.ServoEx;
import dev.nextftc.hardware.positionable.SetPosition;
import dev.nextftc.hardware.positionable.SetPositions;

public class InputSystem implements Subsystem {
    public static final InputSystem INSTANCE = new InputSystem();
    private InputSystem() {}

    // leftIn is reversed(use 1-position logic)
    private ServoEx leftIn = new ServoEx("leftIntake");
    private ServoEx rightIn = new ServoEx("rightIntake");
    // inClaw is reversed
    private ServoEx inClaw = new ServoEx("intakeClaw");
    private ServoEx inWrist = new ServoEx("intakeWrist");

    public Command extend = new SequentialGroup(
            new SetPositions(
                    leftIn.to(1-INTAKE_ARM_POSITION_EXTEND),
                    rightIn.to(INTAKE_ARM_POSITION_EXTEND),
                    inWrist.to(INTAKE_WRIST_POSITION_RETRACT)
            )
    );
    public Command retract = new SequentialGroup(
            new SetPositions(
                    leftIn.to(1-INTAKE_ARM_POSITION_RETRACT),
                    rightIn.to(INTAKE_ARM_POSITION_RETRACT),
                    inWrist.to(INTAKE_WRIST_POSITION_RETRACT)
            ).requires(this)
    );
    public Command openClaw = new SetPosition(inClaw, 1-CLAW_OPEN).requires(this);
    public Command closeClaw = new SetPosition(inClaw, 1-CLAW_CLOSE).requires(this);
}
