package org.firstinspires.ftc.teamcode.SkyStone;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
public abstract class AutonFunctionsV2 extends LinearOpMode {
    MecanumDrive Drive = new MecanumDrive();
    public final double RotationsPerTileForward = 1850, RotationsPer90 = 1225, RotationsPerStafe = 3000;

    public void MoveForward(double tiles){ // pow is how fast it moves, + is CW, - is CCW
        if (!opModeIsActive()) {return;}
        resetMotorEncoder();
        if ( tiles < 0) {
            while ((double)(Drive.fr.getCurrentPosition() + Drive.br.getCurrentPosition() +
                    Drive.fl.getCurrentPosition() + Drive.bl.getCurrentPosition()) / 4 >= RotationsPerTileForward * tiles && opModeIsActive()) {
                setPowers(-.875,-1.0,-.875,-1.0);
            }
            setPowers(0,0,0,0);
        } else if ( tiles > 0) {
            while ((double)(Drive.fr.getCurrentPosition() + Drive.br.getCurrentPosition() +
                    Drive.fl.getCurrentPosition() + Drive.bl.getCurrentPosition()) / 4 <= RotationsPerTileForward * tiles && opModeIsActive()) {
                setPowers(.9,1.0,.9,1.0);
            }
            setPowers(0,0,0,0);
        }
        resetMotorEncoder();
    }
    public void Rotate90(double tiles) {
        if (!opModeIsActive()) {return;}
        resetMotorEncoder();
        if ( tiles < 0) {
            while ((double)(Drive.fr.getCurrentPosition() + Drive.br.getCurrentPosition()) / 2 >=  tiles * RotationsPer90 && opModeIsActive()) {
                setPowers(1,-1,1,-1);
            }
            setPowers(0,0,0,0);
        } else if ( tiles > 0) {
            while ((double)(Drive.fr.getCurrentPosition() + Drive.br.getCurrentPosition()) / 2 <= tiles * RotationsPer90 && opModeIsActive()) {
                setPowers(-1,1,-1,1);
            }
            setPowers(0,0,0,0);
        }
        resetMotorEncoder();
    }

    public void Rotate90V2(double tiles) {
        if(!opModeIsActive()) {return;}
        resetMotorEncoder();

        double dir = tiles / (Math.abs(tiles));

        while ((AvgCornersRight() + AvgCornersLeft()) / 2 * dir >= RotationsPer90 * dir && opModeIsActive()) {
            setPowers(1 * dir, -1 * dir, 1 * dir, -1 * dir);
        }
        setPowers(0,0,0,0);
        resetMotorEncoder();
    }

    public void resetMotorEncoder(){
        Drive.fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); Drive.br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Drive.fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); Drive.bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Drive.fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER); Drive.br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Drive.fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER); Drive.bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        Drive.fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); Drive.br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Drive.fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); Drive.br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void setPowers(double a, double b, double c, double d) {
        Drive.LFWheelPower = a; Drive.RFWheelPower = b;
        Drive.LBWheelPower = c; Drive.RBWheelPower = d;
        Drive.SetMotorPower();
    }
    public double AvgCornersLeft() {
        return (Drive.fr.getCurrentPosition() + Drive.bl.getCurrentPosition()) / 2;
    }
    public double AvgCornersRight() {
        return (Drive.fr.getCurrentPosition() + Drive.bl.getCurrentPosition()) / 2;
    }
    public double AvgLeftPos() {
        return (Drive.fr.getCurrentPosition() + Drive.bl.getCurrentPosition()) / 2;
    }
    public double AvgRightPos() {
        return (Drive.fr.getCurrentPosition() + Drive.bl.getCurrentPosition()) / 2;
    }public double AvgTop() {
        return (Drive.fr.getCurrentPosition() + Drive.bl.getCurrentPosition()) / 2;
    }
    public double AvgBot() {
        return (Drive.fr.getCurrentPosition() + Drive.bl.getCurrentPosition()) / 2;
    }
}
