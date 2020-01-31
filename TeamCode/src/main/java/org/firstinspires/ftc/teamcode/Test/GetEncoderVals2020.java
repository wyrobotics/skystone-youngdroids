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



        waitForStart();

        while(opModeIsActive()){
            gamepadHandler(opModeIsActive());
            telemetry.addData("Current Task: ", test);
            telemetry.addData("FL, FR: ", Drive.fl.getCurrentPosition() + " , " + Drive.fr.getCurrentPosition());
            telemetry.addData("BL, BR: ", Drive.bl.getCurrentPosition() + " , " + Drive.br.getCurrentPosition());
            telemetry.addData("", " ");
            telemetry.addData("Lifter Position: ", Drive.Lifter.getCurrentPosition());
            telemetry.addData("","");
            telemetry.addData("Average Front: ", (Drive.fl.getCurrentPosition() + Drive.fr.getCurrentPosition()) / 2);
            telemetry.addData("Average Back: ", (Drive.bl.getCurrentPosition() + Drive.br.getCurrentPosition()) / 2);
            telemetry.addData("Average Left: ", (Drive.fl.getCurrentPosition() + Drive.bl.getCurrentPosition()) / 2);
            telemetry.addData("Average Right: ", (Drive.fr.getCurrentPosition() + Drive.br.getCurrentPosition()) / 2);
            telemetry.update();


        }
    }

    public void gamepadHandler(boolean op) {
        if (!opModeIsActive()) {
            return;
        }
        if(gamepad1.dpad_up){
            test = "Move Forward";
            while ((Drive.fr.getCurrentPosition() + Drive.br.getCurrentPosition() +
                    Drive.fl.getCurrentPosition() + Drive.bl.getCurrentPosition()) / 4 <= 2100) {
                setPowers(1,1,1,1);
            }

        } else if(gamepad1.dpad_down){
            test = "Move Backward";
            while ((Drive.fr.getCurrentPosition() + Drive.br.getCurrentPosition() +
                    Drive.fl.getCurrentPosition() + Drive.bl.getCurrentPosition()) / 4 >= -2100) {
                setPowers(-1,-1,-1,-1);
            }

        } else if(gamepad1.dpad_right){
            test = "Strafe Right";
            while ((Drive.fr.getCurrentPosition() + Drive.bl.getCurrentPosition()) / 2 >= -3000) {
                setPowers(1,-1,-1,1);
            }
        } else if(gamepad1.dpad_left){
            test = "Strafe Left";
            while ((Drive.fr.getCurrentPosition() + Drive.bl.getCurrentPosition()) / 2 <= 3000) {
                setPowers(-1,1,1,-1);
            }

        } else if(gamepad1.right_bumper){
            test = "Rotate CW";
            while ((Drive.fr.getCurrentPosition() + Drive.br.getCurrentPosition()) / 2 >= -1650) {
                setPowers(1,-1,1,-1);
            }

        } else if(gamepad1.left_bumper){
            test = "Rotate CCW";
            while ((Drive.fr.getCurrentPosition() + Drive.br.getCurrentPosition()) / 2 <= 1650) {
                setPowers(-1,1,-1,1);
            }

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
