package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class TestEncoders extends OpMode
{
    double forward, side;
    private DcMotor frontRightDrive, frontLeftDrive, backLeftDrive, backRightDrive;



    @Override
    public void init()
    {
        frontRightDrive = hardwareMap.get(DcMotor.class, "frontRightDrive");
        frontLeftDrive = hardwareMap.get(DcMotor.class, "frontLeftDrive");
        backRightDrive = hardwareMap.get(DcMotor.class, "backRightDrive");
        backLeftDrive = hardwareMap.get(DcMotor.class, "backLeftDrive");

        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);

        frontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }


    @Override
    public void loop()
    {
        movement();
        telemetry();
    }


    public void movement()
    {
        frontRightDrive.setPower(0.3);
        frontLeftDrive.setPower(0.3);
        backLeftDrive.setPower(0.3);
        backRightDrive.setPower(0.3);

        frontRightDrive.setTargetPosition(5581);
        frontLeftDrive.setTargetPosition(5581);
        backRightDrive.setTargetPosition(5581);
        backLeftDrive.setTargetPosition(5581);

        frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }
    public void telemetry()
    {
        telemetry.addData("FR Pos = ",frontRightDrive.getCurrentPosition());
        telemetry.addData("FL Pos = ",frontLeftDrive.getCurrentPosition());
        telemetry.addData("BR Pos = ",backRightDrive.getCurrentPosition());
        telemetry.addData("FR Pos = ",backLeftDrive.getCurrentPosition());
    }
}
