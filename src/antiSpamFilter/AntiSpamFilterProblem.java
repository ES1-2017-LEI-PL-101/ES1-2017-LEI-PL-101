package antiSpamFilter;

import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;

import Tools.Debug;

public class AntiSpamFilterProblem extends AbstractDoubleProblem {

	  public AntiSpamFilterProblem() {
	    // 10 variables (anti-spam filter rules) by default 
	    this(10);
	    Debug.getInstance().IN("AntiSpamFilterProblem [Constructor]");
	    Debug.getInstance().OUT("AntiSpamFilterProblem [Constructor]");
	  }

	  public AntiSpamFilterProblem(Integer numberOfVariables) {
		Debug.getInstance().IN("AntiSpamFilterProblem [Constructor(INTEGER)]");
	    setNumberOfVariables(numberOfVariables);
	    setNumberOfObjectives(2);
	    setName("AntiSpamFilterProblem");

	    List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables()) ;
	    List<Double> upperLimit = new ArrayList<>(getNumberOfVariables()) ;
	    //Debug.getInstance().msg("lowerLimit [Before]: "+lowerLimit.toString());
	    //Debug.getInstance().msg("upperLimit [Before]: "+lowerLimit.toString());
	    for (int i = 0; i < getNumberOfVariables(); i++) {
	      lowerLimit.add(-5.0);
	      upperLimit.add(5.0);
	    }
	    //Debug.getInstance().msg("lowerLimit [After]: "+lowerLimit.toString());
	    //Debug.getInstance().msg("upperLimit [After]: "+lowerLimit.toString());
	    setLowerLimit(lowerLimit);
	    setUpperLimit(upperLimit);
	    Debug.getInstance().OUT("AntiSpamFilterProblem [Constructor(INTEGER)]");
	  }

	  public void evaluate(DoubleSolution solution){
		Debug.getInstance().IN("AntiSpamFilterProblem [Constructor(INTEGER)]");
	    double aux, xi, xj;
	    double[] fx = new double[getNumberOfObjectives()];
	    double[] x = new double[getNumberOfVariables()];
	    for (int i = 0; i < solution.getNumberOfVariables(); i++) {
	      x[i] = solution.getVariableValue(i) ;
	      //Debug.getInstance().msg("x : "+x[i]);
	    }
	    
	    fx[0] = 0.0;
	    for (int var = 0; var < solution.getNumberOfVariables() - 1; var++) {
		  fx[0] += Math.abs(x[0]); // Example for testing
	    }
	    //Debug.getInstance().msg("fx[0] : "+fx[0]);
	    fx[1] = 0.0;
	    for (int var = 0; var < solution.getNumberOfVariables(); var++) {
	    	fx[1] += Math.abs(x[1]); // Example for testing
	    }
	    //Debug.getInstance().msg("fx[1] : "+fx[1]);

	    solution.setObjective(0, fx[0]);
	    solution.setObjective(1, fx[1]);
	    Debug.getInstance().OUT("AntiSpamFilterProblem [Constructor(INTEGER)]");
	  }
	}
