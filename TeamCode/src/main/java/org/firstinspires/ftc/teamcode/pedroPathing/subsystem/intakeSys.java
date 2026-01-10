package org.firstinspires.ftc.teamcode.pedroPathing.subsystem;


import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;

import dev.nextftc.control.ControlSystem;
import dev.nextftc.control.KineticState;
import dev.nextftc.control.feedback.PIDCoefficients;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.utility.InstantCommand;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.controllable.MotorGroup;
import dev.nextftc.hardware.controllable.RunToVelocity;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.impl.ServoEx;
import dev.nextftc.hardware.positionable.SetPosition;
import dev.nextftc.hardware.powerable.SetPower;
@Configurable
public class intakeSys implements Subsystem {
    public static final intakeSys INSTANCE = new intakeSys();
    private intakeSys() {}

    public MotorEx intake = new MotorEx("intake").brakeMode();

    public Command intakeStop = new SetPower(intake, 0);
    public Command intakeStart = new SetPower(intake, 1);

    public Command intakeReverse = new SetPower(intake, -1);

}