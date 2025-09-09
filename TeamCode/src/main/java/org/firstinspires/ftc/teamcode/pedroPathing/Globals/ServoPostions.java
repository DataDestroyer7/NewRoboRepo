package org.firstinspires.ftc.teamcode.pedroPathing.Globals;

public class ServoPostions {
    public static final double LINKAGE_POSITION_INIT = 0.0;
    public static final double LINKAGE_POSITION_EXTEND = 0.5;
    public static final double LINKAGE_POSITION_RETRACT = 0.13;

    //0.89 for 180 degrees servo 0.534 (160.2 degrees)
    public static final double INTAKE_ARM_POSITION_EXTEND = 0.57;
    public static final double INTAKE_ARM_POSITION_RETRACT = 0.0;

    public static final double INTAKE_WRIST_POSITION_EXTEND = 0.88;
    public static final double INTAKE_WRIST_POSITION_RETRACT = 0.505;

    public static final double OUTTAKE_ARM_POSITION_EXTEND = 0.05;
    public static final double OUTTAKE_ARM_POSITION_RETRACT = 0.46;

    //Old 180 (0.6)
    public static final double OUTTAKE_WRIST_POSITION_EXTEND = 0.4;
    //Old 180 (0.766)
    public static final double OUTTAKE_WRIST_POSITION_RETRACT = 0.78;

    public static final double CLAW_OPEN = 0.0;
    public static final double CLAW_CLOSE = 0.5;

    public static final int HIGH_BUCKET = 3000;
    public static final int INIT_SLIDE = 5;

    public static Location SIDE = Location.FAR;
    /**
     * Match constants.
     */
    public static Location ALLIANCE = Location.RED;
    public static boolean IS_AUTO = false;

    /**
     * Robot State Constants
     */
    public static boolean IS_SCORING = false;
    public static boolean IS_INTAKING = false;

    public static void startScoring() {
        IS_SCORING = true;
        IS_INTAKING = false;
    }

    public static void stopScoring(){
        IS_SCORING = false;
        IS_INTAKING = false;
    }

    public static void startIntaking() {
        IS_SCORING = false;
        IS_INTAKING = true;
    }

    public static void stopIntaking() {
        IS_SCORING = false;
        IS_INTAKING = false;
    }
}
