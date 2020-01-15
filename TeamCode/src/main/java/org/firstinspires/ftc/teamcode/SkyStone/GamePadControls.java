package org.firstinspires.ftc.teamcode.SkyStone;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.Gamepad;

@Disabled
public class GamePadControls {
    Object[][] buttons; // {{name of button, value}}
    Object[][] modes; // {{name of button, mode, start value}}
    public GamePadControls(Gamepad a, Object[][] b) {
        modes = b;
        init();
    }
    //returns the current button value
    public boolean currentButVal(String a, Gamepad b) {
        boolean cVal;
        switch (a) {
            case "a": cVal = b.a; break;
            case "b": cVal = b.b; break;
            case "x": cVal = b.x; break;
            case "y": cVal = b.y; break;
            case "dpUp": cVal = b.dpad_up; break;
            case "dpDown": cVal = b.dpad_down; break;
            case "dpLeft": cVal = b.dpad_left; break;
            case "dpRight": cVal = b.dpad_right; break;
            case "rightBumper": cVal = b.right_bumper; break;
            case "LeftBumper": cVal = b.left_bumper; break;
            case "jsLeftButton": cVal = b.left_stick_button; break;
            case "jsRightButton": cVal = b.right_stick_button; break;
            case "guide": cVal = b.guide; break;
            case "start": cVal = b.start; break;
            case "back": cVal = b.back; break;
            default : cVal = false;
        }
        return cVal;
    }

    //changes the 3 column object into a 2 column object {name, button or toggle, boolean} -> {name, boolean}
    public void init() {
        for (int i = 0; i < modes.length; i++) {
            Object[] o = modes[i];
            String name = (String)o[0];
            Boolean start = (Boolean)o[2];

            buttons[i][0] = name;
            buttons[i][1] = start;

        }
    }
    // toggle
    public boolean toggle(Boolean a, Boolean b) {
        if (b) {
            return !a;
        } else {
            return a;
        }
    }

    // button
    public boolean button(Boolean a, Boolean b) {
        return b;
    }

    //updates the values in the buttons object
    public void updateController(Gamepad g) {
        for (int i = 0; i < buttons.length; i++) {
            boolean currentState = (Boolean)buttons[i][1];
            boolean gpButtonVal = currentButVal((String)buttons[i][0], g);
            switch ((String)modes[i][1]) {
                case "toggle": buttons[i][1] = toggle(currentState, gpButtonVal); break;
                case "button": buttons[i][1] = button(currentState, gpButtonVal); break;
            }
        }
    }

    //returns the current value of a button
    public boolean getVal(String a) {
        for (int i = 0; i < buttons.length; i++) {
            if (a == (String)buttons[i][0]) {
                return (Boolean)buttons[i][1];
            }
        }
        return false;
    }
}
