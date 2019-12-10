package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.GyroSensor;

import java.sql.Driver;


@Autonomous(
        name = "BlueAutonLeft"
)
public class BlueAutonLeft extends LinearOpMode {
    MecanumDrive Drive = new MecanumDrive();
    AutonFunctions help = new AutonFunctions();
    GyroSensor gyro;

    @Override
    public void runOpMode() throws InterruptedException {

        Drive.init(hardwareMap);
        gyro = hardwareMap.gyroSensor.get("gyro");// map the gyro

        gyro.calibrate();

        waitForStart();

        //while(opModeIsActive()){

          //  help.   INCOMPLETE

        //}
    }
}
