package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;

@Disabled
public class AutonFunctions {

    public double RotationsPerTileForward = 2100;



    public void Turn90CW(GyroSensor gyro, DcMotor FL, DcMotor FR, DcMotor BL, DcMotor BR){
        int StartDegree = gyro.getHeading();
        while(gyro.getHeading() != StartDegree + 90){
            FL.setPower(.5);
            BL.setPower(.5);
            FR.setPower(-.5);
            BR.setPower(-.5);
        }
    }

    public void Turn90CCW(GyroSensor gyro, DcMotor FL, DcMotor FR, DcMotor BL, DcMotor BR){
        int StartDegree = gyro.getHeading();
        while(gyro.getHeading() != StartDegree - 90){
            FL.setPower(-.5);
            BL.setPower(-.5);
            FR.setPower(.5);
            BR.setPower(.5);
        }
    }

    public void MoveForward(int tiles, DcMotor FL, DcMotor FR, DcMotor BL, DcMotor BR){

        int AverageStartRotation = AverageRotation(FL, FR, BL, BR);

        while(AverageRotation(FL,FR,BL,BR) - AverageStartRotation <= tiles * RotationsPerTileForward){
            FL.setPower(.5);
            BL.setPower(.5);
            FR.setPower(.5);
            BR.setPower(.5);
        }
    }

    public int AverageRotation(DcMotor FL, DcMotor FR, DcMotor BL, DcMotor BR){
        return (FL.getCurrentPosition() + FR.getCurrentPosition() + BL.getCurrentPosition() + BR.getCurrentPosition()) / 4;
    }
}
