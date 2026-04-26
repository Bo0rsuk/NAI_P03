import java.util.*;

public class Trainer {

	private NeuralNetLayer neuralNet;
	private double learningRate;
	private Map<String, List<String>> texts;
	private int trainingCycles = 100;

	public Trainer(NeuralNetLayer neuralNet, double learningRate, Map<String, List<String>> texts) {
		this.neuralNet = neuralNet;
		this.learningRate = learningRate;
		this.texts = texts;
	}

	public void learn() {
		List<Perceptron> perceptrons = neuralNet.getPerceptrons();
		List<String> languages = neuralNet.getLanguages();

		List<Map.Entry<String, String>> examples = new ArrayList<>();
		for (String language : texts.keySet()) {
			for (String text : texts.get(language)) {
				examples.add(Map.entry(language, text));
			}
		}

		Random rand = new Random();
		for (int i = 0; i < trainingCycles; i++) {
			Collections.shuffle(examples, rand);
			for (Map.Entry<String, String> example : examples) {
				String language = example.getKey();
				double[] input = TextProcessor.processText(example.getValue());

				for (int j = 0; j < perceptrons.size(); j++) {
					Perceptron perc = perceptrons.get(j);
					int d = languages.get(j).equals(language) ? 1 : 0;
					int y = perc.classify(input);

					if (d != y) {
						perc.delta(input, d, y, this.learningRate);
					}
				}
			}
		}
	}
}
