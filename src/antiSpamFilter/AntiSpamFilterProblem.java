package antiSpamFilter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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

	/**
	 * Constructor. Creates a HashMap of rules, ham and spam.
	 * 
	 */
	public AntiSpamFilterProblem() {
		this.rules = new LinkedHashMap<String, Double>();
		this.ham = new LinkedHashMap<String, ArrayList<String>>();
		this.spam = new LinkedHashMap<String, ArrayList<String>>();
	}

	// definimos o lower limit e upper limit
	/**
	 * Constructor. Creates a new instance of the AntiSpamFilter Problem.
	 *
	 * @param numberOfVariables
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

	/**
	 * This method is used to read rules. 
	 * 
	 * @param path
	 */
	public void readRules(String path) {
		rules = FileLoader.readRulesFile(path);
		updateAntiSpamFilterProblem(getRules().size());
	}

	/**
	 * This method is used to read ham.
	 * 
	 * @param path
	 */
	public void readHam(String path) {
		ham = FileLoader.readHamOrSpamFile(path);
	}

	/**
	 * This method is used to read spam.
	 * 
	 * @param path
	 */
	public void readSpam(String path) {
		spam = FileLoader.readHamOrSpamFile(path);
	}

	
	/**
	 * 
	 *
	 * @param solution
	 */
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

	/**
	 * 
	 * 
	 * @param rules
	 * @return Double
	 */
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

	/**
	 * 
	 * 
	 * @return
	 */
	public LinkedHashMap<String, Double> getRules() {
		return rules;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public LinkedHashMap<String, ArrayList<String>> getHam() {
		return ham;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public LinkedHashMap<String, ArrayList<String>> getSpam() {
		return spam;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public boolean validLists() {
		return !rules.isEmpty() && !ham.isEmpty() && !spam.isEmpty();
	}

	/**
	 * This method is used to set new rules in rules map.
	 * 
	 * @param newRules
	 */
	public void setRules(LinkedHashMap<String, Double> newRules) {
		this.rules = newRules;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public int getCountFN() {
		return countFN;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public int getCountFP() {
		return countFP;
	}

}
