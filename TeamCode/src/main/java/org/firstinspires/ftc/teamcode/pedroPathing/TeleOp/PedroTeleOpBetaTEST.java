package org.firstinspires.ftc.teamcode.pedroPathing.TeleOp;

import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.FlyWheelTest;
import org.firstinspires.ftc.teamcode.pedroPathing.subsystem.FlyWheels;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.delays.Delay;
import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
import dev.nextftc.hardware.driving.MecanumDriverControlled;
import dev.nextftc.hardware.impl.MotorEx;

@TeleOp(name = "Beta TeleOp Test")
public class PedroTeleOpBetaTEST extends NextFTCOpMode {
    public PedroTeleOpBetaTEST() {
        addComponents(
                new SubsystemComponent(FlyWheelTest.INSTANCE),
                        BulkReadComponent.INSTANCE,
                        BindingsComponent.INSTANCE
        );
    }

    TelemetryManager panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();;

    @Override
    public void onInit() {
    }
    // change the names and directions to suit your robot

    @Override
    public void onStartButtonPressed() {



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
