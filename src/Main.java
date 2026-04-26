import java.io.IOException;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		try {
			Map<String, List<String>> data = new LinkedHashMap<>(DataLoader.loadData("Languages"));

			List<String> languages = new ArrayList<>(data.keySet());
			List<Perceptron> perceptrons = new ArrayList<>();
			Random rand = new Random();

			for (String language : languages) {
				perceptrons.add(new Perceptron(26));
			}

			NeuralNetLayer neuralNet = new NeuralNetLayer(perceptrons, languages);
			Trainer pokemonTrainer = new Trainer(neuralNet, 0.01, data);
			pokemonTrainer.learn();

			Scanner scan = new Scanner(System.in);
			System.out.println("Enter text: ");
			String text = scan.nextLine();
			double[] input = TextProcessor.processText(text);
			String result = neuralNet.classifyLanguage(input);

			System.out.println(result);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
