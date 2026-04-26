import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public Map<String, Double> getLanguageProbabilities(double[] inputs) {
		if (this.languages.size() != perceptrons.size()) {
			throw new IllegalArgumentException("Number of languages and perceptrons do not match");
		}

		double maxScore = Double.NEGATIVE_INFINITY;
		Map<String, Double> results = new HashMap<String, Double>();
		normalizeInputs(inputs);
		for (int i = 0; i < this.perceptrons.size(); i++) {
			Perceptron perceptron = this.perceptrons.get(i);
			perceptron.normalizeWeights();
			double score = perceptron.calculateScore(inputs);
			results.put(this.languages.get(i), score);
		}
		return results;
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
