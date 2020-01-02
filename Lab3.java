public class Lab3
{
    public static void testLightCandles1()
    {
        Robot.load("candles1.txt");
        Robot.setDelay(0.05);
        lightCandles();
    }

    public static void testLightCandles2()
    {
        Robot.load("candles2.txt");
        Robot.setDelay(0.05);
        lightCandles();
    }

    public static void lightCandles()
    {
        lightingCandles();
        lightingCandles();
        lightingCandles();
        lightingCandles();
        lightingCandles();
        lightingCandles();
        lightingCandles();
        lightingCandles();
        lightingCandles();
        lightingCandles();
    }

    public static void lightingCandles()
    {
        //is the candle one unit high?
        Robot.turnLeft();
        Robot.move();
        turnRight();
        if (Robot.frontIsClear())
        //candle is one unit high!
        {
            completeOneUnitHighCandle();
        }
        else
        //candle is two units high!
        {
            completeTwoUnitHighCandle();
        }   
    }

    public static void completeOneUnitHighCandle()
    {
        //precondition: Robot is to the top left of a one unit high candle
        //postcondition: Robot lights the one unit high candle
        Robot.move();
        Robot.makeDark();
        Robot.move();
        turnRight();
        Robot.move();
        Robot.turnLeft();   
    }

    public static void completeTwoUnitHighCandle()
    {
        //precondition: Robot is at the top left of a two unit high candle
        //postcondition: Robot lights the two unit high candle
        Robot.turnLeft();
        Robot.move();
        turnRight();
        Robot.move();
        Robot.makeDark();
        Robot.move();
        turnRight();
        Robot.move();
        Robot.move();
        Robot.turnLeft();   
    }

    public static void testCompleteRoom1()
    {
        Robot.load("room1.txt");
        Robot.setDelay(0.05);
        completeRoom();
    }

    public static void testCompleteRoom2()
    {
        Robot.load("room2.txt");
        Robot.setDelay(0.05);
        completeRoom();
    }


    public static void completeRoom()
    {
        makeRowDark();
        makeRowDark();
        makeRowDark();
        makeRowDark();
    }

    public static void makeTileDark()
    {
        //precondition: Robot is standing next to a tile or wall
        //postcondition: Robot made the tile dark (if there is a light tile) and has now move up one unit
        //is there a room?
        if (isThereRoom())
        {
            //there is a room!
            //is the tile in the room dark?
            if (isTileAlreadyDark())
            {
                //the tile in the room is already dark
                Robot.move();
            }
            else
            {
                //the tile in the room is still light
                Robot.turnLeft();
                Robot.move();
                Robot.makeDark();
                turnAround();
                Robot.move();
                Robot.turnLeft();
                Robot.move();
            }
        }
        else
        //there is no room!
        {
            Robot.move();

        }   
    }

    public static void makeRowDark()
    {
        //precondition: Robot is facing at a row that it has no gone through yet
        //postcondition: Robot made each tiles in each room dark, and is at the end of the row facing a new one
        makeTileDark();   
        makeTileDark();   
        makeTileDark();   
        makeTileDark();
        atEndofRow();
    }

    public static void atEndofRow()
    {
        //precondition: Robot is at the end of a row
        //postcondition: The robot is facing at a new row. If there is a light tile to the left of the robot, then the robot made it dark
        //is the tile to the left of the robot already dark?
        if (isThereRoom())
        //there is room!
        //now is the tile in the room already dark?
        {
            if (isTileAlreadyDark())
            //the tile in the room is already dark
            {
                turnRight();
            }
            else
            //the tile in the room is not dark
            {
                Robot.turnLeft();
                Robot.move();
                Robot.makeDark();
                turnAround();
                Robot.move();
            }
        }
        else
        //there is no room!
        {
            turnRight();
        }   
    }

    public static boolean isThereRoom()
    {
        //is there a room?
        Robot.turnLeft();
        if (Robot.frontIsClear())
        //there is a room!
        {
            turnRight();
            return true;
        }
        else
        //there is not a room
        {
            turnRight();
            return false;
        }

    }

    public static boolean isTileAlreadyDark()
    {
        //is there a room?
        if (isThereRoom())
        {
            //yes there is! now, is it dark or light?
            Robot.turnLeft();
            Robot.move();
            if (Robot.onDark())
            {
                //it's dark!
                turnAround();
                Robot.move();
                Robot.turnLeft();
                return true;
            }
            else
            {
                //it's light!
                turnAround();
                Robot.move();
                Robot.turnLeft();
                return false;
            }
        }
        else
        //there is no room
        {
            return false;
        }
    }

    public static void testSwapAll1()
    {
        Robot.load("swap1.txt");
        Robot.setDelay(0.05);
        swapAll();
    }

    public static void testSwapAll2()
    {
        Robot.load("swap2.txt");
        Robot.setDelay(0.05);
        swapAll();
    }

    public static void swapAll()
    {
        swapTilesForOneRow();
        swapTilesForOneRow();
        swapTilesForOneRow();
        swapTilesForOneRow();
        swapTilesForOneRow();
        swapTilesForOneRow();
        swapTilesForOneRow();
        swapTilesForOneRow();
        swapTilesForOneRow();
        swapTileLastRow();

    }

    public static void swapTilesForOneRow()
    {
        //precondition: Robot is facing north and is in the middle of a row
        //postcondition: Robot swapped the color of the tiles in the row and moves up to a new row
        turnRight();
        Robot.move();
        //is the tile on the right dark?
        if (Robot.onDark())
        {
            //the tile on the right is dark!
            turnAround();
            Robot.move();
            Robot.move();
            //if so, is the tile on the left dark as well?
            if (Robot.onDark())
            //the tile on the left is also dark
            {
                ifThereAreDarkTilesOnBothSide();
            }
            else
            //the tile on the left is light 
            {
                ifDarkTileIsOnRightSideOnly();
            }

        }
        else
        //the tile on the right is not dark
        {
            isThereDarkTileOnLeft();
        }
    }

    public static void makeOtherTileLight()
    {
        //precondition: Robot is on the left or right side of the row
        //postcondition: Robot makes the other side of the row light and moves up to a new row 
        turnAround();
        Robot.move();
        Robot.move();
        Robot.makeLight();
        turnAround();
        Robot.move();
    }

    public static void swapTileLastRow()
    {
        //precondition: Robot is on the last (top) row
        //postcondition: The robot swapped the left and right tiles
        turnRight();
        Robot.move();
        //is the tile on the right dark?
        if (Robot.onDark())
        //the tile on the right is dark!
        {
            turnAround();
            Robot.move();
            Robot.move();
            //is the tile on the left dark as well?
            if (Robot.onDark())
            //the tile on the left is also dark!
            {
                turnAround();
            }
            else
            //the tile on the left is light!
            {
                Robot.makeDark();
                makeOtherTileLight();
            }

        }
        else
        //the tile on the right is not dark!
        {
            turnAround();
            Robot.move();
            Robot.move();
            //is the tile on the left dark?
            if (Robot.onDark())
            //the tile on the left is dark!
            {
                Robot.makeLight();
                turnAround();
                Robot.move();
                Robot.move();
                Robot.makeDark();
            }
        }
    }

    public static void isThereDarkTileOnLeft()
    {
        //precondition: The Robot is on the right side of the row
        //postcondition: The robot moves to the left side of the row and makes the left tile dark or light and moves up a row
        turnAround();
        Robot.move();
        Robot.move();
        //is the tile on the left dark?
        if (Robot.onDark())
        //the tile on the left is dark!
        {
            Robot.makeLight();
            makeOtherTileDark();
            turnRight();
            Robot.move();
        }
        else
        //the tile on the left is light!
        {
            turnAround();
            Robot.move();
            Robot.turnLeft();
            Robot.move();
        }
    }

    public static void makeOtherTileDark()
    {
        //precondition: The robot is on a light tile
        //postcondition: The robot moves to the opposite side of the row and makes that tile dark, and then moves up to a new row
        turnAround();
        Robot.move();
        Robot.move();
        Robot.makeDark();
        turnAround();
        Robot.move();
    }

    public static void ifThereAreDarkTilesOnBothSide()
    {
        //precondition: Robot is on the far right or far left of a row
        //postcondition: The robot moves up to a new row
        turnAround();
        Robot.move();
        Robot.turnLeft();
        Robot.move();   
    }

    public static void  ifDarkTileIsOnRightSideOnly()
    {
        //precondition: Robot is on the far left side of the row. The left tile is light while the right tile is dark
        //postcondition: Robot swaps the color of both the left and right tiles and moves up to a new row
        Robot.makeDark();
        makeOtherTileLight();
        turnRight();
        Robot.move();   
    }

    public static void testLightCandles3()
    {
        Robot.load("candles3.txt");
        Robot.setDelay(0.05);
        lightCandles();
    }

    public static void testLightCandles4()
    {
        Robot.load("candles4.txt");
        Robot.setDelay(0.05);
        lightCandles();
    }

    public static void testCompleteRoom3()
    {
        Robot.load("room3.txt");
        Robot.setDelay(0.05);
        completeRoom();
    }

    public static void testCompleteRoom4()
    {
        Robot.load("room4.txt");
        Robot.setDelay(0.05);
        completeRoom();
    }

    public static void testSwapAll3()
    {
        Robot.load("swap3.txt");
        Robot.setDelay(0.05);
        swapAll();
    }

    public static void testSwapAll4()
    {
        Robot.load("swap4.txt");
        Robot.setDelay(0.05);
        swapAll();
    }

    public static void turnRight()
    {
        //precondition: Robot is facing whatever direction
        //postcondition: Robot turns left three times in order to turn right from where it was initially facing
        Robot.turnLeft();
        Robot.turnLeft();
        Robot.turnLeft();
    }

    public static void turnAround()
    {
        //precondition: Robot is facing whatever direction
        //postcondition: Robot is facing the opposite direction
        Robot.turnLeft();
        Robot.turnLeft();
    }
}
