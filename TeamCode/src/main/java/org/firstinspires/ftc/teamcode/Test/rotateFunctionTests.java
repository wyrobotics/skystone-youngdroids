package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;

@TeleOp(name = "Rotate 90", group = "Test")
public class rotateFunctionTests extends LinearOpMode {
    DcMotor lf = hardwareMap.dcMotor.get("lf"), lb = hardwareMap.dcMotor.get("lb");
    DcMotor rf = hardwareMap.dcMotor.get("rf"), rb = hardwareMap.dcMotor.get("rb");

    GyroSensor gyro = hardwareMap.gyroSensor.get("gyro");
    boolean bPressed = false, xPressed = false;

    public void turnBase(double a) { // sets the power of the left wheels to and and the right ones to -a
        lf.setPower(a); lb.setPower(a);
        rf.setPower(-a); rb.setPower(-a);
    }

    public void ZeroMotors(){
        turnBase(0);
    }

    public void rotateCW(int degree, int error){
        int initialHeading = gyro.getHeading();
        if(degree > gyro.getHeading()){
            while(gyro.getHeading() - initialHeading < 360 - initialHeading) {
                turnBase(1);
            }
        }
        while(gyro.getHeading() < degree){
            turnBase(1);
        }
        while(! (Math.abs(degree - gyro.getHeading()) <= error)){
            if(gyro.getHeading() > degree){
                turnBase(-.1);
            }
            if(gyro.getHeading() < degree) {
                turnBase(.1);
            }
            ZeroMotors(); //TODO: Is this supposed to be inside the loop?
        }
    }
    public void rotateCCW(int degree, int error){
        int initialHeading = gyro.getHeading();
        while(initialHeading - gyro.getHeading() > 0){
            turnBase(-1);
        }
        while(gyro.getHeading() > degree){
            turnBase(-1);
        }
        while(! (Math.abs(degree - gyro.getHeading()) <= error)) {
            if (gyro.getHeading() > degree) {
                turnBase(-.1);
            }
            if (gyro.getHeading() < degree) {
                turnBase(.1);
            }
            ZeroMotors();
        }
    }

    @Override
    public void runOpMode(){

        gyro.calibrate();

        int error = 3;

        waitForStart();

        if(gamepad1.x && !xPressed){
            rotateCW(90, error);
            xPressed = !xPressed;
        }
        if(gamepad1.b && !bPressed) {
            rotateCCW(90, error);
            bPressed = !bPressed;
        }
    }
    //TODO: Code looks good, should add more things though

}
