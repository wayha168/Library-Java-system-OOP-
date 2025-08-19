public class Admin extends User {
    // private String adminId;
    // private String adminPassword;

    // public Admin(String name, String email, String phoneNumber, String adminId, String adminPassword) {
    //     super(name, email, phoneNumber);
    //     this.adminId = adminId;
    //     this.adminPassword = adminPassword;
    // }

    // public String getAdminId() {
    //     return adminId;
    // }

    // public void setAdminId(String adminId) {
    //     this.adminId = adminId;
    // }

    // public String getAdminPassword() {
    //     return adminPassword;
    // }

    // public void setAdminPassword(String adminPassword) {
    //     this.adminPassword = adminPassword;
    // }
    public Admin(String name) {
        super(name);
        // Default constructor
    }
    public Admin(String name, String email, String phoneNumber) {
        super(name, email, phoneNumber);
    }
}
