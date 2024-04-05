package payloads;

import java.util.List;

public class EmployeesResponse {
    private Body body;

    // Default constructor for deserialization
    public EmployeesResponse() {
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public static class Body {
        private int totalItems;
        private List<Employee> employees;

        public int getTotalItems() {
            return totalItems;
        }

        public void setTotalItems(int totalItems) {
            this.totalItems = totalItems;
        }

        public List<Employee> getEmployees() {
            return employees;
        }

        public void setEmployees(List<Employee> employees) {
            this.employees = employees;
        }
    }

    public static class Employee {
        private String id;
        private String email;
        private String firstName;
        private String lastName;
        private String userId;
        private String onoffId;
        private String msUserId;
        private String msDisplayName;
        private List<String> plans;
        private List<String> labels;
        private List<String> departments;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getOnoffId() {
            return onoffId;
        }

        public void setOnoffId(String onoffId) {
            this.onoffId = onoffId;
        }

        public String getMsUserId() {
            return msUserId;
        }

        public void setMsUserId(String msUserId) {
            this.msUserId = msUserId;
        }

        public String getMsDisplayName() {
            return msDisplayName;
        }

        public void setMsDisplayName(String msDisplayName) {
            this.msDisplayName = msDisplayName;
        }

        public List<String> getPlans() {
            return plans;
        }

        public void setPlans(List<String> plans) {
            this.plans = plans;
        }

        public List<String> getLabels() {
            return labels;
        }

        public void setLabels(List<String> labels) {
            this.labels = labels;
        }

        public List<String> getDepartments() {
            return departments;
        }

        public void setDepartments(List<String> departments) {
            this.departments = departments;
        }
    }
}
