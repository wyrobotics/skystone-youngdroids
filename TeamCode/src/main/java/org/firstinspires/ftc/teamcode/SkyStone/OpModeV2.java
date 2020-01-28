package org.firstinspires.ftc.teamcode.SkyStone;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="OpMode2020", group = "SkyStone")
public class OpModeV2 extends LinearOpMode {


    MecanumDrive Drive = new MecanumDrive();


    @Override
    public void runOpMode() {
        Drive.init(hardwareMap);

        telemetry.addData("say", "before testmode"); telemetry.update();

        final double inCtrlLOpenPos, inCtrlLClosePos, InLSpeed, InRSpeed, inCtrlROpenPos, inCtrlRClosePos;
        final double LifterSpeed, GrabberOpenPos, GrabberClosePos, PlateGrabberDownPos;

        inCtrlLOpenPos = 0.162; inCtrlLClosePos = 0.621;
        inCtrlROpenPos = 0.134; inCtrlRClosePos = 0.675;
        InLSpeed = 10; InRSpeed = 5;
        LifterSpeed = 1;
        GrabberOpenPos = 0.426; GrabberClosePos = 0.577;
        PlateGrabberDownPos = 0.4;

        Drive.inCtrlLPos = Drive.inCtrlRPos = 1;
        Drive. GrabberPos = GrabberOpenPos;
        Drive.SetMotorPower();

        waitForStart();

        Drive.inCtrlLPos = inCtrlLClosePos; Drive.inCtrlRPos = inCtrlRClosePos;
        Drive.SetMotorPower();

        Drive.InLPower = 5; Drive.InRPower = 10;


        // Declares all the variables
        boolean aPressed = false;
        boolean yPressed = false;
        boolean xPressed = false;
        boolean bPressed = false;
        boolean dPadUp = false;
        boolean dPadDown = false;
        boolean rightBumper = false, leftBumper = false;



        while (opModeIsActive()) {

            if (gamepad1.right_bumper) {
                rightBumper = true;
                leftBumper = false;
            } else if (gamepad1.left_bumper) {
                leftBumper = true;
                rightBumper = false;
            } else {
                rightBumper = leftBumper = false;
            }
            if(gamepad1.dpad_up){
                dPadUp = true;
                dPadDown = false;
            } else if (gamepad1.dpad_down){
                dPadUp = false;
                dPadDown = true;
            } else {
                dPadUp = dPadDown = false;
            }
            if(gamepad1.a){
                aPressed = true;
                xPressed = false;
                yPressed = false;
                bPressed = false;
            } else if (gamepad1.x){
                aPressed = false;
                xPressed = true;
                yPressed = false;
                bPressed = false;
            } else if (gamepad1.y){
                aPressed = false;
                xPressed = false;
                yPressed = true;
                bPressed = false;
            } else if (gamepad1.b){
                aPressed = false;
                xPressed = false;
                yPressed = false;
                bPressed = true;
            } else {
                aPressed = xPressed = yPressed = bPressed = false;
            }

            Drive.DriveTrain(gamepad1.left_stick_x,gamepad1.left_stick_y,gamepad1.right_stick_x);

            if (rightBumper) {
                Drive.LifterPower = LifterSpeed;
            } else if (leftBumper) {
                Drive.LifterPower = LifterSpeed * -1;
            } else {
                Drive.LifterPower = 0;
            }
            if (aPressed) {
                Drive.GrabberPos = GrabberClosePos;
                Drive.inCtrlLPos = inCtrlLOpenPos; Drive.inCtrlRPos = inCtrlROpenPos;
                Drive.InRPower = Drive.InLPower = 0;
                Drive.SetMotorPower();
            }
            if (bPressed) {
                Drive.GrabberPos = GrabberOpenPos; Drive.SetMotorPower();
                Drive.GrabberPos = GrabberClosePos;
            }
            if (xPressed) {
                Drive.GrabberPos = GrabberOpenPos;
                Drive.inCtrlLPos = inCtrlLClosePos; Drive.inCtrlRPos = inCtrlRClosePos;
                Drive.SetMotorPower();
                Drive.InLPower = InLSpeed; Drive.InRPower = InRSpeed;
            }
            if (dPadUp) {
                Drive.PlateGrabRPos = Drive.PlateGrabLPos = 0;
            }
            if (dPadDown) {
                Drive.PlateGrabRPos = Drive.PlateGrabLPos = PlateGrabberDownPos;
            }

            Drive.SetMotorPower();
        }
    }
}