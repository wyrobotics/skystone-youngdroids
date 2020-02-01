package org.firstinspires.ftc.teamcode.SkyStone;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.internal.system.Deadline;

import java.util.concurrent.TimeUnit;

@TeleOp(name="OpMode 2020", group = "SkyStone")
public class OpModeV3 extends LinearOpMode {


    MecanumDrive Drive = new MecanumDrive();

    int openGrabber = 1;



    @Override
    public void runOpMode() {

        Drive.init(hardwareMap);

        telemetry.addData("say", "before testmode"); telemetry.update();

        final double inCtrlLOpenPos, inCtrlLClosePos, InLSpeed, InRSpeed, inCtrlROpenPos, inCtrlRClosePos;
        final double LifterSpeed, GrabberOpenPos, GrabberClosePos, PlateGrabberDownPos;
        final int maxLifterHeight, minLifterHeight;



        inCtrlLOpenPos = 0.169; inCtrlLClosePos = 0.590;
        inCtrlROpenPos = 0.0; inCtrlRClosePos = 0.470;
        InLSpeed = 10; InRSpeed = 10;
        LifterSpeed = 1;
        GrabberOpenPos = 0.819; GrabberClosePos = 1.0;
        PlateGrabberDownPos = 0.4;
        maxLifterHeight = 3150; minLifterHeight = 0;

        Drive.inCtrlLPos = inCtrlLOpenPos;
        Drive.inCtrlRPos = inCtrlROpenPos;
        Drive.GrabberPos = GrabberClosePos;
        Drive.SetMotorPower();
        while (Drive.tSensor.getState()) {
            Drive.LifterPower = LifterSpeed * -1;
            Drive.SetMotorPower();
        }
        try {
            sleep(650);
        } catch (Exception e){
            e.getStackTrace();
        }
        Drive.LifterPower = 0;
        Drive.inCtrlRPos = 1;
        Drive. GrabberPos = GrabberOpenPos;

        Drive.SetMotorPower();
        try {
            sleep(200);
        } catch (Exception e) {
            e.getStackTrace();
        }
        Drive.inCtrlLPos = 1;
        Drive.SetMotorPower();
        Drive.Lifter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Drive.Lifter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Drive.Lifter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);



        waitForStart();

        Deadline gamepadRateLimit = new Deadline(1000, TimeUnit.MILLISECONDS);

        Drive.inCtrlLPos = inCtrlLClosePos; Drive.inCtrlRPos = inCtrlRClosePos;
        Drive.SetMotorPower();

        Drive.InLPower = 10; Drive.InRPower = 10;


        while (opModeIsActive()) {

            //if (gamepad1.right_trigger > 0.1) {
              //  Drive.DriveTrain(5 * gamepad1.right_trigger);
            if (gamepad1.right_trigger > .1 || gamepad1.left_trigger > .1) {
                Drive.DriveTrain(gamepad1.left_stick_x,gamepad1.left_stick_y,gamepad1.right_stick_x,
                        gamepad1.right_trigger, gamepad1.left_trigger);
            } else {
                Drive.DriveTrain(gamepad1.left_stick_x,gamepad1.left_stick_y,gamepad1.right_stick_x);
            }

            if (Drive.InRPower == 0 && Drive.InLPower == 0) {
                if (gamepad1.left_bumper && (Drive.Lifter.getCurrentPosition() >= minLifterHeight)) {
                    Drive.LifterPower = LifterSpeed * -1;
                } else if (gamepad1.right_bumper && (Drive.Lifter.getCurrentPosition() <= maxLifterHeight)) {
                    Drive.LifterPower = LifterSpeed;
                } else {
                    Drive.LifterPower = 0;
                }
            }

            if (gamepad1.a) {
                Thread CloseGrabber = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Drive.GrabberPos = GrabberClosePos;
                        Drive.SetMotorPower();
                        try {
                            sleep(500);
                        } catch (Exception e) {
                            e.getStackTrace();
                        }
                        Drive.inCtrlLPos = inCtrlLOpenPos; Drive.inCtrlRPos = inCtrlROpenPos;
                        Drive.InRPower = Drive.InLPower = 0;
                        Drive.SetMotorPower();
                     }
                });
                CloseGrabber.start();
            } else if (gamepad1.b && gamepadRateLimit.hasExpired()) {
                openGrabber ++;
                if (openGrabber % 2 == 0) {
                    Drive.GrabberPos = GrabberClosePos;
                } else {
                    Drive.GrabberPos = GrabberOpenPos;
                }
                gamepadRateLimit.reset();


            } else if (gamepad1.x && !Drive.tSensor.getState()) {
                Drive.GrabberPos = GrabberOpenPos;
                Drive.inCtrlLPos = inCtrlLClosePos; Drive.inCtrlRPos = inCtrlRClosePos;
                Drive.SetMotorPower();
                Drive.InLPower = InLSpeed; Drive.InRPower = InRSpeed;
            } else if (gamepad1.y && (Drive.InRPower != 0 && Drive.InLPower != 0)) {
                Thread ejectBlock = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Drive.InLPower = InLSpeed * -.25; Drive.InRPower = InLSpeed * -.25;
                        try {
                            sleep(500);
                        } catch(Exception e) {
                            e.getStackTrace();
                        }
                        Drive.InLPower = InLSpeed; Drive.InRPower = InRSpeed;
                    }
                });
                ejectBlock.start();

            }

            if (gamepad1.dpad_up) {
                Drive.PlateGrabRPos = Drive.PlateGrabLPos = 0;
            } else if (gamepad1.dpad_down) {
                Drive.PlateGrabRPos = Drive.PlateGrabLPos = PlateGrabberDownPos;
            }

            Drive.SetMotorPower();

            telemetry.addData("Button Pressesed: ", Drive.tSensor.getState());

            telemetry.update();
        }
    }
}