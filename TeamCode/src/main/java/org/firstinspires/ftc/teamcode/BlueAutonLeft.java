package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;

import java.sql.Driver;


@Autonomous(
        name = "BlueAutonLeft"
)
public class BlueAutonLeft extends LinearOpMode {

    DcMotor FL; // All 4 drive motors
    DcMotor FR;
    DcMotor BL;
    DcMotor BR;

    DcMotor InL; // Intake motors
    DcMotor InR;

    CRServo releaseIn; // Intake release

    CRServo PlateGrabL; // Plate grab servos
    CRServo PlateGrabR;

    GyroSensor gyro;

    public double RotationsPerTileForward = 2100;

    @Override
    public void runOpMode() throws InterruptedException {

        FL = hardwareMap.dcMotor.get("fl");
        FR = hardwareMap.dcMotor.get("fr");
        BL = hardwareMap.dcMotor.get("BL");
        BR = hardwareMap.dcMotor.get("BR");
        InL = hardwareMap.dcMotor.get("InL");
        InR = hardwareMap.dcMotor.get("InR");
        releaseIn = hardwareMap.crservo.get("releaseIn");
        PlateGrabL = hardwareMap.crservo.get("PlateGrabL");
        PlateGrabR = hardwareMap.crservo.get("PlateGrabR");

        gyro = hardwareMap.gyroSensor.get("gyro");// map the gyro

        gyro.calibrate();

        waitForStart();

        while(opModeIsActive()){

            MoveForward(1.5);
            MoveBackward(.5);

          //  help.   INCOMPLETE

        }
    }

    public int AverageRotation(){ //Averages the number of rotations that the 4 wheels have
        return (FL.getCurrentPosition() + FR.getCurrentPosition() + BL.getCurrentPosition() + BR.getCurrentPosition()) / 4;
    }
    public void MoveBackward(double tiles){
        int AverageStartRotation = AverageRotation();
        while (AverageRotation() - AverageStartRotation <= -tiles * RotationsPerTileForward){
            FL.setPower(-.5); BL.setPower(-.5); // If not, keep moving forward
            FR.setPower(-.5); BR.setPower(-.5);
        }
    }
    public void MoveForward(double tiles){ // Moves forward [x] tiles

        int AverageStartRotation = AverageRotation();

        while(AverageRotation() - AverageStartRotation <= tiles * RotationsPerTileForward){ // Checks to see if it has travelled [x] tiles
            FL.setPower(.5); BL.setPower(.5); // If not, keep moving forward
            FR.setPower(.5); BR.setPower(.5);
        }
    }
}
