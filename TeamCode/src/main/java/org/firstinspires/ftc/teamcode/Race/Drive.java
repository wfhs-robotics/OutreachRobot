package org.firstinspires.ftc.teamcode.Race;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Race", group="Robot")
public class Drive extends LinearOpMode {
    Hardware robot = new Hardware();
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        double left;
        double right;
        double drive;
        double turn;
        double max;

        telemetry.addData(">", "Robot Ready.  Press Play.");    //
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            drive = -gamepad1.left_stick_y;
            turn  =  gamepad1.right_stick_x;

            // Combine drive and turn for blended motion.
            left  = drive + turn;
            right = drive - turn;

            // Normalize the values so neither exceed +/- 1.0
            max = Math.max(Math.abs(left), Math.abs(right));
            if (max > 1.0)
            {
                left /= max;
                right /= max;
            }

            robot.leftDrive.setPower(left);
            robot.rightDrive.setPower(right);
            robot.leftForwardDrive.setPower(left);
            robot.rightForwardDrive.setPower(right);

        }
    }
}
