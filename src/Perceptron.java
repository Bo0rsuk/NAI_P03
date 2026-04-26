

public class Perceptron {

	private double[] weights;
	private double bias;

	public Perceptron(double[] weights, double bias) {
		this.weights = weights;
		this.bias = bias;
	}

	public Perceptron(int inputVectorSize) {
		this.weights = new double[inputVectorSize];

		this.bias = 0.0;
		for (int i = 0; i < inputVectorSize; i++) {
			this.weights[i] = Math.random() - 0.5;
		}
	}

	public void delta(double[] inputs, int d, int y, double learningRate) {
		int diff = d - y;
		double updateFactor = learningRate * diff;

		for (int i = 0; i < inputs.length; i++) {
			this.weights[i] += inputs[i] * updateFactor;
		}

		this.bias -= updateFactor;
	}

	public int classify(double[] inputs) {
		double NET = dotProduct(inputs, this.weights) - this.bias;
		return NET < 0 ? 0 : 1;
	}

	public double calculateScore(double[] inputs) {
		double result = 0.0;
		for (int i = 0; i < inputs.length; i++) {
			result += inputs[i] * this.weights[i];
		}
		return result;
	}

	private static double dotProduct(double[] v1, double[] v2) {
		if (v1.length != v2.length) {
			throw new IllegalArgumentException("Długość ma znaczenie!");
		}
		double sum = 0.0;
		for (int i = 0; i < v1.length; i++) {
			sum += v1[i] * v2[i];
		}
		return sum;
	}

	public void normalizeWeights() {
		double normalization = 0.0;
		for (double weight : this.weights) {
			normalization += weight * weight;
		}

		normalization = Math.sqrt(normalization);
		if (normalization == 0) {
			return;
		}

		for (int i = 0; i < this.weights.length; i++) {
			this.weights[i] /= normalization;
		}
	}
}
