package enterprise.elroi.services.DashboardServiceInterface;

import enterprise.elroi.dto.responses.TransactionResponses;
import java.util.List;
import java.util.Map;

public interface DashboardServiceInterface {

    Map<String, Object> getDashboardStats(String userId);
    List<TransactionResponses> getRecentActivity(String userId);
}