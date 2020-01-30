package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.SkyStone.AutonFunctions;
import org.firstinspires.ftc.teamcode.SkyStone.MecanumDrive;


@TeleOp(name = "GetEncoderVals2020", group = "SkyStone")
public class GetEncoderVals2020 extends LinearOpMode {

   MecanumDrive Drive = new MecanumDrive();

    String test = "Do nothing";


    @Override
    public void runOpMode() throws InterruptedException {

        Drive.init(hardwareMap);

        Thread ch = new Thread(new Runnable() {
            @Override
            public void run() {
                while (opModeIsActive()) {
                    gamepadHandler(opModeIsActive());
                }
            }
        });

        waitForStart();

        ch.start();

        while(opModeIsActive()){
            telemetry.addData("Current Task: ", test);
            telemetry.addData("FL, FR: ", Drive.fl.getCurrentPosition() + " , " + Drive.fr.getCurrentPosition());
            telemetry.addData("BL, BR: ", Drive.bl.getCurrentPosition() + " , " + Drive.br.getCurrentPosition());

            telemetry.update();

        }
    }

    public void gamepadHandler(boolean op) {
        if(gamepad1.dpad_up){
            test = "Move Forward";
            setPowers(1,1,1,1);

        } else if(gamepad1.dpad_down){
            test = "Move Backward";
            setPowers(-1,-1,-1,-1);

        } else if(gamepad1.dpad_left){
            test = "Strafe Left";
            setPowers(1,-1,-1,1);

        } else if(gamepad1.dpad_right){
            test = "Strafe Right";
            setPowers(-1,1,1,-1);

        } else if(gamepad1.left_bumper){
            test = "Rotate CW";
            setPowers(1,-1,1,-1);

        } else if(gamepad1.right_bumper){
            test = "Rotate CCW";
            setPowers(-1,1,-1,1);

        } else if(gamepad1.start) {
            test = "Resetting";
            reset();
        } else {
            test = "Do Nothing";
            setPowers(0,0,0,0);
        }
    }

    public void setPowers(double a, double b, double c, double d) {
        Drive.LFWheelPower = a; Drive.RFWheelPower = b;
        Drive.LBWheelPower = c; Drive.RBWheelPower = d;
        Drive.SetMotorPower();
    }
    public void reset() {
        setPowers(0,0,0,0);
        Drive.fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); Drive.br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Drive.fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); Drive.bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        Drive.fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER); Drive.br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Drive.fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER); Drive.bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

}
