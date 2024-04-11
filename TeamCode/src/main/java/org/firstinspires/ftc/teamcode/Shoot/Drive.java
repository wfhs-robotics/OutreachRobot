package org.firstinspires.ftc.teamcode.Shoot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Shoot", group="Robot")
public class Drive extends LinearOpMode {
    Hardware robot = new Hardware();
    Boolean prevA = false;
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
            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = gamepad1.right_stick_x;

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double leftFront = (y + x + rx) / denominator;
            double leftBack = (y - x + rx) / denominator;
            double rightFront = (y - x - rx) / denominator;
            double rightBack = (y + x - rx) / denominator;

            if (gamepad1.a && gamepad1.a != prevA) {
                if(robot.shoot.isBusy()) {
                    robot.shoot.setPower(0);
                } else {
                    robot.shoot.setPower(1);
                }
            }

            prevA = gamepad1.a;
            robot.leftDrive.setPower(leftFront);
            robot.rightDrive.setPower(rightFront);
            robot.leftForwardDrive.setPower(leftBack);
            robot.rightForwardDrive.setPower(rightBack);
        }
    }
}
