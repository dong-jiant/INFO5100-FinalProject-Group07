package business.workrequest;

import java.util.Date;

public abstract class WorkRequest {
    public static final String STATUS_CREATED = "Created";
    public static final String STATUS_PROCESSING = "Processing";
    public static final String STATUS_COMPLETED = "Completed";
    public static final String STATUS_REJECTED = "Rejected";

    private static int nextId = 1;

    private final String requestId;
    private final String requestType;
    private final String sourceEnterprise;
    private final String targetEnterprise;
    private final String sourceOrganization;
    private final String targetOrganization;
    private String status;
    private final String message;
    private final Date createdAt;
    private Date processedAt;
    private String processedBy;

    protected WorkRequest(
            String requestType,
            String sourceEnterprise,
            String targetEnterprise,
            String sourceOrganization,
            String targetOrganization,
            String message
    ) {
        this.requestId = "WR-" + nextId++;
        this.requestType = requestType;
        this.sourceEnterprise = sourceEnterprise;
        this.targetEnterprise = targetEnterprise;
        this.sourceOrganization = sourceOrganization;
        this.targetOrganization = targetOrganization;
        this.message = message;
        this.status = STATUS_CREATED;
        this.createdAt = new Date();
    }

    public void markProcessing(String user) {
        this.status = STATUS_PROCESSING;
        this.processedBy = user;
    }

    public void complete(String user) {
        this.status = STATUS_COMPLETED;
        this.processedBy = user;
        this.processedAt = new Date();
    }

    public void reject(String user) {
        this.status = STATUS_REJECTED;
        this.processedBy = user;
        this.processedAt = new Date();
    }

    public String getRequestId() {
        return requestId;
    }

    public String getRequestType() {
        return requestType;
    }

    public String getSourceEnterprise() {
        return sourceEnterprise;
    }

    public String getTargetEnterprise() {
        return targetEnterprise;
    }

    public String getSourceOrganization() {
        return sourceOrganization;
    }

    public String getTargetOrganization() {
        return targetOrganization;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getProcessedAt() {
        return processedAt;
    }

    public String getProcessedBy() {
        return processedBy;
    }

    public abstract String getBusinessPayload();
}
