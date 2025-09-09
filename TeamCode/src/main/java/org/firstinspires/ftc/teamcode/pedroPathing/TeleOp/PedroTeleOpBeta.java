package org.firstinspires.ftc.teamcode.pedroPathing.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.rowanmcalpin.nextftc.core.command.CommandManager;
import com.rowanmcalpin.nextftc.ftc.NextFTCOpMode;
import com.rowanmcalpin.nextftc.pedro.DriverControlled;

import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.HorizSlide;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.VertSlide;

@TeleOp(name = "Beta TeleOp")
public class PedroTeleOpBeta extends NextFTCOpMode {
    public PedroTeleOpBeta() {
        super(HorizSlide.INSTANCE, VertSlide.INSTANCE);
    }

    @Override
    public void onInit() {
    }

    @Override
    public void onStartButtonPressed() {
        CommandManager.INSTANCE.scheduleCommand(new DriverControlled(gamepadManager.getGamepad1(), true));

        gamepadManager.getGamepad2().getDpadUp().setPressedCommand(HorizSlide.INSTANCE::extendIntake);
    }
}
