package antiSpamFilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;

import Tools.Debug;
import fileReader.FileLoader;

public class AntiSpamFilterProblem extends AbstractDoubleProblem {
	
	private static final long serialVersionUID = -8036102488474183100L;
	
	private HashMap <String,Double> Rules = FileLoader.getInstance().getRulesMap();
	private HashMap<String, ArrayList<String>> Ham = FileLoader.getInstance().getHamRulesMap();
	private HashMap<String, ArrayList<String>> Spam = FileLoader.getInstance().getSpamRulesMap();
	
	
	public AntiSpamFilterProblem() {
	    // 10 variables (anti-spam filter rules) by default 
		//TODO getNumberOfRules file rules
		this(FileLoader.getInstance().getRulesMap().size());  
	   
	    //TODO Create GetPath's to Rules, Ham, Spam of GUI
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
	    
	    
//	    for (int i = 0; i < solution.getNumberOfVariables(); i++) {
//	      x[i] = solution.getVariableValue(i) ;
//	      Debug.msg("x : "+x[i]);
//	    }
	    //verificar ordfem do hashmaplinked -> sort by key order
	    int iterator = 0;
	    for (HashMap.Entry<String, Double> rule : Rules.entrySet()){
			rule.setValue(solution.getVariableValue(iterator));
	    	iterator++;
	    }
	    
	    
	    //TODO HAM 
	    //check if is FN or FP and countTotal -> send count to setObjective
	    // calcule FN e FP to auth and manual configuration
	    fx[0] = 0.0; //FP
	    for (Entry<String, ArrayList<String>> hamRule : Ham.entrySet()){
	    	if(Rules.containsKey(hamRule.getKey())){
	    		fx[0] += Rules.get(hamRule.getKey());
	    	}
	    }
	    Debug.msg("FX [0] = "+ fx[0]);
	    
	    //TODO SPAM
	    fx[1] = 0.0; //FN
	    for (Entry<String, ArrayList<String>> spamRule : Spam.entrySet()){
	    	if(Rules.containsKey(spamRule.getKey())){
	    		fx[1] += Rules.get(spamRule.getKey());
	    	}
	    }
	    Debug.msg("FX [1] = "+ fx[1]);
	    
	    
	    solution.setObjective(0, fx[0]); //objective 0 fx[0] will be subst by FN
	    solution.setObjective(1, fx[1]); //objective 1 fx[1] will be subst by FP
	    Debug.OUT("AntiSpamFilterProblem [evaluate(DoubleSolution)]");
	  }
	  
	  
	  //Este petodo existe apenas para efeitos de teste antes da finalização do fileReader
	  private void insertDummyValues(){
		  Rules.put("BAYES_05", -1.0);
		  Rules.put("SPF_FAIL", -1.0);
		  Rules.put("SUBJECT_NEEDS_ENCODING", -1.0);
	  }

	  
	}
