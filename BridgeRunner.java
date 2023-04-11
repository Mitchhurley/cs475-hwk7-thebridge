/**
 * Runs all threads
 */
public class BridgeRunner {

	public static void main(String[] args) {

		// TODO - check command line inputs
		if (args.length < 3) {
			System.out.println("Not enough arguments, Usage: BridgeRunner <bridge limit> <num cars>");
			// TODO throw exception and give usage
		} else {
			if (Integer.parseInt(args[0]) > 0 && Integer.parseInt(args[1]) > 0) {
				System.out.println("Num Cars and Bridge Limit have to be a positive number, Usage: BridgeRunner <bridge limit> <num cars>");
			} else {
				int bridgeLimit = Integer.parseInt(args[0]);
				int numCars = Integer.parseInt(args[1]);
				OneLaneBridge bridge = new OneLaneBridge(bridgeLimit);
				// TODO - instantiate the bridge

				Thread[] threads = new Thread[numCars];

				for (int i = 0; i < numCars; i++) {
					threads[i] = new Thread(new Car(i, bridge));
					threads[i].start();
				}
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
