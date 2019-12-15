package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Encoder")
public class GetEncoderVals extends LinearOpMode {

    DcMotor FL, FR, BL, BR; // All 4 drive motors

    DcMotor InL, InR; // Intake motors
    Servo releaseIn; // Intake release

    Servo PlateGrabL, PlateGrabR; // Plate grab servos


    public double RotationsPerTileForward = 2100, RotationsPer90 = 1050, RotationsPerStafe = 1050;

    @Override
    public void runOpMode() throws InterruptedException {

        // HARDWARE MAPPING AND INITIALIZING
        FL = hardwareMap.dcMotor.get("fl"); BL = hardwareMap.dcMotor.get("bl");
        FR = hardwareMap.dcMotor.get("fr"); BR = hardwareMap.dcMotor.get("br");

        InL = hardwareMap.dcMotor.get("InL"); InR = hardwareMap.dcMotor.get("InR");
        releaseIn = hardwareMap.servo.get("releaseIn");

        PlateGrabL = hardwareMap.servo.get("PlateGrabL"); PlateGrabR = hardwareMap.servo.get("PlateGrabR");


        FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        FR.setMode(DcMotor.RunMode.RUN_USING_ENCODER); BR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FL.setMode(DcMotor.RunMode.RUN_USING_ENCODER); BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        FL.setDirection(DcMotorSimple.Direction.REVERSE); BL.setDirection(DcMotorSimple.Direction.REVERSE);
        InL.setDirection(DcMotorSimple.Direction.REVERSE);
        PlateGrabL.setDirection(Servo.Direction.REVERSE);

        releaseIn.setPosition(.5);
        waitForStart();

        while(opModeIsActive()){
            if(gamepad1.y){
                MoveForward(1);
            }
            if(gamepad1.a){
                MoveForward(-1);
            }
            if(gamepad1.x){
                Strafe(-1);
            }
            if(gamepad1.b){
                Strafe(1);
            }
            if(gamepad1.left_bumper){
                Rotate90(-.6);
            }
            if(gamepad1.right_bumper){
                Rotate90(.6);
            }
        }
    }

    public void Rotate90(double pow){ // pow is how fast it moves, + is CW, - is CCW
        double dir = 1;
        if(pow < 0){
            dir = -1;
        }
        double InitLeft = InitLeftPos(), InitRight = InitRightPos();
        while( dir * (InitLeftPos() - InitLeft) <= RotationsPer90 && dir * (InitRightPos() - InitRight) <= RotationsPer90 && opModeIsActive()){
            FL.setPower(pow); BL.setPower(pow);
            FR.setPower(-pow); BR.setPower(-pow);
        }
        FL.setPower(0); BL.setPower(0);
        FR.setPower(0); BR.setPower(0);
    }
    public void Strafe(double tiles){ // + is to the right, - to the left
        int dir = 1;
        if (tiles < 0){
            dir = -1;
        }
        double InitFL = FL.getCurrentPosition(), InitFR = FR.getCurrentPosition();
        double InitBL = BL.getCurrentPosition(), InitBR = BR.getCurrentPosition();

        while( (dir * (FL.getCurrentPosition() + BR.getCurrentPosition() - InitFL - InitBR)) / 2 > tiles * RotationsPerStafe &&
                (dir * (FR.getCurrentPosition() + BL.getCurrentPosition() - InitFR - InitBL)) / 2 > tiles * RotationsPerStafe && opModeIsActive()){
            FL.setPower(-.5 * dir); BL.setPower(.5 * dir);
            FR.setPower(.5 * dir); BR.setPower(-.5 * dir);
        }
        FL.setPower(0); BL.setPower(0);
        FR.setPower(0); BR.setPower(0);
    }
    public double InitLeftPos(){
        return (FL.getCurrentPosition() + BL.getCurrentPosition()) / 2;
    }
    public double InitRightPos(){
        return (FR.getCurrentPosition() + BR.getCurrentPosition()) / 2;
    }
    public int AverageRotation(){ //Averages the number of rotations that the 4 wheels have
        return (FL.getCurrentPosition() + FR.getCurrentPosition() + BL.getCurrentPosition() + BR.getCurrentPosition()) / 4;
    }
    public void MoveForward(double tiles){ // Moves forward [x] tiles
        int AverageStartRotation = AverageRotation();
        if (tiles < 0) {
            while( AverageRotation() -  AverageStartRotation <= tiles * RotationsPerTileForward && opModeIsActive()) { // Checks to see if it has travelled [x] tiles
                FL.setPower(-.5); BL.setPower(-.5); // If not, keep moving forward
                FR.setPower(-.5); BR.setPower(-.5);
            }
        } else {
            while( AverageRotation() >= tiles * RotationsPerTileForward + AverageStartRotation && opModeIsActive()) { // Checks to see if it has travelled [x] tiles
                FL.setPower(.5); BL.setPower(.5); // If not, keep moving forward
                FR.setPower(.5); BR.setPower(.5);
            }
        }
        FL.setPower(0); BL.setPower(0);
        FR.setPower(0); BR.setPower(0);
    }
}
