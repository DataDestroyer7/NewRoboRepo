package org.firstinspires.ftc.teamcode.pedroPathing.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.FlyWheels;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.InputSystem;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.OutputSystem;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.SlideSystem;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.extensions.pedro.PedroDriverControlled;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import dev.nextftc.hardware.driving.DriverControlledCommand;
import dev.nextftc.hardware.driving.MecanumDriverControlled;
import dev.nextftc.hardware.impl.MotorEx;

@TeleOp(name = "Beta TeleOp")
public class PedroTeleOpBeta extends NextFTCOpMode {
    public PedroTeleOpBeta() {
        addComponents(
                new SubsystemComponent(FlyWheels.INSTANCE),
                        BulkReadComponent.INSTANCE,
                        BindingsComponent.INSTANCE
        );
    }

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
        boolean isSpinning = false;
        driverControlled.schedule();

        Gamepads.gamepad2().a()
                .toggleOnBecomesTrue()
                .whenBecomesTrue(FlyWheels.INSTANCE.moveFlyWheels)
                .whenBecomesFalse(FlyWheels.INSTANCE.stopFlyWheels);
        Gamepads.gamepad2().x()
                .toggleOnBecomesTrue()
                .whenBecomesTrue(FlyWheels.INSTANCE.moveServo)
                .whenBecomesFalse(FlyWheels.INSTANCE.returnServo);

    }
}
