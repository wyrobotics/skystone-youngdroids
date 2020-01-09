package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;

@TeleOp(name="SpinnyOP", group="Robot")
public class SpinnyOP extends LinearOpMode{
    MecanumDrive Drive = new MecanumDrive();

    @Override
    public void runOpMode() {
        Drive.init(hardwareMap);

        telemetry.addData("say", "before opmode");
        telemetry.update();
        waitForStart();

        boolean dpadUp = false, dpadDwn = false, dpadL = false, dpadR = false, xTog = false, yTog = false, bBut = false, aBut = false;
        boolean start, back;

        float curPow = 0;
        float powDir = 1;
        boolean isOn = false;

        while (opModeIsActive()) {
            Drive.InLPower = Drive.InRPower = curPow;

            if (yTog) {
                powDir = -1;
            } else {
                powDir = 1;
            }
            if (xTog) {
                isOn = true;
            } else {
                isOn = false;
            }
            if (aBut) {
                curPow += 1;
            }
            if (bBut) {
                curPow -= 1;
            }
            if (dpadDwn) {
                curPow = 0;
            }


            if(gamepad1.x) {
                xTog = !xTog;
            }
            if(gamepad1.y) {
                yTog = !yTog;
            }
            if(gamepad1.dpad_down != dpadDwn) {
                dpadDwn = gamepad1.dpad_down;
            }
            if(gamepad1.dpad_up) {
                dpadUp = !dpadUp;
            }
            if(gamepad1.a != aBut) {
                aBut = gamepad1.a;
            }
            if(gamepad1.b != bBut) {
                bBut = gamepad1.b;
            }


            telemetry.addData("Intake Power: ", Drive.InL.getPower());
            telemetry.update();
        }
    }
}
