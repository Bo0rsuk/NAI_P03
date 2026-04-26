import java.util.ArrayList;
import java.util.List;

public class NeuralNetLayer {

	private List<Perceptron> perceptrons;
	private List<String> languages;

	public NeuralNetLayer(List<Perceptron> perceptrons, List<String> languages) {
		if (languages.size() != perceptrons.size()) {
			throw new IllegalArgumentException("Number of languages and perceptrons do not match");
		}

		this.perceptrons = perceptrons;
		this.languages = languages;
	}

	public List<Perceptron> getPerceptrons() {
		return perceptrons;
	}

	public List<String> getLanguages() {
		return languages;
	}

	public String classifyLanguage(double[] inputs) {
		if (this.languages.size() != this.perceptrons.size()) {
			throw new IllegalArgumentException("Number of languages and perceptrons do not match");
		}

		double maxScore = Double.NEGATIVE_INFINITY;
		String language = "";
		normalizeInputs(inputs);

		for (int i = 0; i < this.perceptrons.size(); i++) {
			Perceptron perceptron = this.perceptrons.get(i);
			perceptron.normalizeWeights();
			double score = perceptron.calculateScore(inputs);
			if (score > maxScore) {
				maxScore = score;
				language = this.languages.get(i);
			}
		}
		return language;
	}

	private void normalizeInputs(double[] inputs) {
		double normalization = 0.0;
		for (double input : inputs) {
			normalization += input * input;
		}

		normalization = Math.sqrt(normalization);
		if (normalization == 0) {
			return;
		}

		for (int i = 0; i < inputs.length; i++) {
			inputs[i] /= normalization;
		}
	}
}
