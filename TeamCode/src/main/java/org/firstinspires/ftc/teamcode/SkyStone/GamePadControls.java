package org.firstinspires.ftc.teamcode.SkyStone;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.Gamepad;

@Disabled
public class GamePadControls {
    Gamepad gp;
    Object[][] buttons;
    Object[][] modes;
    GamePadControls(Gamepad a, Object[][] b) {
        gp = a;
        modes = b;
        init(gp);
    }

    public boolean currentButVal(String a, Gamepad b) {
        boolean cVal;
        switch (a) {
            case "a": cVal = b.a; break;
            case "b": cVal = b.b; break;
            case "x": cVal = b.x; break;
            case "y": cVal = b.y; break;
            case "dpUp": cVal = b.a; break;
            case "dpDown": cVal = b.a; break;
            case "dpLeft": cVal = b.a; break;
            case "dpRight": cVal = b.a; break;
            case "rightBumper": cVal = b.a; break;
            case "LeftBumper": cVal = b.a; break;
            case "jsLeftButton": cVal = b.a; break;
            case "jsRightButton": cVal = b.a; break;
            case "guide": cVal = b.a; break;
            case "start": cVal = b.a; break;
            case "quit": cVal = b.a; break;
            default : cVal = false;
        }
        return cVal;
    }

    public void init(Gamepad a) {
        for (int i = 0; i < modes.length; i++) {
            Object[] o = modes[i];
            String button = (String)o[0];
            String mode = (String)o[1];
            Boolean start = (Boolean)o[2];



            buttons[i][0] = button;

            switch (button) {
                case "a": buttons[i][1] =
            }

        }
    }
}
