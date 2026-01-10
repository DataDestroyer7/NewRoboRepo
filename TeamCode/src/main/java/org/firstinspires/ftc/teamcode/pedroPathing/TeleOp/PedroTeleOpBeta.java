package org.firstinspires.ftc.teamcode.pedroPathing.TeleOp;

import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.FlyWheelTest;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.FlyWheels;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.intakeSys;

import dev.nextftc.core.commands.Command;
import com.bylazar.telemetry.PanelsTelemetry;
import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.extensions.pedro.PedroComponent;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import dev.nextftc.hardware.driving.MecanumDriverControlled;
import dev.nextftc.hardware.impl.Direction;
import dev.nextftc.hardware.impl.MotorEx;

@TeleOp(name = "Beta TeleOp")
public class PedroTeleOpBeta extends NextFTCOpMode {
    public PedroTeleOpBeta() {
        addComponents(
                new SubsystemComponent(FlyWheels.INSTANCE, intakeSys.INSTANCE),
                BulkReadComponent.INSTANCE,
                BindingsComponent.INSTANCE
        );
    }

    //TelemetryManager panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();;

    @Override
    public void onInit() {
    }
    // change the names and directions to suit your robot
    private final MotorEx frontLeftMotor = new MotorEx("frontLeftMotor").reversed();
    private final MotorEx frontRightMotor = new MotorEx("frontRightMotor");
    private final MotorEx backLeftMotor = new MotorEx("backLeftMotor").reversed();
    private final MotorEx backRightMotor = new MotorEx("backRightMotor");

    @Override
    public void onStartButtonPressed() {

        Command driverControlled = new MecanumDriverControlled(
                frontLeftMotor,
                frontRightMotor,
                backLeftMotor,
                backRightMotor,
                Gamepads.gamepad1().leftStickY().negate(),
                Gamepads.gamepad1().leftStickX(),
                Gamepads.gamepad1().rightStickX()
        );
        driverControlled.schedule();


        Gamepads.gamepad1().y()
                .toggleOnBecomesTrue()
                .whenBecomesTrue(FlyWheels.INSTANCE.flyWheelFast)
                .whenBecomesFalse(FlyWheels.INSTANCE.flyWheelStop);

        Gamepads.gamepad1().b()
                .toggleOnBecomesTrue()
                .whenBecomesTrue(FlyWheels.INSTANCE.flyWheelSlow)
                .whenBecomesFalse(FlyWheels.INSTANCE.flyWheelStop);

        Gamepads.gamepad1().rightTrigger().greaterThan(0.2)
                .whenBecomesTrue(intakeSys.INSTANCE.intakeStart)
                .whenBecomesFalse(intakeSys.INSTANCE.intakeStop);

        Gamepads.gamepad1().leftTrigger().greaterThan(0.2)
                .whenBecomesTrue(intakeSys.INSTANCE.intakeReverse)
                .whenBecomesFalse(intakeSys.INSTANCE.intakeStop);


        /*
        //Old robot Code
        //Gamepads.gamepad1().x()
                //.toggleOnBecomesTrue()
                //.whenBecomesTrue(FlyWheels.INSTANCE.moveServo)
                //.whenBecomesFalse(FlyWheels.INSTANCE.returnServo);

        // starts firing the balls
        Gamepads.gamepad1().x()
                .whenBecomesTrue(FlyWheels.INSTANCE.flyWheelFast
                        .then(intakeSys.INSTANCE.intakeStart));

        //fast and slow
        Gamepads.gamepad1().b()
                .toggleOnBecomesTrue()
                .whenBecomesTrue(FlyWheels.INSTANCE.flyWheelFast)
                .whenBecomesFalse(FlyWheels.INSTANCE.flyWheelSlow);

        // Old robot code
        //Gamepads.gamepad1().y()
                //Extends and retracts servo on button press with a fixed delay
                //.whenBecomesTrue(FlyWheels.INSTANCE.returnServo
                        //.then(new Delay(1))
                        //.then(FlyWheels.INSTANCE.moveServo));

        //Turns off flywheel and starts the intake system
        Gamepads.gamepad1().y()
                        .whenBecomesTrue(FlyWheels.INSTANCE.flyWheelStop
                                .then(intakeSys.INSTANCE.intakeStart));

        // turns on and off the intake system
        Gamepads.gamepad1().a()
                .toggleOnBecomesTrue()
                .whenBecomesTrue(intakeSys.INSTANCE.intakeStart)
                .whenBecomesFalse(intakeSys.INSTANCE.intakeStop);

        Gamepads.gamepad1().dpadUp()
                .whenBecomesTrue(FlyWheels.INSTANCE.moveFlyWheelsFast
                        .then(new Delay(2))
                        .then(FlyWheels.INSTANCE.moveServo)
                        .then(new Delay(0.25))
                        .then(FlyWheels.INSTANCE.stopFlyWheels)
                        .then(new Delay(1))
                        .then(FlyWheels.INSTANCE.returnServo)

                        .then(new Delay(1))
                        .then(FlyWheels.INSTANCE.moveFlyWheelsFast)
                        .then(new Delay(2))
                        .then(FlyWheels.INSTANCE.moveServo)
                        .then(new Delay(0.25))
                        .then(FlyWheels.INSTANCE.stopFlyWheels)
                        .then(new Delay(1))
                        .then(FlyWheels.INSTANCE.returnServo)

                        .then(new Delay(1))
                        .then(FlyWheels.INSTANCE.moveFlyWheelsFast)
                        .then(new Delay(2))
                        .then(FlyWheels.INSTANCE.moveServo)
                        .then(new Delay(0.25))
                        .then(FlyWheels.INSTANCE.stopFlyWheels)
                        .then(new Delay(1))
                        .then(FlyWheels.INSTANCE.returnServo));


        //panelsTelemetry.addData("SPEED OF FLYWHEEL: ", FlyWheels.getPower());
        //panelsTelemetry.addData("SPEED OF FLYWHEEL: ", FlyWheels.getPower());
        //panelsTelemetry.update();*/


    }
}
