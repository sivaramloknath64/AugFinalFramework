package Aug.Utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class retryclass implements IRetryAnalyzer {

	int i=0;
	int maxtry=2;
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		
		if(i<maxtry) {
			i++;
			return true;
		}
		return false;
	}

}
