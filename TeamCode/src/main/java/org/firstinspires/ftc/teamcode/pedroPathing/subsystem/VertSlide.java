package org.firstinspires.ftc.teamcode.pedroPathing.subsystem;



import static org.firstinspires.ftc.teamcode.pedroPathing.Globals.ServoPostions.*;
import java.util.Map;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.control.controllers.PIDFController;
import com.rowanmcalpin.nextftc.core.control.controllers.feedforward.StaticFeedforward;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.ServoToPosition;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;

public class VertSlide extends Subsystem{
    public static final VertSlide INSTANCE = new VertSlide();
    private VertSlide() {}

    public MotorEx leftSlide;
    public MotorEx rightSlide;
    public Servo outputClaw;
    public Servo outputWrist;
    public Servo leftOutput;
    public Servo rightOutput;
    public PIDFController controller = new PIDFController(0.01, 0.0, 0.00003, new StaticFeedforward(0.0));

    public String leftS = "slideLeft";
    public String rightS = "slideRight";
    public String outputC = "outputClaw";
    public String outputW = "outputWrist";
    public String leftO = "leftOutput";
    public String rightO = "rightOutput";

    @Override
    public void initialize() {
        leftSlide = new MotorEx(leftS);
        rightSlide = new MotorEx(rightS);
        outputClaw = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, outputC);
        outputWrist = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, outputW);
        leftOutput = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, leftO);
        rightOutput = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, rightO);

        leftSlide.setDirection(DcMotorSimple.Direction.REVERSE);
        rightOutput.setDirection(Servo.Direction.REVERSE);

        new ServoToPosition(outputClaw, LINKAGE_POSITION_INIT, this);
    }
}
