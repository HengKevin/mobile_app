package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.HasMany;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Account type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Accounts")
@Index(name = "byUser", fields = {"userID"})
public final class Account implements Model {
  public static final QueryField ID = field("Account", "id");
  public static final QueryField ACCOUNT_TYPE = field("Account", "account_type");
  public static final QueryField BALANCE = field("Account", "balance");
  public static final QueryField USER_ID = field("Account", "userID");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String account_type;
  private final @ModelField(targetType="Float") Double balance;
  private final @ModelField(targetType="ID") String userID;
  private final @ModelField(targetType="InvestmentPla") @HasMany(associatedWith = "accountID", type = InvestmentPla.class) List<InvestmentPla> InvestmentPlas = null;
  public String getId() {
      return id;
  }
  
  public String getAccountType() {
      return account_type;
  }
  
  public Double getBalance() {
      return balance;
  }
  
  public String getUserId() {
      return userID;
  }
  
  public List<InvestmentPla> getInvestmentPlas() {
      return InvestmentPlas;
  }
  
  private Account(String id, String account_type, Double balance, String userID) {
    this.id = id;
    this.account_type = account_type;
    this.balance = balance;
    this.userID = userID;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Account account = (Account) obj;
      return ObjectsCompat.equals(getId(), account.getId()) &&
              ObjectsCompat.equals(getAccountType(), account.getAccountType()) &&
              ObjectsCompat.equals(getBalance(), account.getBalance()) &&
              ObjectsCompat.equals(getUserId(), account.getUserId());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getAccountType())
      .append(getBalance())
      .append(getUserId())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Account {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("account_type=" + String.valueOf(getAccountType()) + ", ")
      .append("balance=" + String.valueOf(getBalance()) + ", ")
      .append("userID=" + String.valueOf(getUserId()))
      .append("}")
      .toString();
  }
  
  public static AccountTypeStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   * @throws IllegalArgumentException Checks that ID is in the proper format
   */
  public static Account justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new Account(
      id,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      account_type,
      balance,
      userID);
  }
  public interface AccountTypeStep {
    BuildStep accountType(String accountType);
  }
  

  public interface BuildStep {
    Account build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep balance(Double balance);
    BuildStep userId(String userId);
  }
  

  public static class Builder implements AccountTypeStep, BuildStep {
    private String id;
    private String account_type;
    private Double balance;
    private String userID;
    @Override
     public Account build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Account(
          id,
          account_type,
          balance,
          userID);
    }
    
    @Override
     public BuildStep accountType(String accountType) {
        Objects.requireNonNull(accountType);
        this.account_type = accountType;
        return this;
    }
    
    @Override
     public BuildStep balance(Double balance) {
        this.balance = balance;
        return this;
    }
    
    @Override
     public BuildStep userId(String userId) {
        this.userID = userId;
        return this;
    }
    
    /** 
     * WARNING: Do not set ID when creating a new object. Leave this blank and one will be auto generated for you.
     * This should only be set when referring to an already existing object.
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     * @throws IllegalArgumentException Checks that ID is in the proper format
     */
    public BuildStep id(String id) throws IllegalArgumentException {
        this.id = id;
        
        try {
            UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
        } catch (Exception exception) {
          throw new IllegalArgumentException("Model IDs must be unique in the format of UUID.",
                    exception);
        }
        
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String accountType, Double balance, String userId) {
      super.id(id);
      super.accountType(accountType)
        .balance(balance)
        .userId(userId);
    }
    
    @Override
     public CopyOfBuilder accountType(String accountType) {
      return (CopyOfBuilder) super.accountType(accountType);
    }
    
    @Override
     public CopyOfBuilder balance(Double balance) {
      return (CopyOfBuilder) super.balance(balance);
    }
    
    @Override
     public CopyOfBuilder userId(String userId) {
      return (CopyOfBuilder) super.userId(userId);
    }
  }
  
}
