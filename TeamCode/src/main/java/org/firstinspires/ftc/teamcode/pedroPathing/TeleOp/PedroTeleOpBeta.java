package org.firstinspires.ftc.teamcode.pedroPathing.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.InputSystem;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.OutputSystem;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.SlideSystem;

import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.extensions.pedro.PedroDriverControlled;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import dev.nextftc.hardware.driving.DriverControlledCommand;

@TeleOp(name = "Beta TeleOp")
public class PedroTeleOpBeta extends NextFTCOpMode {
    public PedroTeleOpBeta() {
        addComponents(
                new SubsystemComponent(InputSystem.INSTANCE, OutputSystem.INSTANCE, SlideSystem.INSTANCE),
                        BulkReadComponent.INSTANCE,
                        BindingsComponent.INSTANCE
        );
    }

    @Override
    public void onInit() {
    }

    @Override
    public void onStartButtonPressed() {
        DriverControlledCommand driverControlled = new PedroDriverControlled(
                Gamepads.gamepad1().leftStickY(),
                Gamepads.gamepad1().leftStickX(),
                Gamepads.gamepad1().rightStickX()
        );
        driverControlled.schedule();


    }
}
