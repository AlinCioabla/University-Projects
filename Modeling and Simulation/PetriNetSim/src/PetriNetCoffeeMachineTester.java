
public class PetriNetCoffeeMachineTester {

	public static void main(String[] args) {

		ProducerConsumer pc = new ProducerConsumer(3, 2, 5);

		pc.run();

	}

}
