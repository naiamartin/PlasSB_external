package plasSB.dto;

public class AssignmentRequestDTO {
    private int dumpsterCount;
    private int packageCount;
    private String assignedBy;
    
    // Constructor vacío
    public AssignmentRequestDTO() {}
    
    // Constructor con parámetros
    public AssignmentRequestDTO(int dumpsterCount, int packageCount, String assignedBy) {
        this.dumpsterCount = dumpsterCount;
        this.packageCount = packageCount;
        this.assignedBy = assignedBy;
    }
    
    // Getters y Setters
    public int getDumpsterCount() { return dumpsterCount; }
    public void setDumpsterCount(int dumpsterCount) { this.dumpsterCount = dumpsterCount; }
    
    public int getPackageCount() { return packageCount; }
    public void setPackageCount(int packageCount) { this.packageCount = packageCount; }
    
    public String getAssignedBy() { return assignedBy; }
    public void setAssignedBy(String assignedBy) { this.assignedBy = assignedBy; }
    
    @Override
    public String toString() {
        return String.format("AssignmentRequestDTO{dumpsters=%d, packages=%d, assignedBy='%s'}", 
            dumpsterCount, packageCount, assignedBy);
    }
}
