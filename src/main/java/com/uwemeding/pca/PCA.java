package com.uwemeding.pca;

/**
 *
 * @author uwe
 */
public class PCA {

	// The incoming matrix
	private final Matrix m;
	// the principal components
	private final Matrix pc;
	// facpr
	private final Matrix facpr;
	// lambda
	private final Matrix lambda;

	public PCA(Matrix x) {

		// Weight and center the matrix
		this.m = x.wcenter();
		// compute the eigenvectors of y'*y using svd
		SVD svd = new SVD(this.m);

		// calculate the lambda
		this.lambda = calculateLambda(svd.getS());
		// get the principle factors
		this.facpr = svd.getV();

		// calculate the principle components
		this.pc = this.m.times(svd.getV());
	}

	private Matrix calculateLambda(Matrix s) {

		Matrix d = s.diag();
		double[][] D = d.getArray();

		int size = d.getNRows();
		for (int i = 0; i < size; i++) {
			D[i][0] = (D[i][0] * D[i][0]) / (size - 1);
		}

		return d;
	}

	private void log(String text, Matrix m) {
		System.out.println(text + ":");
		m.print(1, 4);
	}

	public Matrix getPrincipalComponents() {
		return pc;
	}

	public Matrix getLambda() {
		return lambda;
	}

	public Matrix getPrinicipalFactors() {
		return facpr;
	}

}
