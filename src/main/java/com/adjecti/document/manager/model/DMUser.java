package com.adjecti.document.manager.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"user\"")
public class DMUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private long id;
	private long companyId;
	private Date createDate;
	private boolean defaultUser;
	private long contactId;
	private boolean passwordEncrypted;
	private boolean passwordReset;
	private Date passwordModifiedDate;
	private String digest;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id") 
	private List<DMFile> dmFile;
	
	private String reminderQueryQuestion;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id") 
	private List<DMFolder> dmFolder;
	
	private String reminderQueryAnswer;
	private int graceLoginCount;
	
	@Column(name = "username", unique = true)
	private String userName;
	
	private String emailAddress;
	private long facebookId;
	private String googleUserId;
	private long ldapserverId;
	private String openId;
	private String languageId;
	private String timeZoneId;
	private String greeting;
	private String comments;
	private String firstName;
	private String middleName;
	private String lastName;
	private String jobTitle;
	private Date loginDate;
	private String loginIp;
	private Date lastLoginDate;
	private String lastLoginIp;
	private Date lastFailedLoginDate;
	private int failedLoginAttempts;
	private boolean lockout;
	private Date lockoutDate;
	private String agreedToTermsOfUse;
	private boolean emailAddressVerified;
	private int status;
	private String externalReferenceCode;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public boolean isDefaultUser() {
		return defaultUser;
	}
	public void setDefaultUser(boolean defaultUser) {
		this.defaultUser = defaultUser;
	}
	public long getContactId() {
		return contactId;
	}
	public void setContactId(long contactId) {
		this.contactId = contactId;
	}
	public boolean isPasswordEncrypted() {
		return passwordEncrypted;
	}
	public void setPasswordEncrypted(boolean passwordEncrypted) {
		this.passwordEncrypted = passwordEncrypted;
	}
	public boolean isPasswordReset() {
		return passwordReset;
	}
	public void setPasswordReset(boolean passwordReset) {
		this.passwordReset = passwordReset;
	}
	public Date getPasswordModifiedDate() {
		return passwordModifiedDate;
	}
	public void setPasswordModifiedDate(Date passwordModifiedDate) {
		this.passwordModifiedDate = passwordModifiedDate;
	}
	public String getDigest() {
		return digest;
	}
	public void setDigest(String digest) {
		this.digest = digest;
	}
	public List<DMFile> getDmFile() {
		return dmFile;
	}
	public void setDmFile(List<DMFile> dmFile) {
		this.dmFile = dmFile;
	}
	public String getReminderQueryQuestion() {
		return reminderQueryQuestion;
	}
	public void setReminderQueryQuestion(String reminderQueryQuestion) {
		this.reminderQueryQuestion = reminderQueryQuestion;
	}
	public List<DMFolder> getDmFolder() {
		return dmFolder;
	}
	public void setDmFolder(List<DMFolder> dmFolder) {
		this.dmFolder = dmFolder;
	}
	public String getReminderQueryAnswer() {
		return reminderQueryAnswer;
	}
	public void setReminderQueryAnswer(String reminderQueryAnswer) {
		this.reminderQueryAnswer = reminderQueryAnswer;
	}
	public int getGraceLoginCount() {
		return graceLoginCount;
	}
	public void setGraceLoginCount(int graceLoginCount) {
		this.graceLoginCount = graceLoginCount;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public long getFacebookId() {
		return facebookId;
	}
	public void setFacebookId(long facebookId) {
		this.facebookId = facebookId;
	}
	public String getGoogleUserId() {
		return googleUserId;
	}
	public void setGoogleUserId(String googleUserId) {
		this.googleUserId = googleUserId;
	}
	public long getLdapserverId() {
		return ldapserverId;
	}
	public void setLdapserverId(long ldapserverId) {
		this.ldapserverId = ldapserverId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getLanguageId() {
		return languageId;
	}
	public void setLanguageId(String languageId) {
		this.languageId = languageId;
	}
	public String getTimeZoneId() {
		return timeZoneId;
	}
	public void setTimeZoneId(String timeZoneId) {
		this.timeZoneId = timeZoneId;
	}
	public String getGreeting() {
		return greeting;
	}
	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	public Date getLastFailedLoginDate() {
		return lastFailedLoginDate;
	}
	public void setLastFailedLoginDate(Date lastFailedLoginDate) {
		this.lastFailedLoginDate = lastFailedLoginDate;
	}
	public int getFailedLoginAttempts() {
		return failedLoginAttempts;
	}
	public void setFailedLoginAttempts(int failedLoginAttempts) {
		this.failedLoginAttempts = failedLoginAttempts;
	}
	public boolean isLockout() {
		return lockout;
	}
	public void setLockout(boolean lockout) {
		this.lockout = lockout;
	}
	public Date getLockoutDate() {
		return lockoutDate;
	}
	public void setLockoutDate(Date lockoutDate) {
		this.lockoutDate = lockoutDate;
	}
	public String getAgreedToTermsOfUse() {
		return agreedToTermsOfUse;
	}
	public void setAgreedToTermsOfUse(String agreedToTermsOfUse) {
		this.agreedToTermsOfUse = agreedToTermsOfUse;
	}
	public boolean isEmailAddressVerified() {
		return emailAddressVerified;
	}
	public void setEmailAddressVerified(boolean emailAddressVerified) {
		this.emailAddressVerified = emailAddressVerified;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getExternalReferenceCode() {
		return externalReferenceCode;
	}
	public void setExternalReferenceCode(String externalReferenceCode) {
		this.externalReferenceCode = externalReferenceCode;
	}
	public DMUser(long id, long companyId, Date createDate, boolean defaultUser, long contactId, String password_,
			boolean passwordEncrypted, boolean passwordReset, Date passwordModifiedDate, String digest,
			List<DMFile> dmFile, String reminderQueryQuestion, List<DMFolder> dmFolder, String reminderQueryAnswer,
			int graceLoginCount, String userName, String emailAddress, long facebookId, String googleUserId,
			long ldapserverId, String openId, String languageId, String timeZoneId, String greeting, String comments,
			String firstName, String middleName, String lastName, String jobTitle, Date loginDate, String loginIp,
			Date lastLoginDate, String lastLoginIp, Date lastFailedLoginDate, int failedLoginAttempts, boolean lockout,
			Date lockoutDate, String agreedToTermsOfUse, boolean emailAddressVerified, int status,
			String externalReferenceCode) {
		super();
		this.id = id;
		this.companyId = companyId;
		this.createDate = createDate;
		this.defaultUser = defaultUser;
		this.contactId = contactId;
		this.passwordEncrypted = passwordEncrypted;
		this.passwordReset = passwordReset;
		this.passwordModifiedDate = passwordModifiedDate;
		this.digest = digest;
		this.dmFile = dmFile;
		this.reminderQueryQuestion = reminderQueryQuestion;
		this.dmFolder = dmFolder;
		this.reminderQueryAnswer = reminderQueryAnswer;
		this.graceLoginCount = graceLoginCount;
		this.userName = userName;
		this.emailAddress = emailAddress;
		this.facebookId = facebookId;
		this.googleUserId = googleUserId;
		this.ldapserverId = ldapserverId;
		this.openId = openId;
		this.languageId = languageId;
		this.timeZoneId = timeZoneId;
		this.greeting = greeting;
		this.comments = comments;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.jobTitle = jobTitle;
		this.loginDate = loginDate;
		this.loginIp = loginIp;
		this.lastLoginDate = lastLoginDate;
		this.lastLoginIp = lastLoginIp;
		this.lastFailedLoginDate = lastFailedLoginDate;
		this.failedLoginAttempts = failedLoginAttempts;
		this.lockout = lockout;
		this.lockoutDate = lockoutDate;
		this.agreedToTermsOfUse = agreedToTermsOfUse;
		this.emailAddressVerified = emailAddressVerified;
		this.status = status;
		this.externalReferenceCode = externalReferenceCode;
	}
	public DMUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
