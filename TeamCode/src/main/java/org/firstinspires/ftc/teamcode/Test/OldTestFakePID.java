package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.SkyStone.AutonFunctionsV2;

@Disabled
@TeleOp(name="TestThisFirst", group="Test")
public class OldTestFakePID extends LinearOpMode {
    AutonFunctionsV2 auto = new AutonFunctionsV2();

    String test = "Do nothing";


    @Override
    public void runOpMode() throws InterruptedException {

        auto.init(hardwareMap);

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
            telemetry.addData("FL, FR: ", auto.fl.getCurrentPosition() + " , " + auto.fr.getCurrentPosition());
            telemetry.addData("BL, BR: ", auto.bl.getCurrentPosition() + " , " + auto.br.getCurrentPosition());

            telemetry.update();

        }
    }

    public void gamepadHandler(boolean op) {
        if (!op) {return;}

        if(gamepad1.dpad_up){
            test = "Move Forward";

            forward(1);

        } else if(gamepad1.dpad_down){
            test = "Move Backward";
            forward(-1);

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
        auto.LFWheelPower = a; auto.RFWheelPower = b;
        auto.LBWheelPower = c; auto.RBWheelPower = d;
        auto.SetMotorPower();
    }
    public void reset() {
        setPowers(0,0,0,0);
        auto.fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); auto.br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        auto.fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); auto.bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        auto.fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER); auto.br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        auto.fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER); auto.bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        auto.fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); auto.br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        auto.fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); auto.br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void forward(int tiles) {
        reset();
        double goal = tiles * auto.RotationsPerTileForward;
        double errorR = goal, errorRT = 0;
        double errorL = goal, errorLT = 0;
        double lastErrorR = errorR;
        double lastErrorL = errorL;

        final double Kp = 0.01;
        final double Ki = 0;
        final double Kd = 0;

        double proportionalL, proportionalR;
        double integralL, integralR;
        double derivativeL, derivativeR;

        final double integralZone = 250;

        int errorCount = 0;

        double currentR, currentL;

        while (errorCount < 100) {
            if ( Math.abs(errorR) <= 25 && Math.abs(errorL) <= 25) {
                errorCount++;
            } else {
                errorCount = 0;
            }


            errorR = goal - auto.AvgRightPos();
            errorL = goal - auto.AvgRightPos();


            if (Math.abs(errorR) < integralZone && errorR != 0) {
                errorRT += errorR;
            } else {
                errorRT = 0;
            }
            if (Math.abs(errorL) < integralZone && errorL != 0) {
                errorLT += errorL;
            } else {
                errorRT = 0;
            }
            if (errorRT > 1000) {
                errorRT = 1000;
            }
            if (errorLT > 1000) {
                errorLT = 1000;
            }
            if (errorR == 0){
                derivativeR = 0;
            }
            if (errorL == 0) {
                derivativeL = 0;
            }


            proportionalL = errorL * Kp;
            integralL = errorL * Ki;
            derivativeL = (errorL - lastErrorL) * Kd;

            proportionalR = errorR * Kp;
            integralR = errorR * Ki;
            derivativeR = (errorR - lastErrorR) * Kd;

            currentL = proportionalL + integralL + derivativeL;
            currentR = proportionalR + integralR + derivativeR;

            setPowers(currentL,currentR,currentL,currentR);

            lastErrorL = errorL; lastErrorR = errorR;
        }

    }

}