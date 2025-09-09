package org.firstinspires.ftc.teamcode.pedroPathing.subsystem;

import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.MultipleServosToSeperatePositions;
import com.rowanmcalpin.nextftc.ftc.hardware.ServoToPosition;

import static org.firstinspires.ftc.teamcode.pedroPathing.Globals.ServoPostions.*;

import java.util.Map;


public class HorizSlide extends Subsystem {
    public static final HorizSlide INSTANCE = new HorizSlide();
    private HorizSlide() {}

    public Servo leftLinkage;
    public Servo rightLinkage;
    public Servo leftIntake;
    public Servo rightIntake;
    public Servo intakeWrist;
    public Servo intakeClaw;

    public String leftL = "leftLinkage";
    public String rightL = "rightLinkage";
    public String leftI = "leftIntake";
    public String rightI = "rightIntake";
    public String intakeW = "intakeWrist";
    public String intakeC = "intakeClaw";

    public Command extendIntake() {
        return new MultipleServosToSeperatePositions(
                Map.of(
                        leftLinkage, LINKAGE_POSITION_EXTEND,
                        rightLinkage, LINKAGE_POSITION_EXTEND,
                        leftIntake, INTAKE_ARM_POSITION_EXTEND,
                        rightIntake, INTAKE_ARM_POSITION_EXTEND,
                        intakeWrist, INTAKE_WRIST_POSITION_EXTEND
                ),
                this
        );
    }
    public Command retractIntake() {
        return new MultipleServosToSeperatePositions(
                Map.of(
                        leftLinkage, LINKAGE_POSITION_RETRACT,
                        rightLinkage, LINKAGE_POSITION_RETRACT,
                        leftIntake, INTAKE_ARM_POSITION_RETRACT,
                        rightIntake, INTAKE_ARM_POSITION_RETRACT,
                        intakeWrist, INTAKE_WRIST_POSITION_RETRACT
                ),
                this
        );
    }

    public Command openIntakeClaw() {
        return new ServoToPosition(intakeClaw, CLAW_OPEN, this);
    }
    public Command closeIntakeClaw() {
        return new ServoToPosition(intakeClaw, CLAW_CLOSE, this);
    }

    @Override
    public void initialize() {
        leftLinkage = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, leftL);
        rightLinkage = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, rightL);
        leftIntake = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, leftI);
        rightIntake = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, rightI);
        intakeWrist = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, intakeW);
        intakeClaw = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, intakeC);

        leftLinkage.setDirection(Servo.Direction.REVERSE);
        leftIntake.setDirection(Servo.Direction.REVERSE);
        intakeClaw.setDirection(Servo.Direction.REVERSE);

        new ServoToPosition(leftLinkage, LINKAGE_POSITION_INIT, this);
        new ServoToPosition(rightLinkage, LINKAGE_POSITION_INIT, this);
        new ServoToPosition(intakeClaw, CLAW_OPEN, this);
        new ServoToPosition(intakeWrist, INTAKE_WRIST_POSITION_RETRACT, this);
        new ServoToPosition(leftIntake, INTAKE_ARM_POSITION_RETRACT, this);
        new ServoToPosition(rightIntake, INTAKE_ARM_POSITION_RETRACT, this);
    }
}
