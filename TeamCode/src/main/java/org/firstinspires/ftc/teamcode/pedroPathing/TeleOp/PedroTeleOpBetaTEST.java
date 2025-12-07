package org.firstinspires.ftc.teamcode.pedroPathing.TeleOp;

import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.FlyWheelTest;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.intakeSys;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import dev.nextftc.hardware.driving.FieldCentric;
import dev.nextftc.hardware.driving.MecanumDriverControlled;
import dev.nextftc.hardware.impl.Direction;
import dev.nextftc.hardware.impl.IMUEx;
import dev.nextftc.hardware.impl.MotorEx;

@TeleOp(name = "Beta TeleOp Test")
public class PedroTeleOpBetaTEST extends NextFTCOpMode {
    public PedroTeleOpBetaTEST() {
        addComponents(
                new SubsystemComponent(FlyWheelTest.INSTANCE, intakeSys.INSTANCE),
                        BulkReadComponent.INSTANCE,
                        BindingsComponent.INSTANCE
        );
    }

    TelemetryManager panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();

    private final MotorEx frontLeftMotor = new MotorEx("frontLeftMotor").reversed();
    private final MotorEx frontRightMotor = new MotorEx("frontRightMotor");
    private final MotorEx backLeftMotor = new MotorEx("backLeftMotor").reversed();
    private final MotorEx backRightMotor = new MotorEx("backRightMotor");
    private IMUEx imu = new IMUEx("imu", Direction.UP, Direction.BACKWARD).zeroed();


    @Override
    public void onInit() {
    }

    @Override
    public void onStartButtonPressed() {
        Command driverControlled = new MecanumDriverControlled(
                frontLeftMotor,
                frontRightMotor,
                backLeftMotor,
                backRightMotor,
                Gamepads.gamepad1().leftStickY().negate(),
                Gamepads.gamepad1().leftStickX(),
                Gamepads.gamepad1().rightStickX(),
                new FieldCentric(imu)
        );
        driverControlled.schedule();

        //Old robot Code
        //Gamepads.gamepad1().x()
                //.toggleOnBecomesTrue()
                //.whenBecomesTrue(FlyWheels.INSTANCE.moveServo)
                //.whenBecomesFalse(FlyWheels.INSTANCE.returnServo);

        //fast and slow
        Gamepads.gamepad1().y()
                .toggleOnBecomesTrue()
                .whenBecomesTrue(FlyWheelTest.INSTANCE.flyWheelFast)
                .whenBecomesFalse(FlyWheelTest.INSTANCE.flyWheelStop);

        Gamepads.gamepad1().b()
                .toggleOnBecomesTrue()
                .whenBecomesTrue(FlyWheelTest.INSTANCE.flyWheelSlow)
                .whenBecomesFalse(FlyWheelTest.INSTANCE.flyWheelStop);

        Gamepads.gamepad1().x()
                .toggleOnBecomesTrue()
                .whenBecomesTrue(intakeSys.INSTANCE.intakeStart)
                .whenBecomesFalse(intakeSys.INSTANCE.intakeStop);



        // Old robot code
        //Gamepads.gamepad1().y()
                //Extends and retracts servo on button press with a fixed delay
                //.whenBecomesTrue(FlyWheels.INSTANCE.returnServo
                        //.then(new Delay(1))
                        //.then(FlyWheels.INSTANCE.moveServo));



        //Turns off flywheel and starts the intake system




        //panelsTelemetry.addData("SPEED OF FLYWHEEL: ", FlyWheels.getPower());
        //panelsTelemetry.addData("SPEED OF FLYWHEEL: ", FlyWheels.getPower());
        //panelsTelemetry.update();


    }
}
