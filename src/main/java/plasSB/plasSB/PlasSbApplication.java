package plasSB.plasSB;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import plasSB.entity.Allocation;
import plasSB.entity.Credentials;
import plasSB.proxies.HttpServiceProxy;
import plasSB.proxies.IPlasSBServiceProxy;

import java.util.List;

import org.slf4j.Logger;

@SpringBootApplication
public class PlasSbApplication {

	private final IPlasSBServiceProxy serviceProxy = new HttpServiceProxy();
	private String token;
	private String email;
	private String password;
	
	private static final Logger log = LoggerFactory.getLogger(PlasSbApplication.class);	
	
	
	public static void main(String[] args) {
		SpringApplication.run(PlasSbApplication.class, args);
	}
	
	public boolean performLogin() {
		try {
			Credentials credentials = new Credentials(email,password);
			token = serviceProxy.login(credentials);
			log.info("Login successful. Token: {}",token);
			return true;
		} catch (RuntimeException e) {
			log.error("Login failed: {}", e.getMessage());
			
			return false;
		}
	}
	
/*	public boolean loadAssignedDumpsters(LocalDate date) { //Date will be today
		try {
			List<Dumpster> dumpsters = serviceProxy.getAllDumpsters(); //Gets dumpsters assigned in allocation
			if(dumpsters == null || dumpsters.isEmpty()) {
				log.info("No dumpsters found");
				return false;
			}
			dumpsters.forEach(dumpster -> log.info("Dumpster: {}", dumpster.name()));
			return true;
		} catch (RuntimeException e) {
			log.error("Failed to load dumpsters: {}", e.getMessage());
		}
		return false;
	}
*/
	/*
	public boolean loadCapacityAndPlaceAllocation() { //Could place one or more dumpsters
		try {
			int currentCapacity = serviceProxy.getCurrentCapacity();
			
			log.info("Fetching capacity for plant: PlasSB");
			Allocation allocation = serviceProxy.getById();
			Allocation allocationDetails = loadAllocationDetails();
		
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
*/


}
