import com.boomi.execution.ExecutionUtil
import java.util.Properties

for (int i = 0; i < dataContext.getDataCount(); i++) {
    InputStream is = dataContext.getStream(i)
    Properties props = dataContext.getProperties(i)



	//logic start
	
	
	//get dpp.kip.bendex.statuses_for_call value from the flow
	def statusesForCall = ExecutionUtil.getDynamicProcessProperty("dpp.kip.bendex.statuses_for_call")
	def status_new = ExecutionUtil.getDynamicProcessProperty("dpp.kip.bendex.status_new")
	def status_pending = ExecutionUtil.getDynamicProcessProperty("dpp.kip.bendex.status_pending")
	def status_accepted = ExecutionUtil.getDynamicProcessProperty("dpp.kip.bendex.status_accepted")
    def status_processing = ExecutionUtil.getDynamicProcessProperty("ddp.kip.bendex.status_processing")
    def status_pending = ExecutionUtil.getDynamicProcessProperty("dpp.kip.bendex.status_pending")

    
    

    
	//split dpp.kip.bendex.statuses_for_call string to make an array
    def statuses = statusesForCall?.split(",")*.trim()
	
	//define empty "result" variable
    def result = new StringBuilder()
    
	//a loop to add "new","pending" or "accepted" if presented to "result" string
    statuses.each { status ->
    	switch(status) {
    		case "new":
    		    status = status_new
    		    result.append(status).append(",")
    		    break
    		case "pending":
    		    status = status_pending
    		    result.append(status).append(",")
    		    break
    		case "accepted":
    		    status = status_accepted
    			result.append(status).append(",")
            case "processing":
                status = status_processing
                result.append(status).append(",")
    			break
    	}
    }

	//trim the "," symbol in the end of the "result" string
    if (result.length() > 0) {
    	result.setLength(result.length() - 1)
	}

	// append "?Profile=full&status=" to "result" string
	result="?Profile=full&status="+result

	// set dpp.kip.jb.status_string = "result" string
    ExecutionUtil.setDynamicProcessProperty("dpp.kip.jb.status_string", result?.toString(), false)
	
	
	
    //logic end
    
    
    
    dataContext.storeStream(is, props)
}