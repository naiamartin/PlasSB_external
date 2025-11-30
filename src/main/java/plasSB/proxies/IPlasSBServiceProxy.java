package plasSB.proxies;

import java.util.List;

import plasSB.entity.Allocation;
import plasSB.entity.Credentials;

public interface IPlasSBServiceProxy {
	String login(Credentials credentials);
	void logout(String token);
	
	List<Allocation> getAllAllocations();
	Allocation getAllocationDetails(Long allocationId);
	void makeAllocation(Long allocationId, Integer dumpsterNumber, Integer containerNumber, double totalWeight, String token);
	int getCurrentCapacity();
	Allocation getById();
	
}
