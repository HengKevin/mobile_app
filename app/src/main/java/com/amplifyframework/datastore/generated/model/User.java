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

/** This is an auto generated class representing the User type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Users")
public final class User implements Model {
  public static final QueryField ID = field("User", "id");
  public static final QueryField FIRSTNAME = field("User", "firstname");
  public static final QueryField LASTNAME = field("User", "lastname");
  public static final QueryField EMAIL = field("User", "email");
  public static final QueryField PHONENUMBER = field("User", "phonenumber");
  public static final QueryField PASSWORD = field("User", "password");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String firstname;
  private final @ModelField(targetType="String") String lastname;
  private final @ModelField(targetType="AWSEmail") String email;
  private final @ModelField(targetType="AWSPhone") String phonenumber;
  private final @ModelField(targetType="Account") @HasMany(associatedWith = "userID", type = Account.class) List<Account> Accounts = null;
  private final @ModelField(targetType="String") String password;
  public String getId() {
      return id;
  }
  
  public String getFirstname() {
      return firstname;
  }
  
  public String getLastname() {
      return lastname;
  }
  
  public String getEmail() {
      return email;
  }
  
  public String getPhonenumber() {
      return phonenumber;
  }
  
  public List<Account> getAccounts() {
      return Accounts;
  }
  
  public String getPassword() {
      return password;
  }
  
  private User(String id, String firstname, String lastname, String email, String phonenumber, String password) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.phonenumber = phonenumber;
    this.password = password;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      User user = (User) obj;
      return ObjectsCompat.equals(getId(), user.getId()) &&
              ObjectsCompat.equals(getFirstname(), user.getFirstname()) &&
              ObjectsCompat.equals(getLastname(), user.getLastname()) &&
              ObjectsCompat.equals(getEmail(), user.getEmail()) &&
              ObjectsCompat.equals(getPhonenumber(), user.getPhonenumber()) &&
              ObjectsCompat.equals(getPassword(), user.getPassword());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getFirstname())
      .append(getLastname())
      .append(getEmail())
      .append(getPhonenumber())
      .append(getPassword())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("User {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("firstname=" + String.valueOf(getFirstname()) + ", ")
      .append("lastname=" + String.valueOf(getLastname()) + ", ")
      .append("email=" + String.valueOf(getEmail()) + ", ")
      .append("phonenumber=" + String.valueOf(getPhonenumber()) + ", ")
      .append("password=" + String.valueOf(getPassword()))
      .append("}")
      .toString();
  }
  
  public static FirstnameStep builder() {
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
  public static User justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new User(
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
      firstname,
      lastname,
      email,
      phonenumber,
      password);
  }
  public interface FirstnameStep {
    BuildStep firstname(String firstname);
  }
  

  public interface BuildStep {
    User build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep lastname(String lastname);
    BuildStep email(String email);
    BuildStep phonenumber(String phonenumber);
    BuildStep password(String password);
  }
  

  public static class Builder implements FirstnameStep, BuildStep {
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String phonenumber;
    private String password;
    @Override
     public User build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new User(
          id,
          firstname,
          lastname,
          email,
          phonenumber,
          password);
    }
    
    @Override
     public BuildStep firstname(String firstname) {
        Objects.requireNonNull(firstname);
        this.firstname = firstname;
        return this;
    }
    
    @Override
     public BuildStep lastname(String lastname) {
        this.lastname = lastname;
        return this;
    }
    
    @Override
     public BuildStep email(String email) {
        this.email = email;
        return this;
    }
    
    @Override
     public BuildStep phonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
        return this;
    }
    
    @Override
     public BuildStep password(String password) {
        this.password = password;
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
    private CopyOfBuilder(String id, String firstname, String lastname, String email, String phonenumber, String password) {
      super.id(id);
      super.firstname(firstname)
        .lastname(lastname)
        .email(email)
        .phonenumber(phonenumber)
        .password(password);
    }
    
    @Override
     public CopyOfBuilder firstname(String firstname) {
      return (CopyOfBuilder) super.firstname(firstname);
    }
    
    @Override
     public CopyOfBuilder lastname(String lastname) {
      return (CopyOfBuilder) super.lastname(lastname);
    }
    
    @Override
     public CopyOfBuilder email(String email) {
      return (CopyOfBuilder) super.email(email);
    }
    
    @Override
     public CopyOfBuilder phonenumber(String phonenumber) {
      return (CopyOfBuilder) super.phonenumber(phonenumber);
    }
    
    @Override
     public CopyOfBuilder password(String password) {
      return (CopyOfBuilder) super.password(password);
    }
  }
  
}
