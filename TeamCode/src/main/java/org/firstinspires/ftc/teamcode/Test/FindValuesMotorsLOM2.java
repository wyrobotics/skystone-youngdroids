package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="FindValuesMotorsTEST2019", group = "Test")
public class FindValuesMotorsLOM2 extends LinearOpModeController {

    @Override
    public void OpModeEvents() {
        // Motor and servo Stuff
        if (rightBumper) {
            Drive.LifterPower = 1;
        } else if (leftBumper) {
            Drive.LifterPower = -1;
        } else {
            Drive.LifterPower = 0;
        }


        if  (leftTrigger) {
            Drive.GrabberPos += .1;
        } else if (rightTrigger) {
            Drive.GrabberPos -= .1;
        }


        if (dPadUp) {
            Drive.PlateGrabLPos += .1;
            Drive.PlateGrabRPos += .1;
        } else if (dPadDown) {
            Drive.PlateGrabLPos -= .1;
            Drive.PlateGrabRPos -= .1;
        }


        if (x) {
            Drive.releaseInPos -= .1;
        } else if (b) {
            Drive.releaseInPos += .1;
        }
        if (y) {
            Drive.InLPower += 1;
            Drive.InRPower += 1;
        } else if (a) {
            Drive.InRPower -= 1;
            Drive.InLPower += 1;
        }

        Drive.SetMotorPower();

        telemetry.addData("InLPower: ",Drive.InLPower);
        telemetry.addData("InRPower: ",Drive.InRPower);
        telemetry.addData("PlateGrabLPos: ",Drive.PlateGrabLPos);
        telemetry.addData("PlateGrabRPos: ",Drive.PlateGrabRPos);
        telemetry.addData("LifterPower: ",Drive.LifterPower);
        telemetry.addData("GrabberPos: ",Drive.GrabberPos);
        telemetry.addData("releaseInPos: ",Drive.releaseInPos);




        telemetry.addData("A: ", a);
        telemetry.addData("B: ", b);
        telemetry.addData("X: ", x);
        telemetry.addData("Y: ", y);
        telemetry.addData("LT: ", leftTrigger);
        telemetry.addData("RT: ", rightTrigger);
        telemetry.addData("jsLX: ", jsLX);
        telemetry.addData("jsLY: ", jsLY);
        telemetry.addData("Start: ", start);
        telemetry.addData("RB: ", rightBumper);
        telemetry.addData("LB: ", leftBumper);

        telemetry.update();
    }
}