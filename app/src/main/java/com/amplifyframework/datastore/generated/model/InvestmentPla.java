package com.amplifyframework.datastore.generated.model;


import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelOperation;
import com.amplifyframework.core.model.annotations.AuthRule;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the InvestmentPla type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "InvestmentPlas", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
@Index(name = "byAccount", fields = {"accountID"})
public final class InvestmentPla implements Model {
  public static final QueryField ID = field("InvestmentPla", "id");
  public static final QueryField PLAN_NAME = field("InvestmentPla", "plan_name");
  public static final QueryField DESCRIPTION = field("InvestmentPla", "description");
  public static final QueryField DEPOSIT = field("InvestmentPla", "deposit");
  public static final QueryField GOAL = field("InvestmentPla", "goal");
  public static final QueryField ACCOUNT_ID = field("InvestmentPla", "accountID");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String") String plan_name;
  private final @ModelField(targetType="String") String description;
  private final @ModelField(targetType="Float") Double deposit;
  private final @ModelField(targetType="Float") Double goal;
  private final @ModelField(targetType="ID") String accountID;
  public String getId() {
      return id;
  }
  
  public String getPlanName() {
      return plan_name;
  }
  
  public String getDescription() {
      return description;
  }
  
  public Double getDeposit() {
      return deposit;
  }
  
  public Double getGoal() {
      return goal;
  }
  
  public String getAccountId() {
      return accountID;
  }
  
  private InvestmentPla(String id, String plan_name, String description, Double deposit, Double goal, String accountID) {
    this.id = id;
    this.plan_name = plan_name;
    this.description = description;
    this.deposit = deposit;
    this.goal = goal;
    this.accountID = accountID;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      InvestmentPla investmentPla = (InvestmentPla) obj;
      return ObjectsCompat.equals(getId(), investmentPla.getId()) &&
              ObjectsCompat.equals(getPlanName(), investmentPla.getPlanName()) &&
              ObjectsCompat.equals(getDescription(), investmentPla.getDescription()) &&
              ObjectsCompat.equals(getDeposit(), investmentPla.getDeposit()) &&
              ObjectsCompat.equals(getGoal(), investmentPla.getGoal()) &&
              ObjectsCompat.equals(getAccountId(), investmentPla.getAccountId());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getPlanName())
      .append(getDescription())
      .append(getDeposit())
      .append(getGoal())
      .append(getAccountId())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("InvestmentPla {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("plan_name=" + String.valueOf(getPlanName()) + ", ")
      .append("description=" + String.valueOf(getDescription()) + ", ")
      .append("deposit=" + String.valueOf(getDeposit()) + ", ")
      .append("goal=" + String.valueOf(getGoal()) + ", ")
      .append("accountID=" + String.valueOf(getAccountId()))
      .append("}")
      .toString();
  }
  
  public static BuildStep builder() {
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
  public static InvestmentPla justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new InvestmentPla(
      id,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      plan_name,
      description,
      deposit,
      goal,
      accountID);
  }
  public interface BuildStep {
    InvestmentPla build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep planName(String planName);
    BuildStep description(String description);
    BuildStep deposit(Double deposit);
    BuildStep goal(Double goal);
    BuildStep accountId(String accountId);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private String plan_name;
    private String description;
    private Double deposit;
    private Double goal;
    private String accountID;
    @Override
     public InvestmentPla build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new InvestmentPla(
          id,
          plan_name,
          description,
          deposit,
          goal,
          accountID);
    }
    
    @Override
     public BuildStep planName(String planName) {
        this.plan_name = planName;
        return this;
    }
    
    @Override
     public BuildStep description(String description) {
        this.description = description;
        return this;
    }
    
    @Override
     public BuildStep deposit(Double deposit) {
        this.deposit = deposit;
        return this;
    }
    
    @Override
     public BuildStep goal(Double goal) {
        this.goal = goal;
        return this;
    }
    
    @Override
     public BuildStep accountId(String accountId) {
        this.accountID = accountId;
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
    private CopyOfBuilder(String id, String planName, String description, Double deposit, Double goal, String accountId) {
      super.id(id);
      super.planName(planName)
        .description(description)
        .deposit(deposit)
        .goal(goal)
        .accountId(accountId);
    }
    
    @Override
     public CopyOfBuilder planName(String planName) {
      return (CopyOfBuilder) super.planName(planName);
    }
    
    @Override
     public CopyOfBuilder description(String description) {
      return (CopyOfBuilder) super.description(description);
    }
    
    @Override
     public CopyOfBuilder deposit(Double deposit) {
      return (CopyOfBuilder) super.deposit(deposit);
    }
    
    @Override
     public CopyOfBuilder goal(Double goal) {
      return (CopyOfBuilder) super.goal(goal);
    }
    
    @Override
     public CopyOfBuilder accountId(String accountId) {
      return (CopyOfBuilder) super.accountId(accountId);
    }
  }
  
}
