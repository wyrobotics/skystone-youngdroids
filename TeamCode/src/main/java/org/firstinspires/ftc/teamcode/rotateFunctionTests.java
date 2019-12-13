package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;

@TeleOp(name = "Rotate 90")
public class rotateFunctionTests extends LinearOpMode {
    DcMotor lf = hardwareMap.dcMotor.get("lf");
    DcMotor lb = hardwareMap.dcMotor.get("lb");
    DcMotor rf = hardwareMap.dcMotor.get("rf");
    DcMotor rb = hardwareMap.dcMotor.get("rb");
    GyroSensor gyro = hardwareMap.gyroSensor.get("gyro");
    boolean bPressed = false;
    boolean xPressed = false;

    public void ZeroMotors(){
        lf.setPower(0);
        lb.setPower(0);
        rf.setPower(0);
        rb.setPower(0);
    }

    public void rotateCW(int degree, int error){
        int initialHeading = gyro.getHeading();
        if(degree > gyro.getHeading()){
            while(gyro.getHeading() - initialHeading < 360 - initialHeading) {
                lf.setPower(1);
                lb.setPower(1);
                rf.setPower(-1);
                rb.setPower(-1);
            }
        }
        while(gyro.getHeading() < degree){
            lf.setPower(1);
            lb.setPower(1);
            rf.setPower(-1);
            rb.setPower(-1);
        }
        while(! (Math.abs(degree - gyro.getHeading()) <= error)){
            if(gyro.getHeading() > degree){
                lf.setPower(-0.1);
                lb.setPower(-0.1);
                rf.setPower(0.1);
                rb.setPower(0.1);
            }
            if(gyro.getHeading() < degree) {
                lf.setPower(0.1);
                lb.setPower(0.1);
                rf.setPower(-0.1);
                rb.setPower(-0.1);
            }
            ZeroMotors();
        }
    }
    public void rotateCCW(int degree, int error){
        int initialHeading = gyro.getHeading();
        while(initialHeading - gyro.getHeading() > 0){
            lf.setPower(-1);
            lb.setPower(-1);
            rf.setPower(1);
            rb.setPower(1);
        }
        while(gyro.getHeading() > degree){
            lf.setPower(-1);
            lb.setPower(-1);
            rf.setPower(1);
            rb.setPower(1);
        }
        while(! (Math.abs(degree - gyro.getHeading()) <= error)) {
            if (gyro.getHeading() > degree) {
                lf.setPower(-0.1);
                lb.setPower(-0.1);
                rf.setPower(0.1);
                rb.setPower(0.1);
            }
            if (gyro.getHeading() < degree) {
                lf.setPower(0.1);
                lb.setPower(0.1);
                rf.setPower(-0.1);
                rb.setPower(-0.1);
            }
            ZeroMotors();
        }
    }

    @Override public void runOpMode(){

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


}
