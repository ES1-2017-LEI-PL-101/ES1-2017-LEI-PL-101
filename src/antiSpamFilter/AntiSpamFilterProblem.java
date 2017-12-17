package antiSpamFilter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;

import Tools.Debug;
import fileReader.FileLoader;

public class AntiSpamFilterProblem extends AbstractDoubleProblem {

	private static final long serialVersionUID = -8036102488474183100L;

	private LinkedHashMap<String, Double> rules;
	private LinkedHashMap<String, ArrayList<String>> ham;
	private LinkedHashMap<String, ArrayList<String>> spam;
	private int countFP = 0;
	private int countFN = 0;
	private final double algorithmLimit = 5.0;

	public AntiSpamFilterProblem() {

		rules = new LinkedHashMap<String, Double>();
		ham = new LinkedHashMap<String, ArrayList<String>>();
		spam = new LinkedHashMap<String, ArrayList<String>>();
	}

	// definimos o lower limit e upper limit
	/**
	 * Constructor. Creates a new instance of the AntiSpamFilter Problem.
	 *
	 * @param numberOfVariables
	 *            Number of variables of the problem
	 */
	public void updateAntiSpamFilterProblem(Integer numberOfVariables) {

		setNumberOfVariables(numberOfVariables);
		setNumberOfObjectives(2);// FN & FP
		setName("AntiSpamFilterProblem");

		List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables());
		List<Double> upperLimit = new ArrayList<>(getNumberOfVariables());

		for (int i = 0; i < getNumberOfVariables(); i++) {
			lowerLimit.add(-5.0);
			upperLimit.add(5.0);
		}

		setLowerLimit(lowerLimit);
		setUpperLimit(upperLimit);
	}

	public void readRules(String path) {
		rules = FileLoader.readRulesFile(path);
		updateAntiSpamFilterProblem(getRules().size());
	}

	public void readHam(String path) {
		ham = FileLoader.readHamOrSpamFile(path);
	}

	public void readSpam(String path) {
		spam = FileLoader.readHamOrSpamFile(path);
	}

	/** Evaluate() method */
	public void evaluate(DoubleSolution solution) {
		Debug.in("AntiSpamFilterProblem [evaluate(DoubleSolution)]");

		// tratamento de dados
		int iterator = 0;
		for (HashMap.Entry<String, Double> rule : rules.entrySet()) {
			rule.setValue(solution.getVariableValue(iterator));
			iterator++;
		}

		Debug.msg("Call evaluate(rules)");
		double[] fx = evaluate(rules);

		solution.setObjective(0, fx[0]); // objective 0 fx[0] will be subst by FP
		solution.setObjective(1, fx[1]); // objective 1 fx[1] will be subst by FN
		Debug.out("AntiSpamFilterProblem [evaluate(DoubleSolution)]");
	}

	public double[] evaluate(LinkedHashMap<String, Double> rules) {

		double[] fx = new double[getNumberOfObjectives()];

		fx[0] = 0.0; // FP
		for (Entry<String, ArrayList<String>> hamRule : ham.entrySet()) {
			double count = 0.0;
			for (String hamRules : hamRule.getValue()) {
				if (rules.containsKey(hamRules)) {
					count += rules.get(hamRules);
				}
			}
			if (count > algorithmLimit) {
				fx[0]++;
			}

		}

		fx[1] = 0.0; // FN
		for (Entry<String, ArrayList<String>> spamRule : spam.entrySet()) {
			double count = 0.0;
			for (String spamRules : spamRule.getValue()) {
				if (rules.containsKey(spamRules)) {
					count += rules.get(spamRules);
				}
			}
			if (count < algorithmLimit) {
				fx[1]++;
			}

		}

		return fx;
	}
	// public double[] evaluate(LinkedHashMap<String, Double> rules) {
	//
	// double[] fx = new double[getNumberOfObjectives()];
	// // TODO HAM
	// // check if is FN or FP and countTotal -> send count to setObjective
	// // calcule FN e FP to auth and manual configuration
	// fx[0] = 0.0; // FP
	// for (Entry<String, ArrayList<String>> hamRule : ham.entrySet()) {
	// if (rules.containsKey(hamRule.getKey())) {
	// fx[0] += rules.get(hamRule.getKey());
	// }
	// }
	// Debug.msg("FX [0] = " + fx[0]);
	//
	// // TODO SPAM
	// fx[1] = 0.0; // FN
	// for (Entry<String, ArrayList<String>> spamRule : spam.entrySet()) {
	// if (rules.containsKey(spamRule.getKey())) {
	// fx[1] += rules.get(spamRule.getKey());
	// }
	// }
	// Debug.msg("FX [1] = " + fx[1]);
	//
	// // verificar valor de Status
	// double Status = (fx[0] > algorithmLimit ? countFP++ : (fx[1] < algorithmLimit
	// ? countFN++ : null));
	// Debug.msg("Status =[" + Status + "]");
	// fx[0] = countFP;
	// fx[1] = countFN;
	// return fx;
	// }

	public LinkedHashMap<String, Double> getRules() {
		return rules;
	}

	public LinkedHashMap<String, ArrayList<String>> getHam() {
		return ham;
	}

	public LinkedHashMap<String, ArrayList<String>> getSpam() {
		return spam;
	}

	// Manual calculation of FN and FP
	public int[] getFN_FP() {
		int[] result = new int[2];

		return result;
	}

	public boolean validLists() {
		return !rules.isEmpty() && !ham.isEmpty() && !spam.isEmpty();
	}

	/** This method is used to set new rules in rules map.
	 * 
	 * @param newRules
	 */
	public void setRules(LinkedHashMap<String, Double> newRules) {
		this.rules = newRules;

	}

	public int getCountFN() {
		// TODO Auto-generated method stub
		return countFN;
	}

	public int getCountFP() {
		return countFP;
	}
	
	
}
