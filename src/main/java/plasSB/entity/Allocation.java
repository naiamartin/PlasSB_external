package plasSB.entity;

public record Allocation(
	Long id,
	Integer dumpsterNumber,
	Integer containerNumber,
	double totalWeight
) {}
