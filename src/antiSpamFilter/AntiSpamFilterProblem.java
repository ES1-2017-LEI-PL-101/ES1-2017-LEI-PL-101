package antiSpamFilter;

import java.util.ArrayList;
import java.util.List;
import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;

import Tools.Debug;

public class AntiSpamFilterProblem extends AbstractDoubleProblem {
	
	private static final long serialVersionUID = -8036102488474183100L;
	private String [] rulesDummy = {"BAYES_05",
			"SPF_FAIL",
			"SUBJECT_NEEDS_ENCODING",
						};
	private String [] hamDummy = {"BAYES_00","HTML_FONT_SIZE_LARGE",	"HTML_MESSAGE" ,	"MIME_HTML_ONLY",	"SPF_FAIL"};
	private String [] SpamDummy = {"BAYES_99","BUG6152_INVALID_DATE_TZ_ABSURD","DATE_IN_PAST_06_12","EXCUSE_4"};
	
	public AntiSpamFilterProblem() {
	    // 10 variables (anti-spam filter rules) by default 
		//TODO getNumberOfRules
		this(10);  
	    
		
	    //TODO Create GetPath's to Rules, Ham, Spam 
	    Debug.getInstance();
		Debug.IN("AntiSpamFilterProblem [Constructor]");
	    Debug.OUT("AntiSpamFilterProblem [Constructor]");
	  }

	  
	  //definimos o lower limit e upper limit
	  /**
	   * Constructor.
	   * Creates a new instance of the AntiSpamFilter Problem.
	   *
	   * @param numberOfVariables Number of variables of the problem
	   */
	  public AntiSpamFilterProblem(Integer numberOfVariables) {
		Debug.IN("AntiSpamFilterProblem [Constructor(INTEGER)]");
		
		//TODO
		//Get rules, ham , spam   
		
		
	    setNumberOfVariables(numberOfVariables);  
	    setNumberOfObjectives(2);// FN & FP 
	    setName("AntiSpamFilterProblem");

	    List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables()) ;
	    List<Double> upperLimit = new ArrayList<>(getNumberOfVariables()) ;
	    
	    for (int i = 0; i < getNumberOfVariables(); i++) {
	      lowerLimit.add(-5.0);
	      upperLimit.add(5.0);
	    }
	    
	    setLowerLimit(lowerLimit);
	    setUpperLimit(upperLimit);
	    
	    Debug.OUT("AntiSpamFilterProblem [Constructor(INTEGER)]");
	  }

	  //ver metodo
	  /** Evaluate() method */
	  public void evaluate(DoubleSolution solution){
		Debug.IN("AntiSpamFilterProblem [evaluate(DoubleSolution)]");
		double aux, xi, xj;
		
		double[] fx = new double[getNumberOfObjectives()];
	    double[] x = new double[getNumberOfVariables()];
	    String msg = "";
	    
	    for (int i = 0; i < solution.getNumberOfVariables(); i++) {
	      x[i] = solution.getVariableValue(i) ;
	      Debug.msg("x : "+x[i]);
	    }
	    
	    
	    //TODO HAM
	    fx[0] = 0.0; //FP
	    for (String result : hamDummy){
	    	//if relesDummy Contains result sum 
	    	//create list's
	    	for(int i=0; i!=rulesDummy.length; i++){
	    		fx[0] += x[i];
	    	}
	    }
	    
	   /*
	    for (int var = 0; var < solution.getNumberOfVariables() - 1; var++) {
	      xi = x[var] * x[var];
	      xj = x[var + 1] * x[var + 1];
	      aux = (-0.2) * Math.sqrt(xi + xj);
	      fx[0] += (-10.0) * Math.exp(aux);
	    }
	    	Debug.msg("FX [0] = "+ fx[0]);
	    */
	    
	    
	    
	    
	    //TODO SPAM
	    fx[1] = 0.0; //FN
	    /*
	    for (int var = 0; var < solution.getNumberOfVariables(); var++) {
	      fx[1] += Math.pow(Math.abs(x[var]), 0.8) +
	        5.0 * Math.sin(Math.pow(x[var], 3.0));
	    }
	    	Debug.msg("FX [1] = "+ fx[1]);
	   */
	    
	    
	    solution.setObjective(0, fx[0]); //objective 0
	    solution.setObjective(1, fx[1]); //objective 1
	    Debug.OUT("AntiSpamFilterProblem [evaluate(DoubleSolution)]");
	  }
	  
	  
	  
	}
