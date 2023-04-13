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
            car.setEntryTime(currentTime);

            //add the car to the bridge list
            bridge.add(car);

            //print out a car summary
            printSum();  

            //adjust time? maybe idk
            currentTime++;
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
                bridge.remove(bridge.indexOf(c));

                //print a summary
                printSum();

                //Notify those leaving 
                leaving.notifyAll();

                //if there is nobody on the bridge, switch direction
                if (bridge.size() < 1 ){
                    direction = !(direction);
                }

                //notify cantEnter
                cantEnter.notifyAll();
            }
        }
    }
    public void printSum(){
        String cars = new StringBuilder("Bridge (dir=");
        cars.append(direction);
        cars.append(") :");
        for (i = 0; i < bridge.size() - 1; i++){
            cars.append(bridge.get(i).toString());
            cars.append(", ");
            
        }
        cars.append(bridge.get(bridge.size() - 1).toString());
        cars.append("]");
        System.out.println(cars);
    }
}
