package gov.va.med.pharmacy.peps.common.vo;


/**
 * Drug data vendor version information.
 */
public class DrugDataVendorVersionVo extends ValueObject {
    private static final long serialVersionUID = 1L;

    private String buildVersion = "";
    private String dataVersion = "";
    private String issueDate = "";
    private String customBuildVersion = "";
    private String customDataVersion = "";
    private String customIssueDate = "";

    /**
     * Returns the Build Version.
     * 
     * @return buildVersion property
     */
    public final String getBuildVersion() {
        return buildVersion;
    }

    /**
     * Sets the Build Version.
     * 
     * @param buildVersion buildVersion property
     */
    public final void setBuildVersion(String buildVersion) {
        this.buildVersion = buildVersion;
    }

    /**
     * Returns the Data Version.
     * 
     * @return dataVersion property
     */
    public final String getDataVersion() {
        return dataVersion;
    }

    /**
     * Sets the Data Version.
     * 
     * @param dataVersion dataVersion property
     */
    public final void setDataVersion(String dataVersion) {
        this.dataVersion = dataVersion;
    }

    /**
     * Returns the Issue Date.
     * 
     * @return issueDate property
     */
    public final String getIssueDate() {
        return issueDate;
    }

    /**
     * Sets the Issue Date.
     * 
     * @param issueDate issueDate property
     */
    public final void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    /**
     * Returns the Custom Build Version.
     * 
     * @return customBuildVersion property
     */
    public final String getCustomBuildVersion() {
        return customBuildVersion;
    }

    /**
     * Sets the Custom Build Version.
     * 
     * @param customBuildVersion customBuildVersion property
     */
    public final void setCustomBuildVersion(String customBuildVersion) {
        this.customBuildVersion = customBuildVersion;
    }

    /**
     * Returns the Custom Data Version.
     * 
     * @return customDataVersion property
     */
    public final String getCustomDataVersion() {
        return customDataVersion;
    }

    /**
     * Sets the Custom Data Version.
     * 
     * @param customDataVersion customDataVersion property
     */
    public final void setCustomDataVersion(String customDataVersion) {
        this.customDataVersion = customDataVersion;
    }

    /**
     * Returns the Custom Issue Date.
     * 
     * @return customIssueDate property
     */
    public final String getCustomIssueDate() {
        return customIssueDate;
    }

    /**
     * Sets the Custom Issue Date.
     * 
     * @param customIssueDate customIssueDate property
     */
    public final void setCustomIssueDate(String customIssueDate) {
        this.customIssueDate = customIssueDate;
    }
}