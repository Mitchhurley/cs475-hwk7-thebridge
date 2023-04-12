public class OneLaneBridge extends Bridge{
    //Might have to add more variables
    private final int MAX_CARS;
    private Object cantEnter = new Object();
    private Object leaving = new Object();


    public OneLaneBridge(final int MAX_CARS){
        this.MAX_CARS = MAX_CARS;
    }

    public void arrive(Car car) throws InterruptedException{
        //Add in sync block
        synchronized (cantEnter){
            while(direction == car.getDirection() || bridge.size() < MAX_CARS)//add conditions for it facing the right way, and the max size of the bridge isnt reached
            {
                cantEnter.wait();//If it cant go right now, put it in the queue
            }
            //set the time of entry for the car that just entered

            //add the car to the bridge list

            //print out a car summary

            //adjust time? maybe idk
        }
    }

    public void exit(Car c){
        //add in sync block to make only one car leave at once
        synchronized(leaving){
            //if this isnt the first car in the list, then it notify's all
            while(bridge.indexOf(c) != 0){
                leaving.notifyAll();
            }
            //add sync block to prevent multiple edits of shared vars
            synchronized(cantEnter){
                //remove the car from the array list

                //print a summary

                //Notify those leaving 

                //if there is nobody on the bridge, notify leaving

                //notify cantEnter
            }
        }
    }
}
