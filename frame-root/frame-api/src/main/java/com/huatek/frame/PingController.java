package com.huatek.frame;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huatek.esb.msg.rpc.RpcProxy;
import com.huatek.frame.service.FwAccountService;
import com.huatek.rpc.server.module.sample.impl.HelloServiceImpl;
import com.huatek.rpc.server.module.simple.HelloService;

@RestController
@RequestMapping(value = "/api")
public class PingController{
	private static final Logger logger = Logger.getLogger(PingController.class);
	
	FwAccountService fwAccountService;
	@Autowired
	RpcProxy rpcProxy;
	@Autowired
	HelloService helloService;
    /**
     * check if the network connecting is ok.
     * @return 
     */
    @RequestMapping("/ping")
    public ResponseEntity<?> ping(HttpServletRequest request) { 
    	//long enterTime = System.nanoTime()  - LoginCheckFilter.executeTime.get();
    	//System.out.println("enter spend nontime:"+enterTime);
    	//String string = HelloServiceImpl.string;
    	//LoginCheckFilter.executeTime.set(System.nanoTime());
		/*try {
			
			response.getOutputStream().print(string+string+string+string);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	HelloService service = rpcProxy.create(HelloService.class);
    	String string= service.delay(0, "winner!");
       return new ResponseEntity(string, HttpStatus.OK);
    }
    
    @RequestMapping("/ping/{time}")
    public ResponseEntity<String> delay(@PathVariable("time") int time, HttpServletResponse response) { 
    	HelloService service = rpcProxy.create(HelloService.class);
    	String string= service.delay(time, "winner!");
       return new ResponseEntity(string, HttpStatus.OK);
    }
    
}
