package business.workrequest;

import java.util.ArrayList;
import java.util.List;

public class WorkRequestDirectory {
    private final List<WorkRequest> requestList;

    public WorkRequestDirectory() {
        this.requestList = new ArrayList<>();
    }

    public List<WorkRequest> getRequestList() {
        return requestList;
    }

    public void addRequest(WorkRequest workRequest) {
        requestList.add(workRequest);
    }
}
