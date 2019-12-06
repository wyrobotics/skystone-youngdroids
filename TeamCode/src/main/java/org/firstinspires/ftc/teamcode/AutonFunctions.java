package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;

@Disabled
public class AutonFunctions {

    public double RotationsPerTileForward = 2100;



    public void Turn90CW(GyroSensor gyro, DcMotor FL, DcMotor FR, DcMotor BL, DcMotor BR){ // Turns the robot 90dg cw
        int StartDegree = gyro.getHeading(); // Initial position of the gyro
        while(gyro.getHeading() != StartDegree + 90){ // While the gyro hasn't turned 90 more degrees, keep turning
            FL.setPower(.5); BL.setPower(.5);
            FR.setPower(-.5); BR.setPower(-.5);
        }
    }

    public void Turn90CCW(GyroSensor gyro, DcMotor FL, DcMotor FR, DcMotor BL, DcMotor BR){ // Turns the robot 90dg ccw
        int StartDegree = gyro.getHeading(); // Similar process to the one above, but turns the other direction
        while(gyro.getHeading() != StartDegree - 90){
            FL.setPower(-.5); BL.setPower(-.5);
            FR.setPower(.5); BR.setPower(.5);
        }
    }

    public int AverageRotation(DcMotor FL, DcMotor FR, DcMotor BL, DcMotor BR){ //Averages the number of rotations that the 4 wheels have
        return (FL.getCurrentPosition() + FR.getCurrentPosition() + BL.getCurrentPosition() + BR.getCurrentPosition()) / 4;
    }
    public void MoveForward(int tiles, DcMotor FL, DcMotor FR, DcMotor BL, DcMotor BR){ // Moves forward [x] tiles

        int AverageStartRotation = AverageRotation(FL, FR, BL, BR);

        while(AverageRotation(FL,FR,BL,BR) - AverageStartRotation <= tiles * RotationsPerTileForward){ // Checks to see if it has travelled [x] tiles
            FL.setPower(.5); BL.setPower(.5); // If not, keep moving forward
            FR.setPower(.5); BR.setPower(.5);
        }
    }

}
