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

		// tratamento de dados
		int iterator = 0;
		for (Entry<String, Double> rule : rules.entrySet()) {
			rule.setValue(solution.getVariableValue(iterator));
			iterator++;
		}

		double[] fx = evaluate(rules);

		countFP = (int) fx[0];
		countFN = (int) fx[1];
		solution.setObjective(0, fx[0]); // objective 0 fx[0] will be subst by FP
		solution.setObjective(1, fx[1]); // objective 1 fx[1] will be subst by FN
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


	public LinkedHashMap<String, Double> getRules() {
		return rules;
	}

	public LinkedHashMap<String, ArrayList<String>> getHam() {
		return ham;
	}

	public LinkedHashMap<String, ArrayList<String>> getSpam() {
		return spam;
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
