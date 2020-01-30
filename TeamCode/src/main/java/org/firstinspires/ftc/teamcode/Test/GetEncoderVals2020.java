package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.internal.system.Deadline;
import org.firstinspires.ftc.teamcode.SkyStone.AutonFunctions;

import java.util.concurrent.TimeUnit;


@TeleOp(name = "GetEncoderVals2020", group = "SkyStone")
public class GetEncoderVals2020 extends LinearOpMode {

   AutonFunctions auto = new AutonFunctions();


    public double RotationsPerTileForward = 2100, RotationsPer90 = 1050, RotationsPerStafe = 1050;
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
            telemetry.update();
        }
    }

    public void gamepadHandler(boolean op) {
        if(gamepad1.y){
            test = "Move Forward";
            auto.MoveForward(1,op);

        } else if(gamepad1.a){
            test = "Move Backward";
            auto.MoveForward(-1, op);

        } else if(gamepad1.x){
            test = "Strafe Left";
            auto.Strafe(-1, op);

        } else if(gamepad1.b){
            test = "Strafe Right";
            auto.Strafe(1, op);

        } else if(gamepad1.left_bumper){
            test = "Rotate CW";
            auto.Rotate90(-.6, op);

        } else if(gamepad1.right_bumper){
            test = "Rotate CCW";
            auto.Rotate90(.6, op);

        } else {
            test = "Do Nothing";
        }
    }

}
