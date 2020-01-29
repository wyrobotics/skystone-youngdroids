package org.firstinspires.ftc.teamcode.SkyStone;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

@Disabled
public class AutonFunctions{

    public void init(HardwareMap HM) {
        tSensor = HM.get(DigitalChannel.class,"tSensor");


        fl = HM.dcMotor.get("fl"); bl = HM.dcMotor.get("bl"); // Maps all our motors/servos
        fr = HM.dcMotor.get("fr"); br = HM.dcMotor.get("br");
        InL = HM.dcMotor.get("InL"); InR = HM.dcMotor.get("InR");
        Lifter = HM.dcMotor.get("Lifter"); Grabber = HM.servo.get("Grabber");

        inCtrlL = HM.servo.get("inCtrlL"); inCtrlR = HM.servo.get("inCtrlR");
        PlateGrabL = HM.servo.get("PlateGrabL"); PlateGrabR = HM.servo.get("PlateGrabR");


        Lifter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        Lifter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER); br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER); bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        tSensor.setMode(DigitalChannel.Mode.INPUT);


        Lifter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        fl.setDirection(DcMotorSimple.Direction.REVERSE); bl.setDirection(DcMotorSimple.Direction.REVERSE);
        InL.setDirection(DcMotorSimple.Direction.REVERSE);
        PlateGrabL.setDirection(Servo.Direction.REVERSE);
        inCtrlL.setDirection(Servo.Direction.REVERSE);
    }

    public DcMotor fl, bl, fr, br; // 4 Drive Motors
    public DcMotor InL, InR; // Intake Motors
    public Servo PlateGrabL, PlateGrabR; // Plate Servos + Release
    public Servo Grabber;
    public DcMotor Lifter;
    public Servo inCtrlL, inCtrlR;
    public DigitalChannel tSensor;

    public double RotationsPerTileForward = 2150, RotationsPer90 = 1700, RotationsPerStafe = 3000;

    public void Rotate90(double pow, boolean opActive){ // pow is how fast it moves, + is CW, - is CCW
        double dir = 1;
        if(pow < 0){
            dir = -1;
        }
        while( dir * AvgLeftPos() <= dir * RotationsPer90 && dir * AvgRightPos() <= dir * RotationsPer90 && opActive){
            fl.setPower(pow); bl.setPower(pow);
            fr.setPower(-pow); br.setPower(-pow);
        }
        fl.setPower(0); bl.setPower(0);
        fr.setPower(0); br.setPower(0);
    }

    public void Strafe(double tiles, boolean opActive){ // + is to the right, - to the left
        if (!opActive) {return;}

        double dir = tiles / Math.abs(tiles);

        while( (dir * (fl.getCurrentPosition() + fr.getCurrentPosition())) / 2.0 > dir *tiles * RotationsPerStafe &&
                (dir * (br.getCurrentPosition() + bl.getCurrentPosition())) / 2.0 > dir * tiles * RotationsPerStafe &&
                opActive){
            fl.setPower(.5 * dir); bl.setPower(-.5 * dir);
            fr.setPower(.5 * dir); br.setPower(-.5 * dir);
        }
        fl.setPower(0); bl.setPower(0);
        fr.setPower(0); br.setPower(0);
        resetMotorEncoder();
    }

    // Average positions of our wheels
    public double AvgLeftPos(){
        return (fl.getCurrentPosition() + bl.getCurrentPosition()) / 2;
    }
    public double AvgRightPos(){
        return (fr.getCurrentPosition() + br.getCurrentPosition()) / 2;
    }
    public double AvgFrontPos() {
        return (fr.getCurrentPosition() + fl.getCurrentPosition()) / 2;
    }
    public double AvgBackPos() {
        return (br.getCurrentPosition() + bl.getCurrentPosition()) / 2;
    }
    public double AverageRotation(){ //Averages the number of rotations that the 4 wheels have
        return (fl.getCurrentPosition() + fr.getCurrentPosition() + bl.getCurrentPosition() + br.getCurrentPosition()) / 4;
    }

    public void MoveForward(double tiles, boolean opActive){ // Moves forward [x] tiles
        if (!opActive) {return;}

        double dir = tiles / Math.abs(tiles);

        while( dir * AverageRotation() <= dir * tiles * RotationsPerTileForward){ // Checks to see if it has travelled [x] tiles
            fl.setPower(dir * .5); bl.setPower(dir * .5); // If not, keep moving forward
            fr.setPower(dir * .5); br.setPower(dir * .5);
        }
        fl.setPower(0); bl.setPower(0);
        fr.setPower(0); br.setPower(0);
        resetMotorEncoder();
    }

    public void resetMotorEncoder(){
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER); br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER); bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }


}
