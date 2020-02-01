package org.firstinspires.ftc.teamcode.SkyStone;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
public class AutonFunctionsV2 extends MecanumDrive {

    public final double RotationsPerTileForward = 1850, RotationsPer90 = 1225, RotationsPerStafe = 3000;

    public void MoveForward(double tiles, boolean opActive){ // pow is how fast it moves, + is CW, - is CCW
        if (!opActive) {return;}
        resetMotorEncoder();
        if ( tiles < 0) {
            while ((fr.getCurrentPosition() + br.getCurrentPosition() +
                    fl.getCurrentPosition() + bl.getCurrentPosition()) / 4 >= RotationsPerTileForward * tiles) {
                setPowers(-.875,-1.0,-.875,-1.0);
            }
            setPowers(0,0,0,0);
        } else if ( tiles > 0) {
            while ((fr.getCurrentPosition() + br.getCurrentPosition() +
                    fl.getCurrentPosition() + bl.getCurrentPosition()) / 4 <= RotationsPerTileForward * tiles) {
                setPowers(.9,1.0,.9,1.0);
            }
            setPowers(0,0,0,0);
        }
        resetMotorEncoder();
    }
    public void Rotate90(double tiles, boolean opActive) {
        if (!opActive) {return;}
        resetMotorEncoder();
        if ( tiles < 0) {
            while ((fr.getCurrentPosition() + br.getCurrentPosition()) / 2 >=  tiles * RotationsPer90) {
                setPowers(1,-1,1,-1);
            }
            setPowers(0,0,0,0);
        } else if ( tiles > 0) {
            while ((fr.getCurrentPosition() + br.getCurrentPosition()) / 2 <= tiles * RotationsPer90) {
                setPowers(-1,1,-1,1);
            }
            setPowers(0,0,0,0);
        }
        resetMotorEncoder();
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
    public double AvgCornersLeft() { return (fl.getCurrentPosition() + br.getCurrentPosition()) / 2; }
    public double AvgCornersRight() { return (fr.getCurrentPosition() + br.getCurrentPosition()) / 2; }

    public double AverageRotation(){ //Averages the number of rotations that the 4 wheels have
        return (fl.getCurrentPosition() + fr.getCurrentPosition() + bl.getCurrentPosition() + br.getCurrentPosition()) / 4;
    }

    public void resetMotorEncoder(){
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER); br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER); bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void setPowers(double a, double b, double c, double d) {
        LFWheelPower = a; RFWheelPower = b;
        LBWheelPower = c; RBWheelPower = d;
        SetMotorPower();
    }


}
