
/**
 * Runs all threads
 */
public class BridgeRunner {

	public static void main(String[] args) {

		if (args.length < 3) {
			System.out.println("Not enough arguments, Usage: BridgeRunner <bridge limit> <num cars>");
		} else {
			if (Integer.parseInt(args[0]) > 0 && Integer.parseInt(args[1]) > 0) {
				System.out.println("Error: bridge limit and/or num cars must be positive.");
			} else {
				//Reading in inputs from command line
				int bridgeLimit = Integer.parseInt(args[0]);
				int numCars = Integer.parseInt(args[1]);
				OneLaneBridge bridge = new OneLaneBridge(bridgeLimit);

				//Create the array of threads
				Thread[] threads = new Thread[numCars];
				
				//start all the threads
				for (int i = 0; i < numCars; i++) {
					threads[i] = new Thread(new Car(i, bridge));
					threads[i].start();
				}
				//Attempt to join all the threads
				for (int i = 0; i < numCars; i++) {
					try {
						threads[i].join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("All cars have crossed!!");
			}
		}
	}
}
