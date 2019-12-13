package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.GyroSensor;

import java.sql.Driver;


@Autonomous(
        name = "BlueAutonLeft"
)
public class BlueAutonLeft extends LinearOpMode {

    DcMotor FL, FR, BL, BR; // All 4 drive motors

    DcMotor InL, InR; // Intake motors

    CRServo releaseIn; // Intake release

    CRServo PlateGrabL, PlateGrabR; // Plate grab servos

    GyroSensor gyro;

    public double RotationsPerTileForward = 2100;
    public double RotationsPer90 = 1050;
    public double RotationsPerStafe = 1050;

    @Override
    public void runOpMode() throws InterruptedException {

        // HARDWARE MAPPING AND INITIALIZING
        FL = hardwareMap.dcMotor.get("fl"); FR = hardwareMap.dcMotor.get("fr");
        BL = hardwareMap.dcMotor.get("BL"); BR = hardwareMap.dcMotor.get("BR");

        InL = hardwareMap.dcMotor.get("InL"); InR = hardwareMap.dcMotor.get("InR");

        releaseIn = hardwareMap.crservo.get("releaseIn");

        PlateGrabL = hardwareMap.crservo.get("PlateGrabL"); PlateGrabR = hardwareMap.crservo.get("PlateGrabR");

        FR.setDirection(DcMotor.Direction.REVERSE); BR.setDirection(DcMotor.Direction.REVERSE);

        InR.setDirection(DcMotor.Direction.REVERSE);
        PlateGrabR.setDirection(CRServo.Direction.REVERSE);

        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); BL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); BR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        FL.setMode(DcMotor.RunMode.RUN_USING_ENCODER); BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FR.setMode(DcMotor.RunMode.RUN_USING_ENCODER); BR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        gyro = hardwareMap.gyroSensor.get("gyro"); gyro.calibrate(); // map and calibrate the gyro

        waitForStart();

        while(opModeIsActive()){

            InR.setPower(.75); InL.setPower(.75);
            MoveForward(1.5);
            MoveForward(-.4);
            Rotate90(-.5);
            MoveForward(3);
            Rotate90(.5);
            MoveForward(.4);
            PlateGrabL.setPower(1);
            PlateGrabR.setPower(1);
            wait(500);
            PlateGrabL.setPower(0);
            PlateGrabR.setPower(0);
            Rotate90(-.5);
            Strafe(1.5);
            PlateGrabL.setPower(-1);
            PlateGrabR.setPower(-1);
            wait(500);
            PlateGrabL.setPower(0);
            PlateGrabR.setPower(0);
            MoveForward(-2.3);


        }
    }

    public void Rotate90(double pow){ // pow is how fast it moves, + is CW, - is CCW
        double dir = 1;
        if(pow < 0){
            dir = -1;
        }
        double InitLeft = InitLeftPos();
        double InitRight = InitRightPos();
        while( dir * (InitLeftPos() - InitLeft) <= RotationsPer90 && dir * (InitRightPos() - InitRight) <= RotationsPer90){
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
        double InitFL = FL.getCurrentPosition();
        double InitFR = FR.getCurrentPosition();
        double InitBL = BL.getCurrentPosition();
        double InitBR = BR.getCurrentPosition();
        while( (dir * (FL.getCurrentPosition() + BR.getCurrentPosition() - InitFL - InitBR)) / 2 > tiles * RotationsPerStafe &&
                (dir * (FR.getCurrentPosition() + BL.getCurrentPosition() - InitFL - InitBR)) / 2 > tiles * RotationsPerStafe){
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
    public void MoveForward(double tiles){ // Moves forward [x] tiles TODO: when it moves backwards, the telemetry shows it going backwards so it'll never be > goal telemetry
        double pow = 1; // Sets value of power for wheels, negative if moving back a number of tiles
        if (tiles < 0) {
            pow *= -1;
        }
        int AverageStartRotation = AverageRotation();
        while( pow * AverageRotation() - AverageStartRotation <= tiles * RotationsPerTileForward){ // Checks to see if it has travelled [x] tiles
            FL.setPower(pow * .5); BL.setPower(pow * .5); // If not, keep moving forward
            FR.setPower(pow * .5); BR.setPower(pow * .5);
        }
        FL.setPower(0); BL.setPower(0);
        FR.setPower(0); BR.setPower(0);
    }
}
