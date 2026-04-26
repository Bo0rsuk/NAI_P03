import java.io.IOException;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		try {
			Map<String, List<String>> data = new LinkedHashMap<>(DataLoader.loadData("Languages"));

			List<String> languages = new ArrayList<>(data.keySet());
			List<Perceptron> perceptrons = new ArrayList<>();

			for (String language : languages) {
				perceptrons.add(new Perceptron(26));
			}

			NeuralNetLayer neuralNet = new NeuralNetLayer(perceptrons, languages);
			Trainer pokemonTrainer = new Trainer(neuralNet, 0.01, data);
			pokemonTrainer.learn();

			Scanner scan = new Scanner(System.in);
			while (true) {
				System.out.println("Enter text: ");
				String text = scan.nextLine();
				double[] input = TextProcessor.processText(text);
//				String result = neuralNet.classifyLanguage(input);
				var probabilities = neuralNet.getLanguageProbabilities(input);
				String result = Collections.max(probabilities.entrySet(), Map.Entry.comparingByValue()).getKey();
				System.out.println(result + " | " +  probabilities);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
