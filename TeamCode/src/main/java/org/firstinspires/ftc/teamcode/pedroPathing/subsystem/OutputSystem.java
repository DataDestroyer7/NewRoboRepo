package org.firstinspires.ftc.teamcode.pedroPathing.subsystem;

import static org.firstinspires.ftc.teamcode.pedroPathing.Globals.ServoPositions.*;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.groups.SequentialGroup;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.ServoEx;
import dev.nextftc.hardware.positionable.SetPosition;
import dev.nextftc.hardware.positionable.SetPositions;

public class OutputSystem implements Subsystem {
    public static final OutputSystem INSTANCE = new OutputSystem();
    private OutputSystem() {}

    private ServoEx leftOut = new ServoEx("leftOutput");
    // rightOut is reversed(use 1-position logic)
    private ServoEx rightOut = new ServoEx("rightOutput");
    private ServoEx outClaw = new ServoEx("outputClaw");
    private ServoEx outWrist = new ServoEx("outputWrist");

    public Command extend = new SequentialGroup(
            new SetPositions(
                    leftOut.to(OUTTAKE_ARM_POSITION_EXTEND),
                    rightOut.to(1-OUTTAKE_ARM_POSITION_EXTEND),
                    outWrist.to(OUTTAKE_WRIST_POSITION_RETRACT)
            )
    );
    public Command retract = new SequentialGroup(
            new SetPositions(
                    leftOut.to(OUTTAKE_ARM_POSITION_RETRACT),
                    rightOut.to(1-OUTTAKE_ARM_POSITION_RETRACT),
                    outWrist.to(OUTTAKE_WRIST_POSITION_RETRACT)
            ).requires(this)
    );
    public Command openClaw = new SetPosition(outClaw, CLAW_OPEN).requires(this);
    public Command closeClaw = new SetPosition(outClaw, CLAW_CLOSE).requires(this);
}
