package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous
public class AutoTest extends LinearOpMode
{
    //Constants
     private int oneMeter = 1000;
     private int fullTurn = 1000;


    private DcMotor frontRightDrive, frontLeftDrive, backRightDrive, backLeftDrive;

    @Override
    public void runOpMode()
    {
        frontRightDrive = hardwareMap.get(DcMotor.class, "frontRightDrive");
        frontLeftDrive = hardwareMap.get(DcMotor.class, "frontLeftDrive");
        backRightDrive = hardwareMap.get(DcMotor.class, "backRightDrive");
        backLeftDrive = hardwareMap.get(DcMotor.class, "backLeftDrive");

        frontRightDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.REVERSE);

        frontRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontRightDrive.setPower(0.8);
        frontLeftDrive.setPower(0.8);
        backLeftDrive.setPower(0.8);
        backRightDrive.setPower(0.8);

        waitForStart();
        while(opModeIsActive())
        {
            autoCode();
            stop();
        }

    }

    public void autoCode()
    {
        
    }

    public void forward(double amount)
    {
        setTargetsAll((int) (Math.round(oneMeter * amount)));
        goToAllTargets();
        while(checkingTargetPostion()){}
    }

    public void backwards(double amount)
    {
        setTargetsAll(-((int) (Math.round(oneMeter * amount))));
        goToAllTargets();
        while(checkingTargetPostion()){}
    }
    public void right(double amount)
    {
        setTargetRightTopDiagonal((int) (Math.round(oneMeter * amount)));
        setTargetLeftTopDiagonal(-((int) (Math.round(oneMeter * amount))));
        goToAllTargets();
        while(checkingTargetPostion()){}
    }

    public void left(double amount)
    {
        setTargetRightTopDiagonal(-((int) (Math.round(oneMeter * amount))));
        setTargetLeftTopDiagonal((int) (Math.round(oneMeter * amount)));
        goToAllTargets();
        while(checkingTargetPostion()){}
    }

    public void turn(double degrees)
    {
        degrees = degrees % 360;
        if(degrees < 180)
        {
            turnClockWise(degrees);
        }
        else
        {
            degrees = 360 - degrees;
            turnAntiClockWise(degrees);
        }

    }

    public void turnClockWise(double degrees)
    {
        double turningAmount = (degrees/360) * fullTurn;
        setTargetsLeft((int) (Math.round(turningAmount)));
        setTargetsRight(-((int) (Math.round(turningAmount))));
        goToAllTargets();
        while(checkingTargetPostion()){}
    }

    public void turnAntiClockWise(double degrees)
    {
        double turningAmount = (degrees/360) * fullTurn;
        setTargetsLeft(-((int) (Math.round(turningAmount))));
        setTargetsRight((int) (Math.round(turningAmount)));
        goToAllTargets();
        while(checkingTargetPostion()){}
    }
    public void setTargetsAll(int desiredPostion)
    {
        frontRightDrive.setTargetPosition(frontRightDrive.getCurrentPosition() + desiredPostion);
        frontLeftDrive.setTargetPosition(frontLeftDrive.getCurrentPosition() + desiredPostion);
        backLeftDrive.setTargetPosition(backLeftDrive.getCurrentPosition() + desiredPostion);
        backRightDrive.setTargetPosition(backRightDrive.getCurrentPosition() + desiredPostion);
    }

    //Only right and left used in turing
    public void setTargetsRight(int desiredPostion)
    {
        frontRightDrive.setTargetPosition(frontRightDrive.getCurrentPosition() + desiredPostion);
        backRightDrive.setTargetPosition(backRightDrive.getCurrentPosition() + desiredPostion);
    }

    public void setTargetsLeft(int desiredPostion)
    {
        frontLeftDrive.setTargetPosition(frontLeftDrive.getCurrentPosition() + desiredPostion);
        backLeftDrive.setTargetPosition(backLeftDrive.getCurrentPosition() + desiredPostion);
    }

    //Only used in strafing left and right
    public void setTargetRightTopDiagonal(int desiredPostion)
    {
        frontRightDrive.setTargetPosition(frontRightDrive.getCurrentPosition()+ desiredPostion);
        backLeftDrive.setTargetPosition(backLeftDrive.getCurrentPosition() + desiredPostion);
    }

    public void setTargetLeftTopDiagonal(int desiredPostion)
    {
        frontLeftDrive.setTargetPosition(frontLeftDrive.getCurrentPosition() + desiredPostion);
        backRightDrive.setTargetPosition(backRightDrive.getCurrentPosition() + desiredPostion);
    }

    public boolean checkingTargetPostion()
    {
        int frp = frontRightDrive.getTargetPosition();
        int flp = frontLeftDrive.getTargetPosition();
        int brp = backRightDrive.getTargetPosition();
        int blp = backLeftDrive.getTargetPosition();
        //Horrible looking if statements check all encoders are within +- 5 of desired position
        if( ((frp < frontRightDrive.getCurrentPosition()+5) && (frp > frontRightDrive.getCurrentPosition()-5))  && ((flp < frontLeftDrive.getCurrentPosition()+5) && (flp > frontLeftDrive.getCurrentPosition()-5))  && ((brp < backRightDrive.getCurrentPosition()+5) && (brp > backRightDrive.getCurrentPosition()-5)) && ((blp < backLeftDrive.getCurrentPosition()+5) && (blp > backLeftDrive.getCurrentPosition()-5)) )
        {
            return false;
        }
        return true;
    }

    public void goToAllTargets()
    {
        frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void showTelemetry()
    {

    }
}


