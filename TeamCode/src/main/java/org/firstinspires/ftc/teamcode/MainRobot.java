package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class MainRobot {

    // SERVOS
    Servo GrabServo; // The servo that grabs the block

    Servo PlateGrabberL;//Servo that grabs the plate to move it
    Servo PlateGrabberR;//Servo that grabs the plate to move it

    // MOTORS
    DcMotor UltraSlideMotorV;//vertical ultra slide motor
    DcMotor UltraSlideMotorH;//Horizontal ultra slide motor

    // GYRO

    GyroSensor gyro;

    private static double ClosePos = 0.0;
    private static double OpenPos = 1.0;
    public static boolean GrabOpen = false;

    public void init(HardwareMap HM) {
        gyro = HM.gyroSensor.get("gyro");// map the gyro
        GrabServo = HM.servo.get("Grabber_Servo");//map the servo that grabs blocks
        PlateGrabberL = HM.servo.get("Plate_Grabber_Servo_L");//map the left servo that grabs the plate
        PlateGrabberR = HM.servo.get("Plate_Grabber_Servo_R");//map the right servo that grabs the plate
        UltraSlideMotorV = HM.dcMotor.get("Vertical_Ultra_Slide_Motor");//map the vertical ultra slide motor
        UltraSlideMotorH = HM.dcMotor.get("Horizontal_Ultra_Slide_Motor");//map the horizontal ultra slide motor
        UltraSlideMotorV.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        UltraSlideMotorH.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        gyro.calibrate();

        PlateGrabberL.setDirection(Servo.Direction.REVERSE);
    }


    double GrabServoDif = 0;


    public double GrabServoDir() {
        if (gyro.getHeading() <= 90) {
            return (gyro.getHeading() / 180) + 0.5 + GrabServoDif;
        }
        if (gyro.getHeading() >= 270) {
            return -gyro.getHeading() / 180 + 2 + GrabServoDif;
        }
        return 0.5;
    }

    public void TurnGrabberLeft(){
        GrabServoDif -= 1 / 180;
    }
    public void TurnGrabberRight(){
        GrabServoDif += 1 / 180;
    }

    public void GrabBlock(){
        GrabServo.setPosition(ClosePos);
    }
    public void ReleaseBlock() { GrabServo.setPosition((OpenPos)); }

    public void GrabPlate(){
        PlateGrabberR.setPosition(.4);
        PlateGrabberR.setPosition(.4);

    }
    public void ReleasePlate(){
        PlateGrabberR.setPosition(0);
        PlateGrabberR.setPosition(0);
    }

    // add the function that sets what the grabber direction should be
}
