package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
public class AutonFunctions {

    DcMotor FL, FR, BL, BR; // All 4 drive motors

    DcMotor InL, InR; // Intake motors

    Servo releaseIn; // Intake release

    Servo PlateGrabL, PlateGrabR; // Plate grab servos


    public double RotationsPerTileForward = 2150, RotationsPer90 = 1800, RotationsPerStafe = 3000;

    public void init(HardwareMap hardwareMap){
        // HARDWARE MAPPING AND INITIALIZING
        FL = hardwareMap.dcMotor.get("fl"); BL = hardwareMap.dcMotor.get("bl"); // map the Front wheels
        FR = hardwareMap.dcMotor.get("fr"); BR = hardwareMap.dcMotor.get("br"); // Map the Back wheels
        InL = hardwareMap.dcMotor.get("InL"); InR = hardwareMap.dcMotor.get("InR");
        releaseIn = hardwareMap.servo.get("releaseIn");
        PlateGrabL = hardwareMap.servo.get("PlateGrabL"); PlateGrabR = hardwareMap.servo.get("PlateGrabR");


        FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FR.setMode(DcMotor.RunMode.RUN_USING_ENCODER); BR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FL.setMode(DcMotor.RunMode.RUN_USING_ENCODER); BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        FL.setDirection(DcMotorSimple.Direction.REVERSE); BL.setDirection(DcMotorSimple.Direction.REVERSE);// reverse the left wheels direction
        InL.setDirection(DcMotorSimple.Direction.REVERSE);
        PlateGrabL.setDirection(Servo.Direction.REVERSE);

    }
    public void Rotate90(double pow, boolean opActive){ // pow is how fast it moves, + is CW, - is CCW
        double dir = 1;
        if(pow < 0){
            dir = -1;
        }
        while( dir * AvgLeftPos() <= dir * RotationsPer90 && dir * AvgRightPos() <= dir * RotationsPer90 && opActive){
            FL.setPower(pow); BL.setPower(pow);
            FR.setPower(-pow); BR.setPower(-pow);
        }
        FL.setPower(0); BL.setPower(0);
        FR.setPower(0); BR.setPower(0);
    }
    public void Strafe(double tiles, boolean opActive){ // + is to the right, - to the left
        int dir = 1;
        if (tiles < 0){
            dir = -1;
        }

        while( (dir * (FL.getCurrentPosition() + BR.getCurrentPosition())) / 2 > dir *tiles * RotationsPerStafe &&
                (dir * (FR.getCurrentPosition() + BL.getCurrentPosition())) / 2 > dir * tiles * RotationsPerStafe &&
                opActive){
            FL.setPower(-.5 * dir); BL.setPower(.5 * dir);
            FR.setPower(.5 * dir); BR.setPower(-.5 * dir);
        }
        FL.setPower(0); BL.setPower(0);
        FR.setPower(0); BR.setPower(0);
    }
    public double AvgLeftPos(){
        return (FL.getCurrentPosition() + BL.getCurrentPosition()) / 2;
    }
    public double AvgRightPos(){
        return (FR.getCurrentPosition() + BR.getCurrentPosition()) / 2;
    }
    public int AverageRotation(){ //Averages the number of rotations that the 4 wheels have
        return (FL.getCurrentPosition() + FR.getCurrentPosition() + BL.getCurrentPosition() + BR.getCurrentPosition()) / 4;
    }
    public void MoveForward(double tiles, boolean opActive){ // Moves forward [x] tiles
        double dir = 1; // Sets value of power for wheels, negative if moving back a number of tiles
        if (tiles < 0) {
            dir *= -1;
        }
        while( dir * AverageRotation() <= dir * tiles * RotationsPerTileForward && opActive){ // Checks to see if it has travelled [x] tiles
            FL.setPower(dir * .5); BL.setPower(dir * .5); // If not, keep moving forward
            FR.setPower(dir * .5); BR.setPower(dir * .5);
        }
        FL.setPower(0); BL.setPower(0);
        FR.setPower(0); BR.setPower(0);
        resetMotorEncoder();
    }
    public void resetMotorEncoder(){
        FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FR.setMode(DcMotor.RunMode.RUN_USING_ENCODER); BR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FL.setMode(DcMotor.RunMode.RUN_USING_ENCODER); BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    // TODO: NEED TO CHANGE HOW THE MOVEMENT IN AUTON WORKS BECAUSE CURRENTLY IT IS BUGGY AND MATH ISNT RIGHT


}
